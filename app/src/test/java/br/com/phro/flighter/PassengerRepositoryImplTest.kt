package br.com.phro.flighter

import br.com.phro.flighter.dao.PassengerDAO
import br.com.phro.flighter.database.entity.Passenger
import br.com.phro.flighter.di.appModule
import br.com.phro.flighter.repository.PassengerRepositoryImpl
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.Runs
import io.mockk.clearAllMocks
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNull
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.After
import org.koin.core.context.loadKoinModules
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject

class PassengerRepositoryImplTest : KoinTest {

    @MockK
    lateinit var passengerDAO: PassengerDAO

    @MockK
    lateinit var passenger: Passenger

    private val passengerRepositoryImpl: PassengerRepositoryImpl by inject()

    // Dar o start no nosso contexto do Koin e realiza a criação do mesmo para o meu teste.
    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger()
        modules(appModule)
    }

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        // Criar modulo no Start = True
        // O Override seria relacionado a o que?
        val module = module(createdAtStart = true, override = true) {
            single { passengerDAO }
        }

        /**
         * O(s) módulo(s) que eu defini serão carregados aqui.
         * Eles são carregados no contexto Global que foi startado
         */

        loadKoinModules(module)
    }

    @Test
    fun `when passed an passenger should insert`() = runBlocking {

        // Given
        coEvery {
            passengerDAO.insert(
                passenger = Passenger(
                    234,
                    "222",
                    "ph@email.com",
                    "12345",
                    "P",
                    "H",
                    "12/01/1990",
                    null
                )
            )
        } just Runs

        // When
        passenger = Passenger(
            234,
            "222",
            "ph@email.com",
            "12345",
            "P",
            "H",
            "12/01/1990",
            null
        )

        passengerRepositoryImpl.insertPassenger(passenger)

        // Then
        coVerify {
            passengerRepositoryImpl.insertPassenger(passenger)
        }
    }

    @Test
    fun `should get the passenger id`() = runBlocking {

        // Given
        val id = 123L
        val email = "ph@mail.com"
        val password = "12345"

        coEvery {
            passengerDAO.getUserId(
                email = email,
                password = password
            )

        } returns id

        val returnedId = passengerRepositoryImpl.getUserId(email = email, password = password)

        // Then
        assertEquals(123L, returnedId)
    }

    @Test
    fun `should not get the passenger id`() = runBlocking {
        // Given
        val email = "ph@mail.com"
        val password = "12345"

        coEvery {
            passengerDAO.getUserId(
                email = email,
                password = password
            )
        } returns null

        // When
        val returnedId = passengerRepositoryImpl.getUserId(email = email, password = password)

        // Then
        assertNull(returnedId)
    }

    @After
    fun tearDown() {
        // Limpando o que está Mockkado
        clearAllMocks()
        // Parando o koin
        stopKoin()
    }
}
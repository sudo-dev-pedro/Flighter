package br.com.raywenderlich.flighter

import br.com.raywenderlich.flighter.dao.PassengerDAO
import br.com.raywenderlich.flighter.database.entity.Passenger
import br.com.raywenderlich.flighter.di.appModule
import br.com.raywenderlich.flighter.repository.PassengerRepositoryImpl
import io.mockk.*
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.*
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
    fun `when passed an passenger should insert`() {

        // Behavior (given)
        every {
            runBlocking {
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
            }
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

        // Then
        runBlocking {
            passengerRepositoryImpl.insertPassenger(passenger)
        }

        verify {
            runBlocking {
                passengerRepositoryImpl.insertPassenger(passenger)
            }
        }
    }

    @Test
    fun `should get the passenger id`() {
        // Behavior (given)
        val id = 123L
        val email = "ph@mail.com"
        val password = "12345"

        every {
            runBlocking {
                passengerDAO.getUserId(
                    email = email,
                    password = password
                )
            }
        } returns id

        runBlocking {
            val returnedId = passengerRepositoryImpl.getUserId(email = email, password = password)
            // Then
            Assert.assertEquals(123L, returnedId)
        }
    }

    @Test
    fun `should not get the passenger id`() {
        // Behavior (given)
        val email = "ph@mail.com"
        val password = "12345"

        every {
            runBlocking {
                passengerDAO.getUserId(
                    email = email,
                    password = password
                )
            }
        } returns null

        runBlocking {
            // When
            val returnedId = passengerRepositoryImpl.getUserId(email = email, password = password)

            // Then
            TestCase.assertNull(returnedId)
        }
    }

    @After
    fun tearDown() {
        // Limpando o que está Mockkado
        clearAllMocks()
        // Parando o koin
        stopKoin()
    }
}
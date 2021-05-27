package br.com.phro.flighter

import br.com.phro.flighter.dao.FlightDAO
import br.com.phro.flighter.database.entity.Flight
import br.com.phro.flighter.di.appModule
import br.com.phro.flighter.repository.FlightRepositoryImpl
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.Runs
import io.mockk.clearAllMocks
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.loadKoinModules
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject

class FlightRepositoryImplTest : KoinTest {

    @MockK
    lateinit var flightDAO: FlightDAO

    @MockK
    lateinit var flight: Flight

    private val flightRepositoryImpl: FlightRepositoryImpl by inject()

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger()
        modules(appModule)
    }

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        val module = module(createdAtStart = true, override = true) {
            single { flightDAO }
        }

        loadKoinModules(module)
    }

    @Test
    fun `when passed an flight should insert`() = runBlocking {

        // Given
        coEvery {
            flightDAO.insertFlight(
                flight = Flight(
                    234,
                    "Brasilia",
                    "Curitiba",
                    "BSB",
                    "CWB",
                    "01/01/2021",
                    "01/01/2021",
                    'B',
                    'C',
                    "C4",
                    "M5",
                    "2 Hours",
                    "Good Flight",
                    275.0,
                    2
                )
            )
        } just Runs

        // When
        flight = Flight(
            234,
            "Brasilia",
            "Curitiba",
            "BSB",
            "CWB",
            "01/01/2021",
            "01/01/2021",
            'B',
            'C',
            "C4",
            "M5",
            "2 Hours",
            "Good Flight",
            275.0,
            2
        )

        flightRepositoryImpl.insertFlight(flight)

        // Then
        coVerify {
            flightRepositoryImpl.insertFlight(flight)
        }
    }

    @After
    fun tearDown() {
        clearAllMocks()
        stopKoin()
    }
}
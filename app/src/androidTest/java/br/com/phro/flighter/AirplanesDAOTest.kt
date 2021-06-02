package br.com.phro.flighter

import android.content.Context
import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import br.com.phro.flighter.dao.AirplaneDAO
import br.com.phro.flighter.database.AppDatabase
import br.com.phro.flighter.database.data.AirplanesProvider
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AirplanesDAOTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase
    private lateinit var airplaneDAO: AirplaneDAO

    @Before
    fun setup() {
        val context: Context = InstrumentationRegistry.getInstrumentation().context

        try {
            database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
                // Sem essa permissão o Room lança um erro!
                .allowMainThreadQueries()
                .build()

        } catch (e: Exception) {
            e.message?.let { errorMessage ->
                Log.i(this.javaClass.simpleName, errorMessage)
            }
        }
        airplaneDAO = database.airplaneDAO()
    }

    @Test
    fun testInsertAirplanes() = runBlocking {
        for (airplane in AirplanesProvider.airplanesList) {
            airplaneDAO.insertAirplane(airplane)
        }

        val numberOfAirplanes = airplaneDAO.getAllAirplanes().size

        assertEquals(2, numberOfAirplanes)
    }

    @Test
    fun testClearPassengers() = runBlocking {
        for (airplane in AirplanesProvider.airplanesList) {
            airplaneDAO.insertAirplane(airplane)
        }

        assertTrue(airplaneDAO.getAllAirplanes().isNotEmpty())

        airplaneDAO.clearTable()

        assertTrue(airplaneDAO.getAllAirplanes().isEmpty())
    }

    @After
    fun tearDown() {
        database.close()
    }
}
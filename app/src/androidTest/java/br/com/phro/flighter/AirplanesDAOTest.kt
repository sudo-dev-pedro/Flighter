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
import kotlinx.coroutines.runBlocking
import org.junit.*
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
    fun testInsertAirplanes() {
        for (airplane in AirplanesProvider.airplanesList) {
            runBlocking {
                airplaneDAO.insertAirplane(airplane)
            }
        }

        val numberOfAirplanes = airplaneDAO.getAllAirplanes().size

        Assert.assertEquals(2, numberOfAirplanes)
    }

    @Test
    fun testClearPassengers() {
        for (airplane in AirplanesProvider.airplanesList) {
            runBlocking {
                airplaneDAO.insertAirplane(airplane)
            }
        }

        Log.d("Teste", airplaneDAO.getAllAirplanes().size.toString())

        Assert.assertTrue(airplaneDAO.getAllAirplanes().isNotEmpty())

        airplaneDAO.clearTable()
        Assert.assertTrue(airplaneDAO.getAllAirplanes().isEmpty())
    }

    @After
    fun tearDown() {
        database.close()
    }
}
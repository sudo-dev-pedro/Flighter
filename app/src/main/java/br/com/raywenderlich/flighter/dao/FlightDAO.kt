package br.com.raywenderlich.flighter.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import br.com.raywenderlich.flighter.database.DatabaseConstants.FLIGHT_TABLE_NAME
import br.com.raywenderlich.flighter.database.entity.Flight

@Dao
interface FlightDAO {

    @Insert
    suspend fun insertFlight(flight: Flight)

    @Query("SELECT * FROM $FLIGHT_TABLE_NAME WHERE departure_city = :departureCity AND arrival_city = :arrivalCity")
    suspend fun getFlightResults(departureCity: String, arrivalCity: String): List<Flight>

    @Delete
    suspend fun deleteFlight(flight: Flight)
}
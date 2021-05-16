package br.com.raywenderlich.flighter.repository

import androidx.lifecycle.LiveData
import br.com.raywenderlich.flighter.database.entity.Flight

interface FlightRepository {
    suspend fun insertFlight(flight: Flight)

    suspend fun getFlightResults(departureCity: String, arrivalCity: String): List<Flight>

    suspend fun getFlightById(flightId: Long?): Flight

    suspend fun deleteFlight(flight: Flight)
}
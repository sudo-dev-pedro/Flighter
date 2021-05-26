package br.com.phro.flighter.repository

import br.com.phro.flighter.database.entity.Flight

interface FlightRepository {
    suspend fun insertFlight(flight: Flight)

    suspend fun getFlightResults(departureCity: String, arrivalCity: String): List<Flight>

    suspend fun getFlightById(flightId: Long?): Flight

    suspend fun deleteFlight(flight: Flight)
}
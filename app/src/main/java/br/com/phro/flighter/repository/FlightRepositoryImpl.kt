package br.com.phro.flighter.repository

import br.com.phro.flighter.dao.FlightDAO
import br.com.phro.flighter.database.entity.Flight

class FlightRepositoryImpl(
    private val flightDAO: FlightDAO
) : FlightRepository {

    override suspend fun insertFlight(flight: Flight) {
        flightDAO.insertFlight(flight)
    }

    override suspend fun getFlightResults(
        departureCity: String,
        arrivalCity: String
    ): List<Flight> = flightDAO.getFlightResults(departureCity, arrivalCity)

    override suspend fun getFlightById(flightId: Long?): Flight = flightDAO.getFlightById(flightId)

    override suspend fun deleteFlight(flight: Flight) {
        flightDAO.deleteFlight(flight)
    }
}
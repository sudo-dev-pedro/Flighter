package br.com.raywenderlich.flighter.repository

import androidx.lifecycle.LiveData
import br.com.raywenderlich.flighter.app.FlighterApplication
import br.com.raywenderlich.flighter.dao.FlightDAO
import br.com.raywenderlich.flighter.database.entity.Flight

class FlightRepositoryImpl(
    private val flightDAO: FlightDAO
) : FlightRepository {

    override fun insertFlight(flight: Flight) {
        TODO("Not yet implemented")
    }

    override fun getFlightResults(
        departureCity: String,
        arrivalCity: String
    ): List<Flight> = flightDAO.getFlightResults(departureCity, arrivalCity)

    override fun deleteFlight(flight: Flight) {
        TODO("Not yet implemented")
    }
}
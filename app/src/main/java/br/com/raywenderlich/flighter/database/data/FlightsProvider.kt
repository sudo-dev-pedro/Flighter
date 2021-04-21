package br.com.raywenderlich.flighter.database.data

import br.com.raywenderlich.flighter.database.entity.Flight

object FlightsProvider {

    var flightList = initFlightsList()

    private fun initFlightsList(): MutableList<Flight> {
        val flights = mutableListOf<Flight>()

        flights.add(
            Flight(
                departureCity = "Brasilia",
                arrivalCity = "Curitiba",
                departureCityInitials = "BSB",
                arrivalCityInitials = "CWB",
                departureDate = "21/04/2021",
                arrivalDate = "21/04/2021",
                departureTerminal = 'B',
                arrivalTerminal = 'A',
                departureGate = "12",
                arrivalGate = "22",
                estimatedFlightTime = "3h 15m",
                airlineName = "BRAZA",
                totalPrice = 345.0,
                airplaneID = 1
            )
        )

        // Que horas o voo parte?
        // Que horas o voo chega?
        flights.add(
            Flight(
                departureCity = "Brasilia",
                arrivalCity = "Curitiba",
                departureCityInitials = "BSB",
                arrivalCityInitials = "CWB",
                departureDate = "21/04/2021",
                arrivalDate = "21/04/2021",
                departureTerminal = 'B',
                arrivalTerminal = 'A',
                departureGate = "12",
                arrivalGate = "22",
                estimatedFlightTime = "3h 15m",
                airlineName = "BRAZA",
                totalPrice = 345.0,
                airplaneID = 1
            )
        )

        return flights
    }
}
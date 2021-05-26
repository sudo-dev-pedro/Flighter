package br.com.phro.flighter.database.data

import br.com.phro.flighter.database.entity.Flight

object FlightsProvider {

    var flightList = initFlightsListTests()

    fun initFlightsList(departureCity: String, arrivalCity: String, departureDate: String): MutableList<Flight> {
        val flights = mutableListOf<Flight>()

        flights.add(
            Flight(
                departureCity = departureCity,
                arrivalCity = arrivalCity,
                departureCityInitials = "BSB",
                arrivalCityInitials = "CWB",
                departureDate = departureDate,
                arrivalDate = "22/04/21",
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
                departureCity = departureCity,
                arrivalCity = arrivalCity,
                departureCityInitials = "BSB",
                arrivalCityInitials = "CWB",
                departureDate = departureDate,
                arrivalDate = "22/04/21",
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
    private fun initFlightsListTests(): MutableList<Flight> {
        val flights = mutableListOf<Flight>()

        flights.add(
            Flight(
                departureCity = "Brasilia",
                arrivalCity = "Curitiba",
                departureCityInitials = "BSB",
                arrivalCityInitials = "CWB",
                departureDate = "22/04/2021",
                arrivalDate = "22/04/2021",
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
                departureDate = "22/04/2021",
                arrivalDate = "22/04/2021",
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
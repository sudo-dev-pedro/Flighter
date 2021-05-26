package br.com.raywenderlich.flighter.database.data

import br.com.raywenderlich.flighter.database.entity.Airplane

object AirplanesProvider {

    var airplanesList = initAirplanesList()

    private fun initAirplanesList(): MutableList<Airplane> {
        val airplanes = mutableListOf<Airplane>()

        airplanes.add(
            Airplane(
                1,
                "A330",
                220
            )
        )

        airplanes.add(
            Airplane(
                2,
                "A747",
                240
            )
        )

        return airplanes
    }
}
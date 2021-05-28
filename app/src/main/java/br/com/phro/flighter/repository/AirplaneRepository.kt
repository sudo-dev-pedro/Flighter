package br.com.phro.flighter.repository

import br.com.phro.flighter.database.entity.Airplane

interface AirplaneRepository {

    suspend fun insertAirplane(airplane: Airplane)

    suspend fun getAllAirplanes(): List<Airplane>

    suspend fun updateAirplane(airplane: Airplane)

    suspend fun deleteAirplane(airplane: Airplane)

    suspend fun clearTable()
}
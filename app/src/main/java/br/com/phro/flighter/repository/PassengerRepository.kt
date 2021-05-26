package br.com.phro.flighter.repository

import br.com.phro.flighter.database.entity.Passenger

interface PassengerRepository {
    suspend fun insertPassenger(passenger: Passenger)

    suspend fun getUserId(email: String, password: String): Long?

    fun deletePassengerData(passenger: Passenger)
}
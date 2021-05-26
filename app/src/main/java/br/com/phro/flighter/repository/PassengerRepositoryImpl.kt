package br.com.phro.flighter.repository

import br.com.phro.flighter.dao.PassengerDAO
import br.com.phro.flighter.database.entity.Passenger

class PassengerRepositoryImpl(
    private val passengerDAO: PassengerDAO
) : PassengerRepository {

    override suspend fun insertPassenger(passenger: Passenger) =
        passengerDAO.insert(passenger)

    override suspend fun getUserId(email: String, password: String): Long? =
        passengerDAO.getUserId(email, password)

    override fun deletePassengerData(passenger: Passenger) {
        TODO("Not yet implemented")
    }
}
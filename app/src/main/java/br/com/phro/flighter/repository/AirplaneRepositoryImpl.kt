package br.com.phro.flighter.repository

import br.com.phro.flighter.dao.AirplaneDAO
import br.com.phro.flighter.database.entity.Airplane
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@KoinApiExtension
class AirplaneRepositoryImpl : AirplaneRepository, KoinComponent {

    private val airplaneDAO: AirplaneDAO by inject()

    override suspend fun insertAirplane(airplane: Airplane) {
        airplaneDAO.insertAirplane(airplane)
    }

    override suspend fun getAllAirplanes(): List<Airplane> = airplaneDAO.getAllAirplanes()

    override suspend fun updateAirplane(airplane: Airplane) {
        airplaneDAO.updateAirplane(airplane)
    }

    override suspend fun deleteAirplane(airplane: Airplane) {
        airplaneDAO.deleteAirplane(airplane)
    }

    override suspend fun clearTable() {
        airplaneDAO.clearTable()
    }
}
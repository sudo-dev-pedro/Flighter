package br.com.phro.flighter.dao

import androidx.room.*
import br.com.phro.flighter.database.DatabaseConstants
import br.com.phro.flighter.database.entity.Airplane

@Dao
interface AirplaneDAO {

    @Insert
    fun insertAirplane(airplane: Airplane)

    @Query("SELECT * FROM ${DatabaseConstants.AIRPLANE_TABLE_NAME}")
    fun getAllAirplanes(): List<Airplane>

    @Update
    fun updateAirplane(airplane: Airplane)

    @Delete
    fun deleteAirplane(airplane: Airplane)

    @Query("DELETE FROM ${DatabaseConstants.AIRPLANE_TABLE_NAME}")
    fun clearTable()
}
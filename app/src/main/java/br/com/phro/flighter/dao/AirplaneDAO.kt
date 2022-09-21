package br.com.phro.flighter.dao

import androidx.room.*
import br.com.phro.flighter.database.DatabaseConstants.AIRPLANE_TABLE_NAME
import br.com.phro.flighter.database.entity.Airplane

@Dao
interface AirplaneDAO {

    @Insert
    suspend fun insertAirplane(airplane: Airplane)

    @Query("SELECT * FROM $AIRPLANE_TABLE_NAME")
    suspend fun getAllAirplanes(): List<Airplane>

    @Update
    suspend fun updateAirplane(airplane: Airplane)

    @Delete
    suspend fun deleteAirplane(airplane: Airplane)

    @Query("DELETE FROM $AIRPLANE_TABLE_NAME")
    suspend fun clearTable()
}
package br.com.phro.flighter.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import br.com.phro.flighter.database.DatabaseConstants.PASSENGER_TABLE_NAME
import br.com.phro.flighter.database.entity.Passenger

@Dao
interface PassengerDAO {

    @Insert
    suspend fun insert(passenger: Passenger)

    @Query("SELECT id FROM $PASSENGER_TABLE_NAME WHERE email = :email AND password = :password")
    suspend fun getUserId(email: String, password: String): Long?

    @Query("SELECT * FROM $PASSENGER_TABLE_NAME ORDER BY id")
    fun getAllPassengers(): List<Passenger>

    @Query("DELETE FROM $PASSENGER_TABLE_NAME")
    fun clearTable()

    @Delete
    fun deletePassengerData(passenger: Passenger)
}
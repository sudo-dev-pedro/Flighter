package br.com.phro.flighter.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.phro.flighter.converters.DateConverter
import br.com.phro.flighter.dao.AirplaneDAO
import br.com.phro.flighter.dao.FlightDAO
import br.com.phro.flighter.dao.PassengerDAO
import br.com.phro.flighter.database.entity.Airplane
import br.com.phro.flighter.database.entity.Book
import br.com.phro.flighter.database.entity.Flight
import br.com.phro.flighter.database.entity.Passenger

@Database(
    entities = [
        (Airplane::class),
        (Book::class),
        (Flight::class),
        (Passenger::class)
    ],
    version = 1,
    exportSchema = false
)

@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun passengerDAO(): PassengerDAO
    abstract fun airplaneDAO(): AirplaneDAO
    abstract fun flightDAO(): FlightDAO
}
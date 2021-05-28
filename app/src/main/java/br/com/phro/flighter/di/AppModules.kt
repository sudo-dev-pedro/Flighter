package br.com.phro.flighter.di

import br.com.phro.flighter.FlighterApplication.Companion.database
import br.com.phro.flighter.repository.AirplaneRepositoryImpl
import br.com.phro.flighter.repository.FlightRepositoryImpl
import br.com.phro.flighter.repository.PassengerRepositoryImpl
import org.koin.dsl.module

val appModule = module {
    single {
        database.passengerDAO()
    }
    single {
        database.flightDAO()
    }
    single {
        database.airplaneDAO()
    }
    single {
        PassengerRepositoryImpl(passengerDAO = get())
    }
    single {
        FlightRepositoryImpl(flightDAO = get())
    }
    single {
        AirplaneRepositoryImpl()
    }
}
package br.com.raywenderlich.flighter.di

import br.com.raywenderlich.flighter.FlighterApplication
import br.com.raywenderlich.flighter.repository.FlightRepositoryImpl
import br.com.raywenderlich.flighter.repository.PassengerRepositoryImpl
import org.koin.dsl.module

val appModule = module {
    single {
        FlighterApplication.database.passengerDAO()
    }
    single {
        FlighterApplication.database.flightDAO()
    }

    single {
        PassengerRepositoryImpl(passengerDAO = get())
    }

    single {
        FlightRepositoryImpl(flightDAO = get())
    }
}
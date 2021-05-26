package br.com.phro.flighter.di

import br.com.phro.flighter.FlighterApplication
import br.com.phro.flighter.repository.FlightRepositoryImpl
import br.com.phro.flighter.repository.PassengerRepositoryImpl
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
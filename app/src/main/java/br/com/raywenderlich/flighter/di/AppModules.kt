package br.com.raywenderlich.flighter.di

import br.com.raywenderlich.flighter.app.FlighterApplication
import br.com.raywenderlich.flighter.repository.FlightRepositoryImpl
import br.com.raywenderlich.flighter.repository.PassengerRepositoryImpl
import br.com.raywenderlich.flighter.ui.flight.FlightSearchViewModel
import br.com.raywenderlich.flighter.ui.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
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

val viewModelModule = module {
    viewModel {
        LoginViewModel(passengerRepositoryImpl = get())
    }

    viewModel {
        FlightSearchViewModel(flightRepositoryImpl = get())
    }
}
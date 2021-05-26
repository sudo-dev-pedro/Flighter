package br.com.raywenderlich.flighter.di

import br.com.raywenderlich.flighter.ui.flight.FlightDetailsViewModel
import br.com.raywenderlich.flighter.ui.flight.FlightSearchViewModel
import br.com.raywenderlich.flighter.ui.login.LoginViewModel
import br.com.raywenderlich.flighter.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        LoginViewModel(passengerRepositoryImpl = get())
    }

    viewModel {
        FlightSearchViewModel(flightRepositoryImpl = get())
    }

    viewModel {
        FlightDetailsViewModel()
    }

    viewModel {
        SplashViewModel()
    }
}
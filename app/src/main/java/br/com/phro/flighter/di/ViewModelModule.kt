package br.com.phro.flighter.di

import br.com.phro.flighter.ui.flight.FlightDetailsViewModel
import br.com.phro.flighter.ui.flight.FlightSearchViewModel
import br.com.phro.flighter.ui.login.LoginViewModel
import br.com.phro.flighter.ui.splash.SplashViewModel
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
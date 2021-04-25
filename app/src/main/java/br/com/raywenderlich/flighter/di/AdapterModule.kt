package br.com.raywenderlich.flighter.di

import br.com.raywenderlich.flighter.ui.flight.adapter.FlightSearchAdapter
import org.koin.dsl.module

val adapterModule = module {
    factory { FlightSearchAdapter() }
}
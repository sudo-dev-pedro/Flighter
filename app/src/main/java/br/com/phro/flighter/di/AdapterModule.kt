package br.com.phro.flighter.di

import br.com.phro.flighter.ui.flight.adapter.FlightSearchAdapter
import org.koin.dsl.module

val adapterModule = module {
    factory { FlightSearchAdapter() }
}
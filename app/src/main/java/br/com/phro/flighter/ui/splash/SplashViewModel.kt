package br.com.phro.flighter.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.phro.flighter.database.data.AirplanesProvider
import br.com.phro.flighter.database.data.FlightsProvider
import br.com.phro.flighter.repository.AirplaneRepositoryImpl
import br.com.phro.flighter.repository.FlightRepositoryImpl
import kotlinx.coroutines.launch
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@KoinApiExtension
class SplashViewModel : ViewModel(), KoinComponent {

    private val flightRepositoryImpl: FlightRepositoryImpl by inject()
    private val airplaneRepositoryImpl: AirplaneRepositoryImpl by inject()

    fun generateAirplanesDemoData() {
        viewModelScope.launch {
            for (airplane in AirplanesProvider.airplanesList) {
                airplaneRepositoryImpl.insertAirplane(airplane)
            }
        }
    }

    fun generateFlightsDemoData(departureCity: String, arrivalCity: String, departureDate: String) {
        viewModelScope.launch {
            for (flight in FlightsProvider.initFlightsList(
                departureCity,
                arrivalCity,
                departureDate
            )) {
                flightRepositoryImpl.insertFlight(flight)
            }
        }
    }
}
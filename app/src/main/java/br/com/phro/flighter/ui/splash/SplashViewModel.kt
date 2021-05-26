package br.com.raywenderlich.flighter.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.raywenderlich.flighter.database.data.FlightsProvider
import br.com.raywenderlich.flighter.repository.FlightRepositoryImpl
import kotlinx.coroutines.launch
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@KoinApiExtension
class SplashViewModel : ViewModel(), KoinComponent {

    private val flightRepositoryImpl: FlightRepositoryImpl by inject()

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
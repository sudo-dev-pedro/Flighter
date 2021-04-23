package br.com.raywenderlich.flighter.ui.flight

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.raywenderlich.flighter.database.data.FlightsProvider
import br.com.raywenderlich.flighter.database.entity.Flight
import br.com.raywenderlich.flighter.repository.FlightRepositoryImpl
import kotlinx.coroutines.launch

class FlightSearchViewModel(
    private val flightRepositoryImpl: FlightRepositoryImpl
) : ViewModel() {

    private val _flightResults = MutableLiveData<List<Flight>>()
    val flightResults: LiveData<List<Flight>>
    get() = _flightResults

    fun generateFlightsDemoData(departureCity: String, arrivalCity: String, departureDate: String) {
        viewModelScope.launch {
            for (flight in FlightsProvider.initFlightsList(departureCity, arrivalCity, departureDate)) {
                flightRepositoryImpl.insertFlight(flight)
            }
        }
    }

    fun getFlights(departureCity: String, arrivalCity: String) {
        viewModelScope.launch {
            _flightResults.value = flightRepositoryImpl.getFlightResults(departureCity, arrivalCity)
        }
    }
}
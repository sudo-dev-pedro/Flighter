package br.com.phro.flighter.ui.flight

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.phro.flighter.database.data.FlightsProvider
import br.com.phro.flighter.database.entity.Flight
import br.com.phro.flighter.repository.FlightRepositoryImpl
import kotlinx.coroutines.launch

class FlightSearchViewModel(
    private val flightRepositoryImpl: FlightRepositoryImpl
) : ViewModel() {

    private val _flightResults = MutableLiveData<List<Flight>>()
    val flightResults: LiveData<List<Flight>>
        get() = _flightResults

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

    fun getFlights(departureCity: String, arrivalCity: String) {
        viewModelScope.launch {
            _flightResults.postValue(
                flightRepositoryImpl.getFlightResults(
                    departureCity,
                    arrivalCity
                )
            )
        }
    }
}
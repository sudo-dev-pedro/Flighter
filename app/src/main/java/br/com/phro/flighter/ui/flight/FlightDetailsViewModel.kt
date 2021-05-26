package br.com.raywenderlich.flighter.ui.flight

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.raywenderlich.flighter.database.entity.Flight
import br.com.raywenderlich.flighter.repository.FlightRepositoryImpl
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class FlightDetailsViewModel : ViewModel(), KoinComponent {

    private val _flightDetails = MutableLiveData<Flight>()
    val flightDetails: LiveData<Flight>
        get() = _flightDetails

    private val flightRepositoryImpl: FlightRepositoryImpl by inject()

    fun getFlightById(flightId: Long?) {
        viewModelScope.launch {
            _flightDetails.postValue(
                flightRepositoryImpl.getFlightById(flightId)
            )
        }
    }
}
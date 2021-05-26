package br.com.phro.flighter.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.phro.flighter.repository.PassengerRepositoryImpl
import kotlinx.coroutines.launch

class LoginViewModel(
    private val passengerRepositoryImpl: PassengerRepositoryImpl
) : ViewModel() {

    private val _userId = MutableLiveData<Long?>()
    val userId: LiveData<Long?>
        get() = _userId

    fun getUserId(email: String, password: String) {
        viewModelScope.launch {
            _userId.value = passengerRepositoryImpl.getUserId(email, password)
        }
    }

}
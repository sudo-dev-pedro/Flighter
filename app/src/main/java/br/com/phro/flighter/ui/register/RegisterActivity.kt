package br.com.phro.flighter.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import br.com.phro.flighter.MainActivity
import br.com.phro.flighter.database.entity.Passenger
import br.com.phro.flighter.databinding.ActivityRegisterBinding
import br.com.phro.flighter.repository.PassengerRepositoryImpl
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject

class RegisterActivity : AppCompatActivity() {

    private lateinit var registerBinding: ActivityRegisterBinding
    private val passengerRepository: PassengerRepositoryImpl by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        registerBinding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = registerBinding.root
        setContentView(view)

        grabPassengerData()
    }

    private fun registerPassenger(passenger: Passenger) {
        lifecycleScope.launch {
            passengerRepository.insertPassenger(passenger)

            withContext(Main) {
                startActivity(
                    Intent(
                        this@RegisterActivity,
                        MainActivity::class.java
                    )
                )
            }
        }
    }

    private fun grabPassengerData() {
        registerBinding.btnRegister.setOnClickListener {
            val cpf = registerBinding.cpfInput.text.toString()
            val firstName = registerBinding.firstNameInput.text.toString()
            val lastName = registerBinding.lastNameInput.text.toString()
            val birthDate = registerBinding.birthDateInput.text.toString()
            val email = registerBinding.emailInput.text.toString()
            val password = registerBinding.passwordInput.text.toString()
            val passport = registerBinding.passportInput.text?.toString()

            val passenger = Passenger(
                cpf = cpf,
                email = email,
                password = password,
                firstName = firstName,
                lastName = lastName,
                birth_date = birthDate,
                passportCode = passport
            )

            if (email.isNotBlank() && password.isNotBlank()) {
                registerPassenger(passenger)
            }
        }
    }
}
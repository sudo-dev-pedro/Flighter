package br.com.raywenderlich.flighter.ui.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import br.com.raywenderlich.flighter.MainActivity
import br.com.raywenderlich.flighter.databinding.ActivityLoginBinding
import br.com.raywenderlich.flighter.ui.register.RegisterActivity
import br.com.raywenderlich.flighter.app.FlighterApplication.Companion.PASSENGER_HASH_CODE
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var loginBinding: ActivityLoginBinding
    private lateinit var view: ConstraintLayout
    private lateinit var sharedPreferences: SharedPreferences

    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        view = loginBinding.root
        setContentView(view)

        sharedPreferences = getPreferences(Context.MODE_PRIVATE)

        if (sharedPreferences.contains(PASSENGER_HASH_CODE)) {
            redirectPassenger()
        }

        grabPassengerCredentials()
    }

    private fun redirectPassenger() {
        startActivity(
            Intent(
                this,
                MainActivity::class.java
            ).addFlags(
                Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            )
        )
    }

    private fun redirectPassengerWithoutAccount() {
        startActivity(
            Intent(
                this,
                RegisterActivity::class.java
            )
        )
    }

    private fun logInPassenger(email: String, password: String) {
        loginViewModel.getUserId(email, password)

        loginViewModel.userId.observe(this) { userId ->
            sharedPreferences.edit()?.putInt(PASSENGER_HASH_CODE, email.hashCode())?.apply()

            if (userId != null) {
                redirectPassenger()
            } else {
                redirectPassengerWithoutAccount()
            }

        }
    }

    private fun grabPassengerCredentials() {
        loginBinding.btnLogin.setOnClickListener {
            val email = loginBinding.emailInput.text.toString()
            val password = loginBinding.passwordInput.text.toString()

            if (email.isNotBlank() && password.isNotBlank()) {
                logInPassenger(email, password)
            }
        }
        createNewAccount()
    }

    private fun createNewAccount() {
        loginBinding.txtRegister.setOnClickListener {
            redirectPassengerWithoutAccount()
        }
    }
}
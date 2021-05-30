package br.com.phro.flighter.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.phro.flighter.R
import br.com.phro.flighter.ui.login.LoginActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.cancel
import org.koin.android.ext.android.inject
import org.koin.core.component.KoinApiExtension

@KoinApiExtension
class SplashActivity : AppCompatActivity() {
    private val coroutineScope by lazy { CoroutineScope(Dispatchers.IO) }

//    private val splashViewModel: SplashViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        navigateToLoginActivity()
    }

    private fun navigateToLoginActivity() {
        coroutineScope.launch {
            delay(3000)
//            splashViewModel.generateAirplanesDemoData()
//            splashViewModel
//                .generateFlightsDemoData("AAA", "BBB", "22/05/2021")

            withContext(Dispatchers.Main) {
                startActivity(
                    Intent(this@SplashActivity, LoginActivity::class.java).addFlags(
                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                    )
                )
                finish()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        coroutineScope.cancel()
        finish()
    }
}
package br.com.raywenderlich.flighter.app

import android.app.Application
import android.content.SharedPreferences
import androidx.room.Room
import br.com.raywenderlich.flighter.database.AppDatabase
import br.com.raywenderlich.flighter.database.DatabaseConstants.DATABASE_NAME
import br.com.raywenderlich.flighter.di.adapterModule
import br.com.raywenderlich.flighter.di.appModule
import br.com.raywenderlich.flighter.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class FlighterApplication : Application() {

    companion object {
        const val PASSENGER_HASH_CODE = "PASSENGER_HASH_CODE"

        lateinit var database: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@FlighterApplication)
            modules(
                listOf(
                    appModule,
                    viewModelModule,
                    adapterModule
                )
            )
        }

        // Isso pode ser usado como DI
        database = Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()
    }
}
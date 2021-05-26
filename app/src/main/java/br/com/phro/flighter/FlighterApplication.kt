package br.com.phro.flighter

import android.app.Application
import androidx.room.Room
import br.com.phro.flighter.database.AppDatabase
import br.com.phro.flighter.database.DatabaseConstants.DATABASE_NAME
import br.com.phro.flighter.di.adapterModule
import br.com.phro.flighter.di.appModule
import br.com.phro.flighter.di.viewModelModule
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
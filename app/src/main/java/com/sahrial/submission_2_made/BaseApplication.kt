package com.sahrial.submission_2_made

import android.app.Application
import com.sahrial.submission_2_made.core.databaseModule
import com.sahrial.submission_2_made.core.networkModule
import com.sahrial.submission_2_made.core.repositoryModule
import com.sahrial.submission_2_made.di.useCaseModule
import com.sahrial.submission_2_made.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@BaseApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}
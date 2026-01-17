package com.geeks.homeshop

import android.app.Application
import com.geeks.homeshop.data.di.dataModule
import com.geeks.homeshop.domain.di.domainModule
import com.geeks.homeshop.ui.di.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)

            modules(dataModule, domainModule, uiModule)
        }

    }

}
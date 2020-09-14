package cz.minarik.youtube

import android.app.Application
import cz.minarik.youtube.di.allModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(allModules)
        }
    }
}
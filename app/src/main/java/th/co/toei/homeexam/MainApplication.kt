package th.co.toei.homeexam

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import th.co.toei.homeexam.di.serviceModule
import th.co.toei.homeexam.di.useCaseModule
import th.co.toei.homeexam.di.viewModelModule

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(listOf(serviceModule, useCaseModule, viewModelModule))
        }
    }
}
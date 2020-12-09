package com.crossdeveloper.mvvmskeleton

import androidx.multidex.MultiDexApplication
import com.crossdeveloper.mvvmskeleton.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

@Suppress("Unused")
class Application : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        // Start Koin
        startKoin {
            androidLogger()
            androidContext(this@Application)
            modules(appModule)
        }
     }
}
package com.weightwatchers.ww_exercise_01

import android.app.Application
import com.weightwatchers.ww_exercise_01.koin.readModules
import com.weightwatchers.ww_exercise_01.koin.retrofitModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(listOf(readModules, retrofitModule))
        }
    }
}
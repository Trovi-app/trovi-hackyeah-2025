package com.example.trovimobileapp

import android.app.Application
import com.example.trovimobileapp.chat.di.chatModule
import com.example.trovimobileapp.core.di.appModule
import com.example.trovimobileapp.core.di.networkModule
import com.example.trovimobileapp.guest.di.guestModule
import com.example.trovimobileapp.host.di.hostModule
import com.example.trovimobileapp.login.di.loginModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class TroviMobileApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@TroviMobileApp)
            modules(appModule, networkModule, loginModule, guestModule, chatModule, hostModule)
        }
    }
}
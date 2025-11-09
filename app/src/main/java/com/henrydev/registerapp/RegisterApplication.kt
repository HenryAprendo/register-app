package com.henrydev.registerapp

import android.app.Application
import com.henrydev.registerapp.data.AppContainer
import com.henrydev.registerapp.data.AppDataContainer

class RegisterApplication: Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }

}
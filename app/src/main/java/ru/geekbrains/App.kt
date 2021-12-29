package ru.geekbrains

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import dagger.android.DaggerApplication
import ru.geekbrains.dagger.ApplicationComponent
import ru.geekbrains.dagger.DaggerApplicationComponent


class App: Application() {


    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        instance  = this
        component = DaggerApplicationComponent.builder()
            .setContext(this)
            .build()
    }

    companion object{
        lateinit var instance: App
    }

    /*
    companion object Navigation {

        private val cicerone: Cicerone<CustomRouter> by lazy {
            Cicerone.create(CustomRouter())
        }
        val navigatorHolder = cicerone.getNavigatorHolder()
        val router = cicerone.router
    }
    */

}
package com.koleychik.clothesstore

import android.app.Application
import com.koleychik.clothesstore.di.components.AppComponent
import com.koleychik.clothesstore.di.components.DaggerAppComponent
import com.koleychik.clothesstore.di.models.AppModule
import com.koleychik.clothesstore.di.models.DatabaseModule
import com.koleychik.clothesstore.di.models.NetworkModule

class App : Application() {

    companion object{
        lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
         component = DaggerAppComponent.builder()
            .appModule(AppModule((this)))
//            .databaseModule(DatabaseModule())
//            .networkModule(NetworkModule())
            .build()


    }
}
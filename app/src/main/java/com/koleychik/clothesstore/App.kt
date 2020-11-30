package com.koleychik.clothesstore

import android.app.Application
import com.koleychik.clothesstore.di.components.AppComponent
import com.koleychik.clothesstore.di.components.DaggerAppComponent
import com.koleychik.clothesstore.di.modules.AppModule

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
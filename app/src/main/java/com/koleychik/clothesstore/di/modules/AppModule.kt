package com.koleychik.clothesstore.di.modules

import android.content.Context
import com.koleychik.clothesstore.ui.dialogs.DialogSetSomething
import com.koleychik.clothesstore.utils.ActiveModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideContext() = context

    @Provides
    @Singleton
    fun provideActiveModel() = ActiveModel()

    @Provides
    fun provideDialogSetSomething(context: Context) = DialogSetSomething(context)
}
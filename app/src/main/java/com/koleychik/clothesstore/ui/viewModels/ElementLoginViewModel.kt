package com.koleychik.clothesstore.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.koleychik.clothesstore.repositories.FirebaseRepository
import javax.inject.Inject

class ElementLoginViewModel @Inject constructor() : ViewModel(){

    @Inject
    lateinit var repository: FirebaseRepository

    val text = MutableLiveData<String>()

}

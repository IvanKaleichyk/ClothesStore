package com.koleychik.clothesstore.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.koleychik.clothesstore.repositories.FirebaseRepository
import javax.inject.Inject

class ElementLoginViewModel @Inject constructor(private val repository: FirebaseRepository) :
    ViewModel() {

    val text = MutableLiveData<String>()

}

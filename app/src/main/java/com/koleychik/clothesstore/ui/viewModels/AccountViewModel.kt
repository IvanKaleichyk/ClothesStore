package com.koleychik.clothesstore.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.koleychik.clothesstore.models.DeviceImage
import com.koleychik.clothesstore.repositories.DeviceImagesRepository
import com.koleychik.clothesstore.ui.states.ImageRvState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AccountViewModel @Inject constructor(): ViewModel() {

    @Inject
    lateinit var repository: DeviceImagesRepository

    val listImages = MutableLiveData<List<DeviceImage>>()

    val stateImageRv = MutableLiveData<ImageRvState>(ImageRvState.Loading)

    val isRvOpen = MutableLiveData(false)

    fun getAllImages() = viewModelScope.launch{
        val list = repository.getAll()
        withContext(Dispatchers.Main){
            if (list == null || list.isEmpty()) listImages.value = listOf()
            else listImages.value = list
        }
    }
}

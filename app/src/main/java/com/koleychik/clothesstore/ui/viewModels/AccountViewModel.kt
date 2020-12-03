package com.koleychik.clothesstore.ui.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.koleychik.clothesstore.models.DeviceImage
import com.koleychik.clothesstore.repositories.DeviceImagesRepository
import com.koleychik.clothesstore.ui.states.ImageRvState
import com.koleychik.clothesstore.utils.constants.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AccountViewModel @Inject constructor(private val repository: DeviceImagesRepository) :
    ViewModel() {

    val listImages = MutableLiveData<List<DeviceImage>>(null)

    val stateImageRv = MutableLiveData<ImageRvState>(ImageRvState.Loading)

    val isRvOpen = MutableLiveData(false)

    fun getAllImages() = viewModelScope.launch {
        Log.d(Constants.TAG, "start getAllImages")
        val list = repository.getAll()
        withContext(Dispatchers.Main) {
            if (list == null || list.isEmpty()) listImages.value = listOf()
            else listImages.value = list
        }
    }
}

package com.koleychik.clothesstore.ui.viewModels

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.koleychik.clothesstore.R
import com.koleychik.clothesstore.repositories.FirebaseRepository
import com.koleychik.clothesstore.ui.states.SignState
import com.koleychik.clothesstore.utils.SignUtils
import kotlinx.coroutines.*
import javax.inject.Inject

class SignViewModel @Inject constructor() : ViewModel() {

    val state = MutableLiveData<SignState>(SignState.Waiting)

    val textFirst = MutableLiveData<String>()
    val textSecond = MutableLiveData<String>()

    private val auth = FirebaseAuth.getInstance()
    private var user = auth.currentUser
    val database = FirebaseDatabase.getInstance()

    @Inject
    lateinit var repository: FirebaseRepository

    fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                user = auth.currentUser
            } else state.value = SignState.Error(R.string.wrongPassword)
        }
    }

    fun checkEmail(value: String, successful: () -> Unit) {
        val result = SignUtils.checkingEmail(value)
        if (result != null) state.value = SignState.Error(result)
        else successful()
    }

    fun checkPasswords(value1: String, value2: String, successful: () -> Unit) =
        viewModelScope.launch {
            val listResults = mutableListOf<Int?>()
            SignUtils.apply {
                listResults.add(checkPassword(value1))
                listResults.add(checkPassword(value2))
                listResults.add(isPasswordMatch(value1, value2))
            }
            withContext(Dispatchers.Main) {
                for (i in listResults) if (i != null) {
                    state.value = SignState.Error(i)
                    return@withContext
                }
                successful()
            }

        }

    fun signUp(email: String, name: String) = viewModelScope.launch {
        auth.createUserWithEmailAndPassword(email, name).addOnCompleteListener {
            if (it.isSuccessful) {

                viewModelScope.launch {
                    repository.updateUser(email, Uri.parse(name), {
                        viewModelScope.launch {
                            repository.verifyEmail(user!!, {
                                viewModelScope.launch(Dispatchers.Main) {
                                    state.value = SignState.Navigate
                                }
                            }, failed = { failed() })
                        }
                    }, {
                        failed()
                    })
                }


                state.value = SignState.Navigate
            } else {
                state.value = SignState.Error(R.string.error)
            }
        }
    }

    private fun failed() = viewModelScope.launch(Dispatchers.Main) {
        state.value = SignState.Error(R.string.error)
    }
}

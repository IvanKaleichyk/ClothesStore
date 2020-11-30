package com.koleychik.clothesstore.ui.states

sealed class SignState {

    object Waiting: SignState()
    class Error(val textRes: Int): SignState()
    class Checking(val email: String, val password: String): SignState()
    object Navigate : SignState()
}

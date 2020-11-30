package com.koleychik.clothesstore.ui.dialogs.states

sealed class DialogStateSamethingState{

    object Show : DialogStateSamethingState()
    object Loading : DialogStateSamethingState()
    class Error(val textRes: Int) : DialogStateSamethingState()

}

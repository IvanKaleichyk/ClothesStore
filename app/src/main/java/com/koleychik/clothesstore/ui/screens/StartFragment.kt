package com.koleychik.clothesstore.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.koleychik.clothesstore.R
import kotlinx.coroutines.*

class StartFragment : Fragment(R.layout.fragment_start) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            delay(1000)
            withContext(Dispatchers.Main) {
                val user = FirebaseAuth.getInstance().currentUser
                if (user == null) Navigation.findNavController(requireView())
                    .navigate(R.id.action_startFragment_to_navDrawerFragment)
                else Navigation.findNavController(requireView())
                    .navigate(R.id.action_startFragment_to_welcomeFragment)
            }
        }
    }
}
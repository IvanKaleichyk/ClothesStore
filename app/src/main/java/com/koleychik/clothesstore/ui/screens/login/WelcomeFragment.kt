package com.koleychik.clothesstore.ui.screens.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.koleychik.clothesstore.R
import com.koleychik.clothesstore.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {

    private lateinit var binding: FragmentWelcomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWelcomeBinding.inflate(layoutInflater)


        binding.textSignIn.setOnClickListener {
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_welcomeFragment_to_signInFragment)
        }

        binding.btnLogo.setOnClickListener {
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_welcomeFragment_to_emailAndNameFragment)
        }

        return binding.root
    }
}
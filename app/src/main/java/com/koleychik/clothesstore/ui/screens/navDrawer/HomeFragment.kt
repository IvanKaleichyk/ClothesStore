package com.koleychik.clothesstore.ui.screens.navDrawer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.koleychik.clothesstore.R
import com.koleychik.clothesstore.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding

    private val tagTest = "tagTest"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)

        if (savedInstanceState != null){
            binding.textText.text = savedInstanceState.getString(tagTest)
        }

        binding.testBtn.setOnClickListener {
            binding.textText.text = "2"
        }

        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(tagTest, "2")
    }
}
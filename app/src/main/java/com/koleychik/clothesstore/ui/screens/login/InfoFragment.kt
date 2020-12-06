package com.koleychik.clothesstore.ui.screens.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.koleychik.clothesstore.databinding.FragmentInfoBinding
import com.koleychik.clothesstore.utils.constants.InfoConstants
import com.koleychik.clothesstore.utils.navigation.navigation

class InfoFragment: Fragment() {

    private lateinit var binding: FragmentInfoBinding

    private var actionId = 0
    private var textId = 0
    private var titleId = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentInfoBinding.inflate(layoutInflater)

        tryGetArgs()
        setUI()
        binding.btn.setOnClickListener {
            if (actionId == -1) Navigation.findNavController(binding.root).popBackStack()
            else navigation(binding.root, actionId)
        }

        binding.textInfo.setText(textId)
        return binding.root
    }

    private fun setUI(){
        binding.textInfo.setText(textId)
        binding.title.setText(titleId)
        if (actionId == 0) binding.btn.visibility = View.GONE
    }

    private fun tryGetArgs(){
        actionId = requireArguments().getInt(InfoConstants.GET_ACTION_ID, 0)
        textId = requireArguments().getInt(InfoConstants.GET_TEXT_RES, 0)
        titleId = requireArguments().getInt(InfoConstants.GET_TITLE_RES, 0)
    }

}
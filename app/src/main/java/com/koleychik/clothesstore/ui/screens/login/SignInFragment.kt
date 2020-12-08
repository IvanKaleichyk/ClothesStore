package com.koleychik.clothesstore.ui.screens.login

import android.graphics.Paint
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.koleychik.clothesstore.App
import com.koleychik.clothesstore.R
import com.koleychik.clothesstore.databinding.FragmentSignBinding
import com.koleychik.clothesstore.ui.states.SignState
import com.koleychik.clothesstore.ui.viewModelFactory.MainViewModelFactory
import com.koleychik.clothesstore.ui.viewModels.SignViewModel
import com.koleychik.clothesstore.utils.navigation
import kotlinx.android.synthetic.main.fragment_sign.view.*
import javax.inject.Inject

class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignBinding

    private lateinit var viewModel: SignViewModel

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignBinding.inflate(layoutInflater)
        App.component.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[SignViewModel::class.java]

        setUI()
        subscribe()
        createOnClickListener()
        return binding.root
    }

    private fun subscribe() {
        viewModel.state.observe(viewLifecycleOwner, { render(it) })
    }

    private fun render(state: SignState) {
        binding.root.loading.isVisible = state is SignState.Checking
        binding.textWrong.isVisible = state is SignState.Error
        when (state) {
            is SignState.Waiting -> {
            }
            is SignState.Error -> binding.textWrong.setText(state.textRes)
            is SignState.Checking -> viewModel.signIn(state.email, state.password)
            is SignState.Navigate -> navigation(
                binding.root,
                R.id.action_signInFragment_to_navDrawerFragment
            )
        }
    }

    private fun createOnClickListener() {
        val onClickListener = View.OnClickListener {
            when (it.id) {
                R.id.btnContinue -> viewModel.state.value = SignState.Checking(
                    binding.editTextFirst.toString().trim(),
                    binding.editTextSecond.toString().trim()
                )
                R.id.setSign -> navigation(
                    binding.root,
                    R.id.action_signInFragment_to_emailAndNameFragment
                )
                R.id.forgotPassword -> navigation(
                    binding.root,
                    R.id.action_signInFragment_to_emailFragment
                )
            }
        }
        binding.btnContinue.setOnClickListener(onClickListener)
        binding.setSign.setOnClickListener(onClickListener)
        binding.forgotPassword.setOnClickListener(onClickListener)
    }

    private fun setUI() {
//        TextView
        binding.title.setText(R.string.sign_in)
        binding.descriptionFirst.setText(R.string.email)
        binding.descriptionSecond.setText(R.string.password)
//        EditText
        binding.editTextFirst.apply {
            val valueViewModel = viewModel.textFirst.value
            if (valueViewModel != null) setText(valueViewModel)
            else setHint(R.string.email)
            inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
        }
        binding.editTextSecond.apply {
            val valueViewModel = viewModel.textSecond.value
            if (valueViewModel != null) setText(valueViewModel)
            else setHint(R.string.password)
            inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
        }

        binding.forgotPassword.apply {
            visibility = View.VISIBLE
            paintFlags = this.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        }

        binding.setSign.apply {
            setText(R.string.sign_up)
            paintFlags = this.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        }
    }

    override fun onPause() {
        super.onPause()
        viewModel.textFirst.value = binding.editTextFirst.text.toString()
        viewModel.textSecond.value = binding.editTextSecond.text.toString()
    }
}
package com.koleychik.clothesstore.ui.screens.login.signUp

import android.graphics.Paint
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.koleychik.clothesstore.App
import com.koleychik.clothesstore.R
import com.koleychik.clothesstore.databinding.FragmentSignBinding
import com.koleychik.clothesstore.ui.states.SignState
import com.koleychik.clothesstore.ui.viewModelFactory.MainViewModelFactory
import com.koleychik.clothesstore.ui.viewModels.SignViewModel
import com.koleychik.clothesstore.utils.constants.InfoConstants
import com.koleychik.clothesstore.utils.constants.LogoConstant
import com.koleychik.clothesstore.utils.navigation.navigation
import kotlinx.android.synthetic.main.fragment_sign.view.*
import javax.inject.Inject

class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignBinding

    private lateinit var name: String
    private lateinit var email: String

    private lateinit var viewModel: SignViewModel

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignBinding.inflate(layoutInflater)
        App.component.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[SignViewModel::class.java]

        tryGetArgs()
        setUI()
        subscribe()
        createOnClickListener()
        return binding.root
    }

    private fun tryGetArgs() {
        name = requireArguments().getString(LogoConstant.nameBundle, "")
        email = requireArguments().getString(LogoConstant.emailBundle, "")
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
            is SignState.Checking -> {
                val name = binding.editTextFirst.text.toString().trim()
                val email = binding.editTextSecond.text.toString().trim()
                viewModel.checkPasswords(name, email) {
                    viewModel.signUp(name, email)
                }
            }
            is SignState.Navigate -> {
                val bundle = Bundle()
                bundle.putInt(InfoConstants.GET_TEXT_RES, R.string.verifyEmail)
                bundle.putInt(InfoConstants.GET_TITLE_RES, R.string.checkEmail)
                bundle.putInt(InfoConstants.GET_ACTION_ID, R.id.action_infoFragment_to_navDrawerFragment)
                navigation(binding.root, R.id.action_signUpFragment_to_infoFragment, bundle)
            }
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
                    R.id.action_signUpFragment_to_signInFragment
                )
            }
        }
        binding.btnContinue.setOnClickListener(onClickListener)
        binding.setSign.setOnClickListener(onClickListener)
    }

    private fun setUI() {
//        TextView
        binding.title.setText(R.string.sign_up)
        binding.descriptionFirst.setText(R.string.password)
        binding.descriptionSecond.setText(R.string.repeatPassword)
//        EditText
        binding.editTextFirst.apply {
            val valueViewModel = viewModel.textFirst.value
            if (valueViewModel != null) setText(valueViewModel)
            else setHint(R.string.password)
            inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
        }
        binding.editTextSecond.apply {
            val valueViewModel = viewModel.textSecond.value
            if (valueViewModel != null) setText(valueViewModel)
            else setHint(R.string.repeatPassword)
            inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
        }

        binding.forgotPassword.visibility = View.INVISIBLE
        binding.setSign.apply {
            setText(R.string.sign_in)
            paintFlags = this.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        }
    }

}
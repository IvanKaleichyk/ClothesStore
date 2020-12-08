package com.koleychik.clothesstore.ui.screens.login.forgotPassword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.koleychik.clothesstore.App
import com.koleychik.clothesstore.R
import com.koleychik.clothesstore.databinding.FragmentElementLoginBinding
import com.koleychik.clothesstore.ui.viewModelFactory.MainViewModelFactory
import com.koleychik.clothesstore.ui.viewModels.ElementLoginViewModel
import com.koleychik.clothesstore.utils.constants.InfoConstants
import com.koleychik.clothesstore.utils.navigation.navigation
import kotlinx.android.synthetic.main.fragment_element_login.view.*
import javax.inject.Inject

class EmailFragment : Fragment() {

    private lateinit var binding: FragmentElementLoginBinding
    private lateinit var viewModel: ElementLoginViewModel

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentElementLoginBinding.inflate(layoutInflater)
        App.component.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[ElementLoginViewModel::class.java]

        setUI()

        createOnClickListener()

        return binding.root
    }

    private fun createOnClickListener() {
        binding.btnContinue.setOnClickListener {
            binding.root.loading.visibility = View.VISIBLE
            FirebaseAuth.getInstance()
                .sendPasswordResetEmail(binding.editTextFirst.text.toString().trim())
                .addOnCompleteListener {
                    binding.root.loading.visibility = View.GONE
                    if (it.isSuccessful){
                        val bundle = Bundle()
                        bundle.putInt(InfoConstants.GET_TEXT_RES, R.string.verifyEmail)
                        bundle.putInt(InfoConstants.GET_TITLE_RES, R.string.checkEmail)
                        navigation(binding.root, R.id.action_emailFragment_to_infoFragment, bundle)
                    }
                    else{
                        Toast.makeText(binding.root.context, it.exception!!.message, Toast.LENGTH_LONG).show()
                    }
                }
        }
    }

    private fun setUI() {
        binding.title.setText(R.string.forgotPassword)
        binding.editTextFirst.setHint(R.string.email)
        binding.descriptionFirst.setText(R.string.email)
    }

    override fun onStart() {
        super.onStart()
        val textEdt = viewModel.text.value
        if (textEdt != null) binding.editTextFirst.setText(textEdt)
    }

    override fun onPause() {
        super.onPause()
        viewModel.text.value = binding.editTextFirst.text.toString()
    }
}
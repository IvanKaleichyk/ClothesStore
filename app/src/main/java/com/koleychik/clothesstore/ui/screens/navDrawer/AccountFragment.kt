package com.koleychik.clothesstore.ui.screens.navDrawer

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import coil.load
import com.koleychik.clothesstore.App
import com.koleychik.clothesstore.R
import com.koleychik.clothesstore.databinding.FragmentAccountBinding
import com.koleychik.clothesstore.ui.adapters.ImagesAdapter
import com.koleychik.clothesstore.ui.adapters.OnCLickToItem
import com.koleychik.clothesstore.ui.dialogs.DialogSetSomething
import com.koleychik.clothesstore.ui.states.ImageRvState
import com.koleychik.clothesstore.ui.viewModelFactory.MainViewModelFactory
import com.koleychik.clothesstore.ui.viewModels.AccountViewModel
import com.koleychik.clothesstore.utils.constants.Constants
import kotlinx.android.synthetic.main.fragment_account.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AccountFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    @Inject
    lateinit var dialog: DialogSetSomething

    @Inject
    lateinit var adapter: ImagesAdapter

    private val binding: FragmentAccountBinding by lazy {
        FragmentAccountBinding.inflate(layoutInflater)
    }
    private val viewModel: AccountViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[AccountViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        App.component.inject(this)
        dialog = DialogSetSomething(binding.root.context)

        subscribe()
        createRv()
        createOnCLickListener()
        return binding.root
    }

    private fun subscribe() {
        viewModel.stateImageRv.observe(viewLifecycleOwner, { render(it) })
        viewModel.listImages.observe(viewLifecycleOwner, {
            when {
                it == null -> {
                    viewModel.stateImageRv.value = ImageRvState.Loading
                    viewModel.getAllImages()
                }
                it.isEmpty() ->{
                    Log.d(Constants.TAG, "list is empty")
                    viewModel.stateImageRv.value = ImageRvState.Nothing
                }
                else -> {
                    Log.d(Constants.TAG, "image list size = ${it.size}")

                    adapter.submitList(it, object : OnCLickToItem {
                        override fun click(data: String) {
                            binding.img.load(data)
                        }
                    })
                    viewModel.stateImageRv.value = ImageRvState.Show
                }
            }
        })
        viewModel.isRvOpen.observe(viewLifecycleOwner, {
            if (it) binding.motionLayout.transitionToState(R.id.end)
            else binding.motionLayout.transitionToState(R.id.start)
        })
    }

    private fun render(state: ImageRvState) {
        binding.imageRv.isVisible = state is ImageRvState.Show
        binding.progressBar.isVisible = state is ImageRvState.Loading
        binding.textNothing.isVisible = state is ImageRvState.Nothing
    }

    private fun createOnCLickListener() {
        binding.img.setOnClickListener { viewModel.isRvOpen.value = !viewModel.isRvOpen.value!! }

        binding.setName.setOnClickListener {
            dialog.setTitle(binding.setName.text.toString())
            dialog.setOnCLickListenerDialog {
                lifecycleScope.launch(Dispatchers.IO) {
                    dialog.setName(binding.name)
                }
            }
        }
        binding.setEmail.setOnClickListener {
            dialog.setTitle(binding.setEmail.text.toString())
            dialog.setOnCLickListenerDialog {
                lifecycleScope.launch(Dispatchers.IO) {
                    dialog.setEmail()
                }
            }
        }
//        TODO -> ON CLICK TO SET PASSWORD -> --------------------
    }

    private fun createRv() {
        binding.imageRv.apply {
            this.adapter = adapter
            this.adapter = adapter
            setHasFixedSize(true)
        }
    }

}
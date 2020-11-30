package com.koleychik.clothesstore.ui.screens.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
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
import kotlinx.android.synthetic.main.fragment_account.view.*
import kotlinx.coroutines.CoroutineScope
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
                it.isEmpty() -> viewModel.stateImageRv.value = ImageRvState.Nothing
                else -> {
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
        binding.root.loading.isVisible = state is ImageRvState.Loading
        binding.textNothing.isVisible = state is ImageRvState.Nothing
    }

    private fun createOnCLickListener() {
        binding.img.setOnClickListener { viewModel.isRvOpen.value = !viewModel.isRvOpen.value!! }

        binding.setName.setOnClickListener {
            dialog.setTitle(binding.setName.text.toString())
            dialog.setOnCLickListener {
                CoroutineScope(Dispatchers.IO).launch {
                    dialog.setName()
                }
            }
        }
        binding.setName.setOnClickListener {
            dialog.setTitle(binding.setEmail.text.toString())
            dialog.setOnCLickListener {
                CoroutineScope(Dispatchers.IO).launch {
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
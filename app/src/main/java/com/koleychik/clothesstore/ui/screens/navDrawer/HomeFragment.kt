package com.koleychik.clothesstore.ui.screens.navDrawer

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.koleychik.clothesstore.App
import com.koleychik.clothesstore.databinding.FragmentHomeBinding
import com.koleychik.clothesstore.ui.adapters.CategoryAdapter
import com.koleychik.clothesstore.ui.states.HomeState
import com.koleychik.clothesstore.ui.viewModelFactory.MainViewModelFactory
import com.koleychik.clothesstore.ui.viewModels.HomeViewModel
import com.koleychik.clothesstore.utils.constants.Constants
import com.koleychik.clothesstore.utils.listCategory
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private lateinit var viewModel: HomeViewModel

    @Inject
    lateinit var adapter: CategoryAdapter

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)
        App.component.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]

        subscribe()
        createRv()
        createSwipeToRefresh()

        return binding.root
    }

    private fun subscribe() {
        viewModel.state.observe(viewLifecycleOwner, { render(it) })
    }

    private fun createRv() {
        binding.rv.adapter = adapter
    }
    private fun render(state: HomeState) {
        loading.isVisible = state is HomeState.Loading
        when (state) {
            is HomeState.Loading -> viewModel.getData(listCategory)
            is HomeState.Refreshing -> viewModel.getData(listCategory)
            is HomeState.Error -> {
                binding.textError.setText(state.textRes)
                binding.swipeToRefresh.isRefreshing = false
            }
            is HomeState.Show -> {
                Log.d(Constants.TAG, "state = HomeState.Show")
                binding.swipeToRefresh.isRefreshing = false
                adapter.submitList(state.lisCategory, state.mapListProducts)
            }
        }
        binding.rv.isVisible = state is HomeState.Show
        binding.textError.isVisible = state is HomeState.Error
    }

    private fun createSwipeToRefresh() {
        binding.swipeToRefresh.setOnRefreshListener {
            binding.swipeToRefresh.isRefreshing = true
            viewModel.state.value = HomeState.Refreshing
        }
    }

}
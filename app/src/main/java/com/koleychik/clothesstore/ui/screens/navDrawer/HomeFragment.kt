package com.koleychik.clothesstore.ui.screens.navDrawer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.koleychik.clothesstore.App
import com.koleychik.clothesstore.databinding.FragmentHomeBinding
import com.koleychik.clothesstore.ui.adapters.CategoryAdapter
import com.koleychik.clothesstore.ui.screens.NavDrawerFragmentDirections
import com.koleychik.clothesstore.ui.states.HomeState
import com.koleychik.clothesstore.ui.viewModelFactory.MainViewModelFactory
import com.koleychik.clothesstore.ui.viewModels.HomeViewModel
import com.koleychik.clothesstore.utils.listCategory
import com.koleychik.clothesstore.utils.navigation.SharedElementNavigation
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private lateinit var viewModel: HomeViewModel

    private val sharedElementNavigation = SharedElementNavigation()


    val adapter: CategoryAdapter by lazy {
        CategoryAdapter { img, model ->
            sharedElementNavigation.fromNavDrawerGoToProductFragment(img, model)
        }
    }

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
        createSwipeToRefresh()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createRv()
    }

    private fun subscribe() {
        viewModel.state.observe(viewLifecycleOwner, { render(it) })
    }

    private fun createRv() {
        binding.rv.adapter = adapter
    }
    private fun render(state: HomeState) {

        when (state) {
            is HomeState.Loading -> viewModel.getData(listCategory)
            is HomeState.Refreshing -> viewModel.getData(listCategory)
            is HomeState.Error -> {
                binding.textError.setText(state.textRes)
                binding.swipeToRefresh.isRefreshing = false
            }
            is HomeState.Show -> adapter.submitList(state.lisCategory, state.mapListProducts)
        }
        loading.isVisible = state is HomeState.Loading
        binding.swipeToRefresh.isRefreshing = state is HomeState.Refreshing
        binding.textError.isVisible = state is HomeState.Error
    }

    private fun createSwipeToRefresh() {
        binding.swipeToRefresh.setOnRefreshListener {
            viewModel.state.value = HomeState.Refreshing
        }
    }

}
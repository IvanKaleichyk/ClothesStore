package com.koleychik.clothesstore.ui.screens.navDrawer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.koleychik.clothesstore.App
import com.koleychik.clothesstore.databinding.FragmentBasketBinding
import com.koleychik.clothesstore.ui.adapters.BasketAdapter
import com.koleychik.clothesstore.ui.states.BasketState
import com.koleychik.clothesstore.ui.viewModelFactory.MainViewModelFactory
import com.koleychik.clothesstore.ui.viewModels.BasketViewModel
import com.koleychik.clothesstore.utils.ActiveModel
import javax.inject.Inject

class BasketFragment : Fragment() {

    private lateinit var binding : FragmentBasketBinding

    private lateinit var viewModel: BasketViewModel

    private lateinit var adapter: BasketAdapter

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    @Inject
    lateinit var activeModel: ActiveModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentBasketBinding.inflate(layoutInflater)
        App.component.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[BasketViewModel::class.java]
        adapter = BasketAdapter(activeModel) {
            viewModel.delete(it)
            adapter.delete(it)
        }

        createRv()
        subscribe()
        swipeToRefresh()

        return binding.root
    }

    private fun createRv(){
        binding.rv.adapter = adapter
    }

    private fun subscribe(){
        viewModel.state.observe(viewLifecycleOwner, {render(it)})
    }

    private fun render(state: BasketState){
        binding.progressBar.isVisible = state is BasketState.Loading
        binding.rv.isVisible = state is BasketState.Show
        binding.textNothing.isVisible = state is BasketState.Nothing
        when(state){
            is BasketState.Loading -> viewModel.getAll()
            is BasketState.Nothing -> binding.swipeToRefresh.isRefreshing = false
            is BasketState.Show -> {
                adapter.submitList(state.list)
                binding.swipeToRefresh.isRefreshing = false
            }
        }
    }

    private fun swipeToRefresh(){
        binding.swipeToRefresh.setOnRefreshListener {
            binding.swipeToRefresh.isRefreshing = true
            viewModel.state.value = BasketState.Loading
        }
    }

}
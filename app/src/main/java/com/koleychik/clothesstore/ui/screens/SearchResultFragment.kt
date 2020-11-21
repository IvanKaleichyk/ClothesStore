package com.koleychik.clothesstore.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.koleychik.clothesstore.databinding.FragmentSearchResoultBinding
import com.koleychik.clothesstore.models.categories.Category
import com.koleychik.clothesstore.ui.adapters.PagesAdapter
import com.koleychik.clothesstore.ui.adapters.ProductAdapter
import com.koleychik.clothesstore.ui.states.SearchResultState
import com.koleychik.clothesstore.ui.viewModelFactory.MainViewModelFactory
import com.koleychik.clothesstore.ui.viewModels.SearchResultViewModel
import com.koleychik.clothesstore.utils.Constants
import javax.inject.Inject

class SearchResultFragment : Fragment() {

    private lateinit var viewModel: SearchResultViewModel

    private var searchWord = ""
    private var mixPrice = 50
    private var maxPrice = 1000
    private lateinit var category: Category

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    @Inject
    lateinit var adapterProducts: ProductAdapter

    private lateinit var adapterPages: PagesAdapter

    private lateinit var binding: FragmentSearchResoultBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchResoultBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this, viewModelFactory)[SearchResultViewModel::class.java]

        tryGetArgs()
        createPageAdapter()
        createRvPages()
        createRv()

        subscribe()

        return binding.root
    }

    private fun subscribe() {
        viewModel.state.observe(viewLifecycleOwner, {
            if (it == null) viewModel.state.value =
                SearchResultState.Loading(searchWord, viewModel.pageNow.value!!)
            else render(it)
        })
        viewModel.totalPages.observe(viewLifecycleOwner, { adapterPages.submitList(it) })
    }

    private fun render(state: SearchResultState) {
        when (state) {
            is SearchResultState.Loading -> {
                binding.rv.visibility = View.GONE
                viewModel.search(
                    category = category,
                    optionsString = mapOf("query" to searchWord),
                    optionsInt = mapOf("page" to state.page, "per_page" to state.per_page)
                )
            }
            is SearchResultState.Nothing -> {
            }
            is SearchResultState.Error -> {
            }
            is SearchResultState.Showing -> {
                adapterProducts.submitList(state.list)
                binding.rv.visibility = View.VISIBLE
            }
        }
    }

    private fun createPageAdapter() {
        adapterPages = PagesAdapter(viewModel.pageNow.value!!) { page ->
            viewModel.pageNow.value = page
            viewModel.state.value = SearchResultState.Loading(searchWord, page)
        }
    }

    private fun createRvPages() {
        binding.rvPages.adapter = adapterPages
    }

    private fun createRv() {
        binding.rv.adapter = adapterProducts
    }

    private fun tryGetArgs() {
        requireArguments().apply {
            category = getParcelable(Constants.SEARCH_CATEGORY)!!
            maxPrice = getInt(Constants.SEARCH_PRICE_MAX, Constants.priceMax)
            mixPrice = getInt(Constants.SEARCH_PRICE_MIN, Constants.priceMin)
            searchWord = getString(Constants.SEARCH_TEXT, "")
        }
    }
}
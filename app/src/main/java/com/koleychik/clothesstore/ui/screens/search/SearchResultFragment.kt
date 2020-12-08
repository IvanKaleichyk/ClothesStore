package com.koleychik.clothesstore.ui.screens.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.koleychik.clothesstore.App
import com.koleychik.clothesstore.R
import com.koleychik.clothesstore.databinding.FragmentSearchResoultBinding
import com.koleychik.clothesstore.models.categories.Category
import com.koleychik.clothesstore.ui.adapters.paging.productPagingAdapter.ProductLoadStateAdapter
import com.koleychik.clothesstore.ui.adapters.paging.productPagingAdapter.ProductPagingAdapter
import com.koleychik.clothesstore.ui.states.SearchResultState
import com.koleychik.clothesstore.ui.viewModelFactory.MainViewModelFactory
import com.koleychik.clothesstore.ui.viewModels.SearchResultViewModel
import com.koleychik.clothesstore.utils.constants.Constants
import com.koleychik.clothesstore.utils.getCategoryById
import com.koleychik.clothesstore.utils.navigation.SharedElementNavigation
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchResultFragment : Fragment() {

    private lateinit var viewModel: SearchResultViewModel

    private var searchWord = ""
    private var minPrice = 50
    private var maxPrice = 1000
    private lateinit var category: Category

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    @Inject
    lateinit var sharedElementNavigation: SharedElementNavigation

    private val adapterProducts by lazy {
        ProductPagingAdapter(
            onCLick = { img, model ->
                sharedElementNavigation.fromSearchResultToProductFragment(img, model)
            },
            onSetState = { isUp ->
                if (isUp) binding.motionLayout.transitionToState(R.id.start)
                else binding.motionLayout.transitionToState(R.id.end)
            }
        )
    }

    private lateinit var binding: FragmentSearchResoultBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchResoultBinding.inflate(layoutInflater)
        App.component.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[SearchResultViewModel::class.java]

        tryGetArgs()
        subscribe()
        createRv()
        createFabIcon()

        return binding.root
    }

    private fun subscribe() {
        viewModel.state.observe(viewLifecycleOwner, {
            if (it == null) viewModel.state.value =
                SearchResultState.Loading(searchWord, viewModel.pageNow.value!!)
            else render(it)
        })
    }

    private fun render(state: SearchResultState) {

        when (state) {
            is SearchResultState.Loading -> {
                Log.d(Constants.TAG, "startLoading")
                lifecycleScope.launch {
                    viewModel.products(
                        categoryId = category.getId(),
                        optionsString = mapOf("query" to searchWord),
                        per_pager = 30,
                        priceMin = minPrice,
                        priceMax = maxPrice
                    ).collectLatest { adapterProducts.submitData(it) }
                }
                viewModel.state.value = SearchResultState.Showing
            }
            is SearchResultState.Nothing -> {
            }
            is SearchResultState.Error -> {
                Toast.makeText(requireContext(), state.errorText, Toast.LENGTH_LONG).show()
            }
            is SearchResultState.Showing -> {
            }
        }
    }

    private fun createRv() {
        adapterProducts.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading) {
                binding.progressBar.visibility = View.VISIBLE
                binding.rv.visibility = View.GONE
            } else {
                binding.progressBar.visibility = View.GONE
                binding.rv.visibility = View.VISIBLE
                val errorState = when {
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }
                errorState?.let {
                    viewModel.state.value = SearchResultState.Error(it.error.message!!)
                }
            }
        }

        binding.rv.apply {
            setHasFixedSize(true)
            adapter =
                adapterProducts.withLoadStateFooter(ProductLoadStateAdapter { adapterProducts.refresh() })
        }

    }

    private fun createFabIcon() {
        binding.up.setOnClickListener {
            binding.rv.scrollToPosition(0)
        }
    }

    private fun tryGetArgs() {
        requireArguments().apply {
            category = getCategoryById(getInt(Constants.SEARCH_CATEGORY))
            maxPrice = getInt(Constants.SEARCH_PRICE_MAX, Constants.priceMax)
            minPrice = getInt(Constants.SEARCH_PRICE_MIN, Constants.priceMin)
            searchWord = getString(Constants.SEARCH_TEXT, "")
        }

        binding.textToolbar.text = searchWord
    }
}
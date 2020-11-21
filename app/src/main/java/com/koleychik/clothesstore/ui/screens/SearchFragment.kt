package com.koleychik.clothesstore.ui.screens

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.koleychik.clothesstore.App
import com.koleychik.clothesstore.R
import com.koleychik.clothesstore.callbacks.OnClickToHistoryModel
import com.koleychik.clothesstore.databinding.FragmentSearchBinding
import com.koleychik.clothesstore.models.HistoryModel
import com.koleychik.clothesstore.ui.adapters.HistoryAdapter
import com.koleychik.clothesstore.ui.states.SearchState
import com.koleychik.clothesstore.ui.viewModelFactory.MainViewModelFactory
import com.koleychik.clothesstore.ui.viewModels.SearchViewModel
import com.koleychik.clothesstore.utils.Constants
import com.koleychik.clothesstore.utils.getListFromListResources
import com.koleychik.clothesstore.utils.getNamesResource
import com.koleychik.clothesstore.utils.startSearching
import java.util.*
import javax.inject.Inject

class SearchFragment : Fragment() {

    lateinit var viewModel: SearchViewModel

    private val keyState = "keyState"

    @Inject
    lateinit var adapter: HistoryAdapter

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater)
        App.component.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[SearchViewModel::class.java]

        createSpinner()
        createRv()
        subscribe()

        return binding.root
    }

    private fun createSpinner() {
        val arrayAdapter = ArrayAdapter(
            requireContext(),
            R.layout.item_spinner,
            ArrayList(getListFromListResources(requireContext(), getNamesResource()))
        )
        binding.spinnerCategory.apply {
            this.adapter = arrayAdapter
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    viewModel.valueSpinnerCategory.value = p2
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {}
            }
        }
    }

    private fun createRv() {
        adapter.setOnClick(object : OnClickToHistoryModel {
            override fun click(model: HistoryModel) {
                model.time = Date().time
//               delete last model
                adapter.deleteModel(model)
//                and insert new model with new time
                viewModel.insert(model)
                adapter.addToList(model)
                startSearching(
                    root = binding.root,
                    textSearch = model.text,
                    category = model.category as Parcelable,
                    minPrice = model.minPrice,
                    maxPrice = model.maxPrice
                )
            }
        })
        binding.rv.adapter = adapter
    }

    private fun subscribe() {
        viewModel.valueSpinnerCategory.observe(viewLifecycleOwner, {
            binding.spinnerCategory.setSelection(it)
        })
        viewModel.state.observe(viewLifecycleOwner, { render(it) })
    }

    private fun render(state: SearchState) {
        when (state) {
            is SearchState.Loading -> loading()
            is SearchState.ShowResult -> show(state.list)
        }
    }

    private fun loading() {
        viewModel.getList()
    }

    private fun show(list: List<HistoryModel>) {
        adapter.submitList(list)
    }
}
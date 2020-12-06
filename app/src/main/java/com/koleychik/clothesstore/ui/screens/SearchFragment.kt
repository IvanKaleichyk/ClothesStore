package com.koleychik.clothesstore.ui.screens

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
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
import com.koleychik.clothesstore.utils.*
import com.koleychik.clothesstore.utils.constants.Constants
import com.koleychik.clothesstore.utils.navigation.startSearching
import java.util.*
import javax.inject.Inject

class SearchFragment : Fragment() {

    lateinit var viewModel: SearchViewModel

    private val TAG_SEARCH_WORD = "TAG_SEARCH_WORD"

    @Inject
    lateinit var adapter: HistoryAdapter

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        saves: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater)
        App.component.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[SearchViewModel::class.java]

        createSpinner()
        createRv()
        createOnClickListener()
        createEditText()
        subscribe()

//        if (saves != null) binding.editTextSearch.setText(saves.getString(TAG_SEARCH_WORD))

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
                    binding.root,
                    model.text,
                    model.categoryId,
                    model.minPrice,
                    model.maxPrice
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

    private fun createOnClickListener() {
        binding.startSearch.setOnClickListener {
            val textSearch = binding.editTextSearch.text.toString().trim()
            if (textSearch != "") {

                var minPrice = 50
                var maxPrice = 1000
                try {
                    minPrice = binding.minPriceText.text.toString().toInt()
                    maxPrice = binding.maxPriceText.text.toString().toInt()
                } catch (e: NumberFormatException) {
                    Log.d(Constants.TAG, "error min and max price")
                }
                if (checkPrice(minPrice, maxPrice)) {
                    val categoryId = binding.spinnerCategory.selectedItemPosition
                    viewModel.insert(
                        generateHistoryModel(
                            textSearch,
                            categoryId,
                            minPrice,
                            maxPrice
                        )
                    )
                    startSearching(
                        Navigation.findNavController(binding.root),
                        textSearch,
                        categoryId,
                        minPrice,
                        maxPrice
                    )
                }
            } else Navigation.findNavController(binding.root).popBackStack()
        }
    }

    private fun checkPrice(minValue: Int, maxValue: Int): Boolean {
        val res = checkMinAndMaxPrice(minValue, maxValue)
        return if (res == null) true
        else {
            Toast.makeText(requireContext(), requireContext().getString(res), Toast.LENGTH_LONG)
                .show()
            false
        }
    }

    private fun createEditText() {
        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                setIconSearch(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }
        binding.editTextSearch.addTextChangedListener(textWatcher)
    }

    fun setIconSearch(newText: String) {
        val text = newText.trim()
        if (text == "") binding.startSearch.setImageResource(R.drawable.close_icon_32)
        else binding.startSearch.setImageResource(R.drawable.search_icon_32)
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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
//        outState.putString(TAG_SEARCH_WORD, binding.editTextSearch.text.toString())
    }
}
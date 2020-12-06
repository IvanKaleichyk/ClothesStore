package com.koleychik.clothesstore.ui.screens

import android.graphics.Paint
import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.doOnNextLayout
import androidx.core.view.doOnPreDraw
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import coil.load
import com.koleychik.clothesstore.App
import com.koleychik.clothesstore.R
import com.koleychik.clothesstore.databinding.FragmentProductBinding
import com.koleychik.clothesstore.models.ProductModel
import com.koleychik.clothesstore.ui.viewModelFactory.MainViewModelFactory
import com.koleychik.clothesstore.ui.viewModels.ProductViewModel
import com.koleychik.clothesstore.utils.*
import com.koleychik.clothesstore.utils.constants.Constants
import com.koleychik.clothesstore.utils.constants.ProductConstants
import com.koleychik.clothesstore.utils.navigation.SharedElementNavigation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductFragment : Fragment() {

    private lateinit var viewModel: ProductViewModel

    private lateinit var binding: FragmentProductBinding

    private lateinit var checkBoxStyle: CheckBoxStyle

    private val sharedElementNavigation = SharedElementNavigation(this)

//    @Inject
//    lateinit var activeModel: ActiveModel

    private lateinit var model: ProductModel

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    val args by lazy {
        ProductFragmentArgs.fromBundle(requireArguments())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductBinding.inflate(layoutInflater)
        App.component.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[ProductViewModel::class.java]

//        sharedElementNavigation.prepareSharedElementTransition(binding.root)

        tryGetArgs()
//        lifecycleScope.launch {
//            viewModel.checkValueInDb(model.id)
//            withContext(Dispatchers.Main) {
//                setUI()
//            }
//        }

        createCheckBoxStyle()
        createOnClickListener()
        subscribe()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val animation =
            TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
        sharedElementEnterTransition = animation
        sharedElementReturnTransition = animation
        binding.img.transitionName = args.transitionName
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }
        setUI()
    }

    private fun setUI() {
//        val model = activeModel.model ?: return
        binding.textCategory.setText(getCategoryById(model.categoryId).getResourceName())
        binding.fullPrice.text = getCurrencyString(model.price)
        binding.img.load(model.photo.urls.regular)
        if (model.sale == null) {
            binding.salePrice.visibility = View.GONE
        } else {
            binding.fullPrice.paintFlags =
                binding.fullPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            binding.salePrice.text = getCurrencyString(model.sale!!)
        }
        binding.description.text = model.photo.description
    }

    private fun subscribe() {
        viewModel.isInBasket.observe(viewLifecycleOwner, {

            if (it) {
                binding.btnAdd.setText(R.string.buy)
                binding.btnAdd.setOnClickListener {

                }
//            TODO IF IN BASKET THAN SET BTN BG AND TEXT, setOnCLick listener to go to buy
            } else {
                binding.btnAdd.setOnClickListener {
                    viewModel.insertInBasket(
                        generateBasketModel(
                            model,
                            checkBoxStyle.getChecked()[0].text.toString(),
                            true
                        )
                    )
                    viewModel.isInBasket.value = true
                    model.isInBasket = true
                }
            }
        })
        viewModel.selectSize.observe(viewLifecycleOwner, {
            if (it == null) viewModel.selectSize.value = binding.checkBox1.id
            else checkBoxStyle.setStyle(it)
        })
    }

    private fun createCheckBoxStyle() {
        val list = mutableListOf(binding.checkBox1, binding.checkBox2, binding.checkBox3)
        val listSelect = mutableListOf(viewModel.selectSize.value ?: binding.checkBox1.id)
        checkBoxStyle = CheckBoxStyle(list, listSelect, isSingle = true, canBeNothingSelect = false)
    }

    private fun tryGetArgs() {
        viewModel.isInBasket.value = args.isFromBasket
        model = args.productModel
//        viewModel.isInBasket.value = requireArguments().getBoolean(ProductConstants.comeFromBasket)
//        model = requireArguments().getParcelable(ProductConstants.model)!!
    }

    private fun createOnClickListener() {
        val onCLickToCheckBox = View.OnClickListener {
            checkBoxStyle.setStyle(it as TextView)
        }
        binding.checkBox1.setOnClickListener(onCLickToCheckBox)
        binding.checkBox2.setOnClickListener(onCLickToCheckBox)
        binding.checkBox3.setOnClickListener(onCLickToCheckBox)

        binding.btnAdd.setOnClickListener {
            Navigation.findNavController(binding.root).popBackStack()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
//        activeModel.model = null
    }
}
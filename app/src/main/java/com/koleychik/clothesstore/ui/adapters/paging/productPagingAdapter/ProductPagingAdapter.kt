package com.koleychik.clothesstore.ui.adapters.paging.productPagingAdapter

import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.NavController
import androidx.navigation.get
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.clear
import coil.load
import com.koleychik.clothesstore.R
import com.koleychik.clothesstore.models.ProductModel
import com.koleychik.clothesstore.ui.adapters.callbacks.productCallback
import com.koleychik.clothesstore.utils.constants.Constants
import com.koleychik.clothesstore.utils.getCurrencyString
import kotlinx.android.synthetic.main.item_rv_product.view.*
import kotlinx.coroutines.*
import java.util.*
import javax.inject.Inject

class ProductPagingAdapter @Inject constructor(
    private val onCLick: (img: ImageView, model: ProductModel) -> Unit,
    private val onSetState: ((isUp: Boolean) -> Unit)? = null,
) :
    PagingDataAdapter<ProductModel, ProductPagingAdapter.MainViewHolder>(
        productCallback
    ) {

    var lastItemPosition = 0
    var wasUp = false
    var lastTime = 0L

    init {
        timerToOpenHeader()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MainViewHolder(
            layoutInflater.inflate(R.layout.item_rv_product_search, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val model = getItem(position)!!

        if (onSetState != null) {
            if (position > lastItemPosition) {
                if (wasUp) {
                    onSetState!!(false)
                    wasUp = false
                }
            } else if (!wasUp) {
                onSetState!!(true)
                wasUp = true
            }
        }
//        Log.d(Constants.TAG, "nowTime - lastTime = ${nowTime - lastTime}")
        lastTime = Date().time
        lastItemPosition = position
        holder.bind(model)

    }

    override fun onViewRecycled(holder: MainViewHolder) {
        super.onViewRecycled(holder)
        holder.itemView.img.clear()
    }

    private fun timerToOpenHeader() = CoroutineScope(Dispatchers.Default).launch {
        while (true) {
            val nowTimer = Date().time
            if (nowTimer - lastTime > 1500) {
                withContext(Dispatchers.Main) { onSetState!!(true) }
                wasUp = true
            }
            delay(500)
        }
    }

    inner class MainViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(model: ProductModel) {
            itemView.description.text = model.photo.description
            itemView.fullPrice.text = getCurrencyString(model.price)

            itemView.img.apply {
                load(model.photo.urls.regular)
                transitionName = model.transitionName
            }

            setOnClickListener(model)
            if (model.sale != null) {
//                CROSS OUT
                itemView.fullPrice.paintFlags =
                    itemView.fullPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

                itemView.salePrice.visibility = View.VISIBLE
                itemView.salePrice.text = getCurrencyString(model.sale!!)
            } else {
//                MAKE ACTIVE AGAIN
                itemView.fullPrice.paintFlags =
                    itemView.fullPrice.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()

                itemView.salePrice.visibility = View.GONE
            }
        }

        private fun setOnClickListener(model: ProductModel) {
            itemView.setOnClickListener {
                onCLick(itemView.img, model)
//                val navController = Navigation.findNavController(itemView)
//                val idAction = getIdAction(navController)
//                val bundle = Bundle()
//                bundle.putBoolean(ProductConstants.comeFromAnother, true)
//                navController.navigate(idAction, bundle)
            }
        }

        private fun getIdAction(navController: NavController): Int {
            with(navController) {
                return when (currentDestination) {
                    graph[R.id.searchResultFragment] -> R.id.action_searchResultFragment_to_productFragment
                    graph[R.id.navDrawerFragment] -> R.id.action_navDrawerFragment_to_productFragment
                    else -> R.id.action_searchResultFragment_to_productFragment
                }
            }
        }
    }
}
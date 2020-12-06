package com.koleychik.clothesstore.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SortedList
import androidx.recyclerview.widget.SortedListAdapterCallback
import com.koleychik.clothesstore.R
import com.koleychik.clothesstore.models.ProductModel
import com.koleychik.clothesstore.models.categories.Category
import com.koleychik.clothesstore.utils.ActiveModel
import com.koleychik.clothesstore.utils.adaptersHelpers.CategoryAdapterHelper
import kotlinx.android.synthetic.main.item_rv_category.view.*
import javax.inject.Inject

class CategoryAdapter @Inject constructor(private val onClick: (imageView: ImageView, model : ProductModel) -> Unit) :
    RecyclerView.Adapter<CategoryAdapter.MainViewHolder>() {

    private val sortedList = SortedList(Category::class.java,
        object : SortedListAdapterCallback<Category>(this) {
            override fun compare(o1: Category, o2: Category): Int = 1

            override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean =
                oldItem.getId() == newItem.getId()

            override fun areItemsTheSame(item1: Category, item2: Category): Boolean =
                item1 == item2
        })

    private val listCategoryId = mutableListOf<Int>()

    private val helper: CategoryAdapterHelper by lazy {
        CategoryAdapterHelper(sortedList, listCategoryId = listCategoryId)
    }

    var mainMap = mapOf<Int, List<ProductModel>>()

    init {
        sortedList
    }

    fun submitList(newList: List<Category>, map: Map<Int, List<ProductModel>>) {
        mainMap = map
        sortedList.clear()
        for (i in newList) helper.addToList(i)
    }

    fun updateList(newList: List<Category>, map: Map<Int, List<ProductModel>>) {
        mainMap = map
        for (i in newList) if (!listCategoryId.contains(i.getId())) helper.addToList(i)
        helper.removeUnusedItems(newList)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return MainViewHolder(
            layoutInflater.inflate(R.layout.item_rv_category, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(sortedList[position])
    }

    override fun getItemCount(): Int = sortedList.size()

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val adapter = ProductAdapter(onClick)

        fun bind(model: Category) {
            itemView.title.setText(model.getResourceName())
            itemView.icon.setImageResource(model.getImageResource())
            createAdapter(model)
        }

        private fun createAdapter(model: Category) {
            itemView.rv.adapter = adapter
            val list = mainMap[model.getId()]
            if (list == null) itemView.visibility = View.GONE
            else {
                if (adapter.isSortedListEmpty()) adapter.submitList(list)
                else (adapter.updateList(list))
                itemView.visibility = View.VISIBLE
            }
        }

    }

}
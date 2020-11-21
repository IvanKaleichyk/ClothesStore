package com.koleychik.clothesstore.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SortedList
import androidx.recyclerview.widget.SortedListAdapterCallback
import com.koleychik.clothesstore.R
import com.koleychik.clothesstore.databinding.ItemRvCategoryBinding
import com.koleychik.clothesstore.models.ProductModel
import com.koleychik.clothesstore.models.categories.Category
import javax.inject.Inject

class CategoryAdapter @Inject constructor() : RecyclerView.Adapter<CategoryAdapter.MainViewHolder>() {

    private val sortedList: SortedList<Category>

     var mainMap = mapOf<Int, List<ProductModel>>()

    init {
        sortedList = SortedList(
            Category::class.java,
            object : SortedListAdapterCallback<Category>(this) {
                override fun compare(o1: Category, o2: Category): Int = 1

                override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean =
                    oldItem.getId() == newItem.getId()

                override fun areItemsTheSame(item1: Category, item2: Category): Boolean =
                    item1 == item2
            })
    }

    fun submitList(newList: List<Category>, map: Map<Int, List<ProductModel>>) {
        mainMap = map
        sortedList.clear()
        sortedList.addAll(newList)
    }

    fun addToList(value: Category) {
        sortedList.add(value)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return MainViewHolder(
            layoutInflater.inflate(R.layout.item_rv_category, parent, false),
            ItemRvCategoryBinding.inflate(layoutInflater)
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(sortedList[position])
    }

    override fun getItemCount(): Int = sortedList.size()

    inner class MainViewHolder(itemView: View, private val binding: ItemRvCategoryBinding) : RecyclerView.ViewHolder(itemView) {

        @Inject
        lateinit var adapter: ProductAdapter

        fun bind(model: Category) {
            binding.title.setText(model.getResourceName())
            binding.icon.setImageResource(model.getImageResource())
            createAdapter(model)
        }

        private fun createAdapter(model : Category){
            binding.rv.adapter = adapter
            val list = mainMap[model.getId()]
            if (list == null) itemView.visibility = View.GONE
            else {
                adapter.submitList(list)
                itemView.visibility = View.VISIBLE
            }
        }

    }

}
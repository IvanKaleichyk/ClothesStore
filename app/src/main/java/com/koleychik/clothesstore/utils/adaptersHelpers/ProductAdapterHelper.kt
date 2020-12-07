package com.koleychik.clothesstore.utils.adaptersHelpers

import androidx.recyclerview.widget.SortedList
import com.koleychik.clothesstore.models.ProductModel

class ProductAdapterHelper(
    private val sortedList: SortedList<ProductModel>,
    private val listCategoryId: MutableList<Int>
) {

    fun addToList(model: ProductModel) {
        sortedList.add(model)
        listCategoryId.add(model.id)
    }

    fun removeUnusedItems(newList: List<ProductModel>) {
        val listToDelete = mutableListOf<ProductModel>()
        val setId = getSetFromList(newList)
        for (i in listCategoryId) if (!setId.contains(i)) listToDelete.add(getModelById(i))
        for (i in listToDelete) remove(i)
    }

    fun remove(model: ProductModel) {
        sortedList.remove(model)
        listCategoryId.remove(model.id)
    }

    fun getModelById(id: Int): ProductModel{
        for (i in (0 until sortedList.size())) if (sortedList[i].id == id) return sortedList[i]
        return sortedList[0]
    }

    fun getSetFromList(list: List<ProductModel>): Set<Int> {
        val set = mutableSetOf<Int>()
        for (i in list) set.add(i.id)
        return set
    }

}
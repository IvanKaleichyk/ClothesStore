package com.koleychik.clothesstore.utils.adaptersHelpers

import androidx.recyclerview.widget.SortedList
import com.koleychik.clothesstore.models.ProductModel
import com.koleychik.clothesstore.models.categories.Category
import javax.inject.Inject

class ProductAdapterHelper @Inject constructor(
    private val sortedList: SortedList<ProductModel>,
    private val listCategoryId: MutableList<Int>
) {

    private fun createSetFromListCategory(newList: List<ProductModel>): Set<Int> {
        val setRes = mutableSetOf<Int>()
        for (i in newList) setRes.add(i.id)
        return setRes
    }

    fun removeUnusedItems(newList: List<ProductModel>) {
        val setIdNewList = createSetFromListCategory(newList)
        val listToDelete = mutableListOf<Int>()
        for (i in listCategoryId) if (!setIdNewList.contains(i)) listToDelete.add(i)
        for (i in listToDelete) remove(i)
    }

    private fun remove(modelId: Int) {
        var indexSelectView = -1
        for (i in (0 until sortedList.size())) if (sortedList[i].id == modelId) {
            indexSelectView = i
            break
        }
        if (indexSelectView != -1) sortedList.removeItemAt(indexSelectView)
        listCategoryId.remove(modelId)
    }

    fun addToList(value: ProductModel) {
        sortedList.add(value)
        listCategoryId.add(value.id)
    }


}
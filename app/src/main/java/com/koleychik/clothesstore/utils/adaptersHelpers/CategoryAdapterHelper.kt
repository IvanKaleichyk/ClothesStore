package com.koleychik.clothesstore.utils.adaptersHelpers

import androidx.recyclerview.widget.SortedList
import com.koleychik.clothesstore.models.categories.Category
import javax.inject.Inject

class CategoryAdapterHelper @Inject constructor(
    private val sortedList: SortedList<Category>,
    private val listCategoryId: MutableList<Int>
) {

    private fun createSetFromListCategory(newList: List<Category>): Set<Int> {
        val setRes = mutableSetOf<Int>()
        for (i in newList) setRes.add(i.getId())
        return setRes
    }

    fun removeUnusedItems(newList: List<Category>) {
        val setIdNewList = createSetFromListCategory(newList)
        for (i in listCategoryId) if (!setIdNewList.contains(i)) remove(i)
    }

    private fun remove(modelId: Int) {
        var indexSelectView = -1
        for (i in (0 until sortedList.size()))
            if (sortedList[i].getId() == modelId) {
                indexSelectView = i
                break
            }
        if (indexSelectView != -1) sortedList.removeItemAt(indexSelectView)
        listCategoryId.remove(modelId)
    }

    fun addToList(value: Category) {
        sortedList.add(value)
        listCategoryId.add(value.getId())
    }


}
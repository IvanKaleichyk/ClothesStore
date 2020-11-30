package com.koleychik.clothesstore.utils

import android.widget.TextView
import androidx.core.content.ContextCompat
import com.koleychik.clothesstore.R
import org.w3c.dom.Text

class CheckBoxStyle(
    private val list: MutableList<TextView>,
    private var checkedListId: MutableList<Int>,
    private val isSingle: Boolean = true,
    private val canBeNothingSelect: Boolean = false
) {

    private val checkedList = mutableListOf<TextView>()

    init {
        for (i in checkedListId) getCheckBoxFromId(i)?.let { checkedList.add(it) }
        for (i in checkedList) {
            setStyle(i)
        }
    }

    //    val nowCheckedIndex : textView
    fun getChecked() = checkedList

    fun addToList(value: TextView) = list.add(value)

    fun setChecked(newChecked: TextView) {
        if (isSingle) if (checkedList.isNotEmpty()) clearChecked(checkedList[0])
        checkedList.add(newChecked)

        setUI(newChecked, R.drawable.bg_check_box_checked, R.color.colorWhite)
    }

    fun setChecked(newCheckedId: Int) {
        val newChecked = getCheckBoxFromId(newCheckedId) ?: return
        if (isSingle) if (checkedList.isNotEmpty()) clearChecked(checkedList[0])
        checkedList.add(newChecked)

        setUI(newChecked, R.drawable.bg_check_box_checked, R.color.colorWhite)
    }

    fun setStyle(newChecked: TextView) {
        if (checkedList.contains(newChecked)) {
            if (canBeNothingSelect) clearChecked(newChecked)
        } else setChecked(newChecked)
    }

    fun setStyle(newCheckedId: Int) {
        val newChecked = getCheckBoxFromId(newCheckedId) ?: return
        if (checkedList.contains(newChecked)) {
            if (canBeNothingSelect) clearChecked(newChecked)
        } else setChecked(newChecked)
    }

    fun clearChecked(textView: TextView) {
        checkedList.remove(textView)

        setUI(textView, R.drawable.bg_check_box_unchecked, R.color.colorBlack)
    }

    private fun setUI(textView: TextView, bgId: Int, colorId: Int) {
        textView.setBackgroundResource(bgId)
        textView.setTextColor(ContextCompat.getColor(textView.context, colorId))
    }

    private fun getCheckBoxFromId(id: Int): TextView? {
        for (i in list) if (i.id == id) return i
        return null
    }

}
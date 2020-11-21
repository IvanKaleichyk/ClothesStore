package com.koleychik.clothesstore.utils

import android.widget.TextView
import androidx.core.content.ContextCompat
import com.koleychik.clothesstore.R

class CheckBoxStyle(
    private val list: MutableList<TextView>,
    private var checkedList: MutableList<TextView>,
    private val isSingle: Boolean = true,
    private val canBeNothingSelect: Boolean = false
) {

    init {
        for (i in checkedList) {
            setChecked(i)
        }
    }

//    val nowCheckedIndex : textView

    fun addToList(value : TextView) = list.add(value)

    fun setChecked(newChecked: TextView) {
        if (isSingle) if (checkedList.isNotEmpty()) clearChecked(checkedList[0])
        checkedList.add(newChecked)

        setUI(newChecked, R.drawable.bg_check_box_checked, R.color.colorWhite)
    }

    fun setStyle(newChecked: TextView) {
        if (list.contains(newChecked)) {
            if (canBeNothingSelect) clearChecked(newChecked)
        } else setChecked(newChecked)
    }

    fun clearChecked(textView: TextView) {
        checkedList.remove(textView)

        setUI(textView, R.drawable.bg_check_box_unchecked, R.color.colorBlack)
    }

    private fun setUI(textView: TextView, bgId : Int, colorId : Int){
        textView.setBackgroundResource(bgId)
        textView.setTextColor(ContextCompat.getColor(textView.context, colorId))
    }
}
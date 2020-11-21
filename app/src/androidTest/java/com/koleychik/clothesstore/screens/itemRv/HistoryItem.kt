package com.koleychik.clothesstore.screens.itemRv

import android.view.View
import com.agoda.kakao.image.KImageView
import com.agoda.kakao.recycler.KRecyclerItem
import com.agoda.kakao.text.KTextView
import com.koleychik.clothesstore.R
import org.hamcrest.Matcher

class HistoryItem (parent: Matcher<View>) : KRecyclerItem<HistoryItem>(parent) {
    val text = KTextView(parent) { withId(R.id.text) }
}
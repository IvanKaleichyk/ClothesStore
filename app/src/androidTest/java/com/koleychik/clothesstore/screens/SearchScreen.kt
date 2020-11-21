package com.koleychik.clothesstore.screens

import com.agoda.kakao.recycler.KRecyclerView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KTextView
import com.koleychik.clothesstore.R
import com.koleychik.clothesstore.screens.itemRv.HistoryItem

class SearchScreen : Screen<SearchScreen>() {

    val rv = KRecyclerView({ withId(R.id.rv) }, itemTypeBuilder = { itemType(::HistoryItem) })
    val title = KTextView{withId(R.id.title)}
}
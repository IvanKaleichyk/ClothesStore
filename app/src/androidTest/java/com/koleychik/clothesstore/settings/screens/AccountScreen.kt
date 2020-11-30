package com.koleychik.clothesstore.settings.screens

import com.agoda.kakao.image.KImageView
import com.agoda.kakao.recycler.KRecyclerView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KTextView
import com.koleychik.clothesstore.R
import com.koleychik.clothesstore.settings.screens.items_rv.ItemRvImage

class AccountScreen : Screen<AccountScreen>() {

    val img = KImageView { withId(R.id.img) }

    val name = KTextView { withId(R.id.name) }
    val setName = KTextView { withId(R.id.setName) }
    val setPassword = KTextView { withId(R.id.setPassword) }
    val setEmail = KTextView { withId(R.id.setEmail) }

    val textNothing = KTextView { withId(R.id.textNothing) }
    val rvImages =
        KRecyclerView({ withId(R.id.imageRv) }, itemTypeBuilder = { itemType(::ItemRvImage) })
}
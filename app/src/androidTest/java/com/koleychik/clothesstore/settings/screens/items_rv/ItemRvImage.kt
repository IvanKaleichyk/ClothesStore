package com.koleychik.clothesstore.settings.screens.items_rv

import android.view.View
import com.agoda.kakao.image.KImageView
import com.agoda.kakao.recycler.KRecyclerItem
import com.koleychik.clothesstore.R
import org.hamcrest.Matcher

class ItemRvImage(parent: Matcher<View>) : KRecyclerItem<ItemRvImage>(parent) {
    val img = KImageView(parent) { withId(R.id.img) }
}

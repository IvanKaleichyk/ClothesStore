package com.koleychik.clothesstore.screens

import com.agoda.kakao.edit.KEditText
import com.agoda.kakao.image.KImageView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KTextView
import com.koleychik.clothesstore.R
import com.koleychik.clothesstore.ui.screens.activities.MainActivity

class MainActivityScreen : Screen<MainActivityScreen>() {

    val goToSearchActivity = KImageView{withId(R.id.goToSearchActivity)}
    val editTextSearch = KEditText{withId(R.id.editTextSearch)}
    val startSearch = KImageView{withId(R.id.startSearch)}
}
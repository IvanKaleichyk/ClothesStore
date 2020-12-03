package com.koleychik.clothesstore.screens

import com.agoda.kakao.common.views.KView
import com.agoda.kakao.screen.Screen
import com.koleychik.clothesstore.R

class LoadingScreen : Screen<LoadingScreen>() {

    val bg = KView { withId(R.id.layoutBgLoading) }
    val progressBar = KView { withId(R.id.progressBarDialog) }

}
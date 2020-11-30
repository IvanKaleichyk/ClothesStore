package com.koleychik.clothesstore.login.screens

import com.agoda.kakao.edit.KEditText
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KButton
import com.agoda.kakao.text.KTextView
import com.koleychik.clothesstore.R

class ElementLoginScreen : Screen<ElementLoginScreen>() {

    val title = KTextView { withId(R.id.title) }
    val descriptionFirst = KTextView { withId(R.id.descriptionFirst) }
    val editTextFirst = KEditText { withId(R.id.editTextFirst) }
    val btnContinue = KButton { withId(R.id.btnContinue) }

}
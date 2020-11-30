package com.koleychik.clothesstore.login.screens

import com.agoda.kakao.edit.KEditText
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KButton
import com.agoda.kakao.text.KTextView
import com.koleychik.clothesstore.R

class SignScreen: Screen<SignScreen>() {

    val title = KTextView { withId(R.id.title) }
    val descriptionFirst = KTextView { withId(R.id.descriptionFirst) }
    val descriptionSecond = KTextView { withId(R.id.descriptionSecond) }
    val setSign = KTextView { withId(R.id.setSign) }
    val forgotPassword = KTextView { withId(R.id.forgotPassword) }
    val textWrong = KTextView { withId(R.id.textWrong) }

    val editTextFirst = KEditText { withId(R.id.editTextFirst) }
    val editTextSecond = KEditText { withId(R.id.editTextSecond) }

    val btnContinue = KButton { withId(R.id.btnContinue) }
}
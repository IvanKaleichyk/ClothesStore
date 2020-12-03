package com.koleychik.clothesstore.screens

import com.agoda.kakao.edit.KEditText
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KButton
import com.agoda.kakao.text.KTextView
import com.koleychik.clothesstore.R

class DialogSetSomethingScreen : Screen<DialogSetSomethingScreen>() {

    val title = KTextView { withId(R.id.title) }
    val editText = KEditText { withId(R.id.editText) }
    val btnDialogSet = KButton {
        withId(R.id.btn)
        withText(R.string.set)
    }

}
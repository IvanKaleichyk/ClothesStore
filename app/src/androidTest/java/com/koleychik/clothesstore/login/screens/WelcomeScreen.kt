package com.koleychik.clothesstore.login.screens

import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KTextView
import com.koleychik.clothesstore.R

class WelcomeScreen : Screen<WelcomeScreen>() {

    val text = KTextView{withId(R.id.text)}
    val btnSignUp = KTextView{withId(R.id.btnLogo)}
    val textSignIn = KTextView{withId(R.id.textSignIn)}
}
package com.koleychik.clothesstore.login.tests

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.koleychik.clothesstore.R
import com.koleychik.clothesstore.launchFragment
import com.koleychik.clothesstore.login.screens.WelcomeScreen
import com.koleychik.clothesstore.ui.screens.activities.MainActivity
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WelcomeFragmentTest {

    @get:Rule
    val rule = ActivityTestRule(MainActivity::class.java)

    val screen = WelcomeScreen()

    @Before
    fun launch() {
        launchFragment(rule, R.id.welcomeFragment, null)
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Assert.assertEquals("com.koleychik.clothesstore", appContext.packageName)
    }

    @Test
    fun testScreen() {
        screen {
            text {
                isVisible()
                hasText(R.string.welcomeText)
            }
            textSignIn {
                isVisible()
                isEnabled()
                isClickable()
                hasText(R.string.sign_in)
            }
            btnSignUp {
                isVisible()
                isEnabled()
                isClickable()
                hasAnyText()
                hasText(R.string.create_account)
//                hasBackgroundColor(R.drawable.logo_btn_bg)
            }
        }
    }

}
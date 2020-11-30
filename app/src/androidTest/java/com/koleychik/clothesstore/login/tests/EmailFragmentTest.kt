package com.koleychik.clothesstore.login.tests

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.koleychik.clothesstore.R
import com.koleychik.clothesstore.launchFragment
import com.koleychik.clothesstore.login.screens.SignScreen
import com.koleychik.clothesstore.ui.screens.activities.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EmailFragmentTest {

    @get:Rule
    val rule = ActivityTestRule(MainActivity::class.java)

    val screen = SignScreen()

    @Before
    fun launch() {
        launchFragment(rule, R.id.emailFragment, null)
    }

    @Test
    fun testUI(){
        screen{
            title{
                isVisible()
                hasText(R.string.forgotPassword)
            }
            descriptionFirst{
                isVisible()
                hasText(R.string.email)
            }
            editTextFirst{
                isVisible()
                hasHint(R.string.email)
                typeText("wndjhwd@gmail.com")
            }
            btnContinue{
                isVisible()
                isEnabled()
                isClickable()
                hasText(R.string.continue_main)
            }
        }
    }

}
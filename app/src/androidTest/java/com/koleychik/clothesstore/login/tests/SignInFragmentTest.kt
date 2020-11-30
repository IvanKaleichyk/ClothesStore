package com.koleychik.clothesstore.login.tests

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.agoda.kakao.screen.Screen.Companion.idle
import com.koleychik.clothesstore.R
import com.koleychik.clothesstore.launchFragment
import com.koleychik.clothesstore.login.screens.SignScreen
import com.koleychik.clothesstore.ui.screens.activities.MainActivity
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SignInFragmentTest {

    @get:Rule
    val rule = ActivityTestRule(MainActivity::class.java)

    val screen = SignScreen()

    @Before
    fun launch() {
        launchFragment(rule, R.id.signInFragment, null)
    }

    @Test
    fun testTextWrong() {
        screen {
            editTextFirst.typeText("email@gmai.com")
            editTextSecond.typeText("uhkhfhkdfh")
            closeSoftKeyboard()
            btnContinue {
                click()
            }
            idle(1000)
            textWrong {
                isVisible()
                hasText(R.string.wrongPassword)
            }
        }
    }


    @Test
    fun testAnother() {
        screen {
            editTextFirst {
                isVisible()
                hasHint(R.string.email)
                typeText("wndjhwd@gmail.com")
            }
            editTextSecond {
                isVisible()
                hasHint(R.string.password)
                typeText("wndjhcom")
            }
            btnContinue {
                isVisible()
                isEnabled()
                isClickable()
                hasText(R.string.continue_main)
            }
        }
    }

    @Test
    fun testTextView() {
        screen {
            title {
                isVisible()
                hasText(R.string.sign_in)
            }
            descriptionFirst {
                isVisible()
                hasText(R.string.email)
            }
            descriptionSecond {
                isVisible()
                hasText(R.string.password)
            }
            setSign {
                isVisible()
                isEnabled()
                isClickable()
                hasText(R.string.sign_up)
            }
            forgotPassword {
                isVisible()
                isEnabled()
                isClickable()
                hasText(R.string.forgotPassword)
            }
        }
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Assert.assertEquals("com.koleychik.clothesstore", appContext.packageName)
    }

}
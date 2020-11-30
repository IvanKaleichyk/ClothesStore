package com.koleychik.clothesstore.login.tests

import android.os.Bundle
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.agoda.kakao.screen.Screen
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
class SignUpFragmentTest {

    @get:Rule
    val rule = ActivityTestRule(MainActivity::class.java)

    val screen = SignScreen()

    @Before
    fun launch() {
//        val bundle = Bundle()
        launchFragment(rule, R.id.signUpFragment, null)
    }

    @Test
    fun testTextWrong(){
        screen{
            editTextFirst.typeText("email")
            editTextSecond.typeText("uhkhfhkdfh")
            closeSoftKeyboard()
            btnContinue.click()
            Screen.idle(1000)
            textWrong{
                isVisible()
                hasText(R.string.passwordNotMatch)
            }
        }
    }

    @Test
    fun testAnother(){
        screen{
            editTextFirst{
                isVisible()
                hasHint(R.string.password)
                typeText("ndkdjd")
            }
            editTextSecond{
                isVisible()
                hasHint(R.string.repeatPassword)
                typeText("ndkdjd")
            }
            btnContinue{
                isVisible()
                isEnabled()
                isClickable()
                hasText(R.string.continue_main)
            }
        }
    }


    @Test
    fun testTextView() {
        screen{
            title{
                isVisible()
                hasText(R.string.sign_up)
            }
            descriptionFirst{
                isVisible()
                hasText(R.string.password)
            }
            descriptionSecond{
                isVisible()
                hasText(R.string.repeatPassword)
            }
            setSign{
                isVisible()
                isEnabled()
                isClickable()
                hasText(R.string.sign_in)
            }
            forgotPassword{
                isInvisible()
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
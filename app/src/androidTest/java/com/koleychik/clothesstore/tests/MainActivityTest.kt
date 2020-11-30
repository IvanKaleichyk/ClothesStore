package com.koleychik.clothesstore.tests

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.koleychik.clothesstore.R
import com.koleychik.clothesstore.screens.MainActivityScreen
import com.koleychik.clothesstore.ui.screens.activities.MainActivity
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val rule = ActivityTestRule(MainActivity::class.java)

    val screen = MainActivityScreen()

//    @Test
//    fun useAppContext() {
//        // Context of the app under test.
//        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
//        Assert.assertEquals("com.koleychik.clothesstore", appContext.packageName)
//    }

    @Test
    fun testToolbar(){
        screen{
            goToSearchActivity.isVisible()
            editTextSearch.isInvisible()
            startSearch.isInvisible()

            goToSearchActivity.click()
            editTextSearch{
                isVisible()
                hasEmptyText()
            }
            startSearch{
                isVisible()
//                click()
//                Screen.idle(1000)
                hasDrawable(R.drawable.close_icon_32)
            }
            editTextSearch.typeText("hello")
            startSearch.hasDrawable(R.drawable.search_icon_32)
        }
    }

    private fun useAppContext(): Context {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Assert.assertEquals("com.koleychik.nasaapi", appContext.packageName)
        return appContext
    }

}
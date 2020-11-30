package com.koleychik.clothesstore.login.tests

import android.os.Bundle
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.koleychik.clothesstore.R
import com.koleychik.clothesstore.launchFragment
import com.koleychik.clothesstore.login.screens.InfoScreen
import com.koleychik.clothesstore.ui.screens.activities.MainActivity
import com.koleychik.clothesstore.utils.constants.InfoConstants
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class InfoFragmentTest {

    @get:Rule
    val rule = ActivityTestRule(MainActivity::class.java)

    val screen = InfoScreen()

    private val actionId = R.id.action_infoFragment_to_navDrawerFragment
    private val textId = R.string.verifyEmail
    private val titleId = R.string.checkEmail

    @Before
    fun launch() {
        val bundle = Bundle()
        bundle.putInt(InfoConstants.GET_ACTION_ID, actionId)
        bundle.putInt(InfoConstants.GET_TITLE_RES, titleId)
        bundle.putInt(InfoConstants.GET_TEXT_RES, textId)
        launchFragment(rule, R.id.infoFragment, bundle)
    }

    @Test
    fun testUI(){
        screen{
            title{
                isVisible()
                hasText(titleId)
            }
            descriptionFirst{
                isVisible()
                hasText(textId)
            }
            btn{
                isVisible()
                isEnabled()
                isClickable()
                hasText(R.string.continue_main)
            }
        }
    }

}
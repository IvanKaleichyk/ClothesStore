package com.koleychik.clothesstore.settings.tests

import android.util.Log
import androidx.test.espresso.Espresso
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.agoda.kakao.screen.Screen.Companion.idle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.koleychik.clothesstore.R
import com.koleychik.clothesstore.TestUser
import com.koleychik.clothesstore.launchFragment
import com.koleychik.clothesstore.repositories.DeviceImagesRepository
import com.koleychik.clothesstore.screens.DialogSetSomethingScreen
import com.koleychik.clothesstore.screens.LoadingScreen
import com.koleychik.clothesstore.settings.screens.AccountScreen
import com.koleychik.clothesstore.ui.screens.activities.MainActivity
import com.koleychik.clothesstore.utils.constants.Constants
import org.junit.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AccountFragmentTest : TestCase() {

    @get:Rule
    val rule = ActivityTestRule(MainActivity::class.java)

    val screen = AccountScreen()
    val screenDialog = DialogSetSomethingScreen()
    val screenLoading = LoadingScreen()

    private lateinit var userMain: FirebaseUser

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    val testUser = TestUser(context)

    init {
        testUser.getUserModel {
            userMain = it
        }
    }

    @Before
    fun launch() {
        launchFragment(rule, R.id.accountFragment, null)
    }

    @Test
    fun testDialog() = run {
        step("test dialog set email") {
            screen.setEmail.click()
            val textEmail = "testEmail@gmail.com"
            val title = R.string.set_email
            testDialog(title, textEmail)
        }
        val name = "Ivan"
        step("test dialog set name"){
            screen.setName.click()
            val title = R.string.set_name
            testDialog(title, name)
        }
//        step("test name after changes"){
//            screen.name{
//                isVisible()
//                hasText(name)
//            }
//        }
//        step("check new user") {
////            assertThat(FirebaseAuth.getInstance().currentUser?.email).isEqualTo(textEmail)
//            Assert.assertEquals(FirebaseAuth.getInstance().currentUser!!.email, textEmail)
//            testUser.setEmail(textEmail)
//        }

    }

    fun testDialog(title: Int, typeText: String) {
        screenDialog {
            title {
                isVisible()
                hasText(title)
            }
            editText {
                isVisible()
                hasHint(title)
                typeText(typeText)
                screen.closeSoftKeyboard()
            }
            btnDialogSet {
                isVisible()
                isEnabled()
                hasText(R.string.set)
//                click()
            }
            editText.clearText()
            pressBack()
        }
    }

    @Test
    fun testTextView() {
        screen {
            name {
                isVisible()
                val name = FirebaseAuth.getInstance().currentUser?.displayName
                if (name != null) hasText(name)
            }
            setName {
                isVisible()
                hasText(R.string.set_name)
                isEnabled()
            }
            setEmail {
                isVisible()
                hasText(R.string.set_email)
                isEnabled()
            }
            setPassword {
                isVisible()
                hasText(R.string.set_password)
                isEnabled()
            }
        }
    }

    @Test
    fun testImagesRv() {
        val imagesList = DeviceImagesRepository(context).getAll() ?: listOf()
        Log.d(Constants.TAG, "imageList.size = ${imagesList.size}")
        screen {
            img.click()

            screenLoading {
                bg.isVisible()
                progressBar.isVisible()
            }
            img.click()
            idle(800)
            rvImages.isInvisible()
        }
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Assert.assertEquals("com.koleychik.clothesstore", appContext.packageName)
    }

}
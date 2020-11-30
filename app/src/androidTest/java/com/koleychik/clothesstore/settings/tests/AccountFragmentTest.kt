package com.koleychik.clothesstore.settings.tests

import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.agoda.kakao.screen.Screen.Companion.idle
import com.google.firebase.auth.FirebaseAuth
import com.koleychik.clothesstore.App
import com.koleychik.clothesstore.R
import com.koleychik.clothesstore.di.components.DaggerAppComponent
import com.koleychik.clothesstore.di.modules.AppModule
import com.koleychik.clothesstore.launchFragment
import com.koleychik.clothesstore.repositories.DeviceImagesRepository
import com.koleychik.clothesstore.settings.screens.AccountScreen
import com.koleychik.clothesstore.settings.screens.items_rv.ItemRvImage
import com.koleychik.clothesstore.ui.screens.activities.MainActivity
import com.koleychik.clothesstore.utils.constants.Constants
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.InputStream

@RunWith(AndroidJUnit4::class)
class AccountFragmentTest {

    @get:Rule
    val rule = ActivityTestRule(MainActivity::class.java)

    val screen = AccountScreen()

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun launch() {
        launchFragment(rule, R.id.accountFragment, null)
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
//                isClickable()
            }
            setEmail {
                isVisible()
                hasText(R.string.set_email)
                isEnabled()
//                isClickable()
            }
            setPassword {
                isVisible()
                hasText(R.string.set_password)
                isEnabled()
//                isClickable()
            }
        }
    }

    @Test
    fun testImagesRv() {
        val imagesList = DeviceImagesRepository(context).getAll() ?: listOf()
        Log.d(Constants.TAG, "imageList.size = ${imagesList.size}")
        screen {
            img.click()
            idle(1000)
            if (imagesList.isEmpty()) screen.textNothing.isVisible()
            else {
                rvImages {
                    isVisible()
                    for (i in imagesList.indices step 2) {
                        val uri = Uri.parse(imagesList[i].data)
                        val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
                        childAt<ItemRvImage>(i) {
                            img {
                                isVisible()
                                isEnabled()
                                isClickable()
                                if (inputStream != null) {
                                    hasDrawable(
                                        Drawable.createFromStream(
                                            inputStream,
                                            uri.toString()
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
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
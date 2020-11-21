package com.koleychik.clothesstore.tests

import android.support.test.rule.ActivityTestRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.agoda.kakao.screen.Screen
import com.koleychik.clothesstore.App
import com.koleychik.clothesstore.R
import com.koleychik.clothesstore.di.components.DaggerAppComponent
import com.koleychik.clothesstore.launchFragment
import com.koleychik.clothesstore.models.HistoryModel
import com.koleychik.clothesstore.repositories.HistoryRepository
import com.koleychik.clothesstore.screens.SearchScreen
import com.koleychik.clothesstore.screens.itemRv.HistoryItem
import com.koleychik.clothesstore.ui.screens.activities.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
class SearchFragmentTest {

    @get:Rule
    val rule = ActivityTestRule(MainActivity::class.java)

    val screen = SearchScreen()

    init {
        App.component.inject(rule.activity)
    }

    @Inject
    lateinit var repository: HistoryRepository


    @Test
    fun testAll(){
        launchFragment(rule, R.id.searchFragment, null)

        screen{
            title{
                isVisible()
                hasText(R.string.history)
            }
            rv{
                isVisible()

                var list = listOf<HistoryModel>()
                CoroutineScope(Dispatchers.IO).launch {
                    list = repository.getAll()
                }
                Screen.idle(1000)

                for ((num, i) in list.withIndex()) {
                    childAt<HistoryItem>(num) {
                        text{
                            isVisible()
                            hasText(i.text)
                        }
                    }
                }
            }
        }
    }

}
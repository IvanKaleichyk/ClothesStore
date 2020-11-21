package com.koleychik.clothesstore.ui.viewPager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.koleychik.clothesstore.R
//
//class ViewPagerFragment : Fragment() {
//
//    private lateinit var root: View
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//
//        root = inflater.inflate(R.layout.fragment_view_pager, container, false)
//
//        makeViewPager()
//        makeBottomNavBar()
//
//        return root
//    }
//
//    private fun makeViewPager() {
//        val adapter = ViewPagerAdapter(
//            list = getListFragment(),
//            fm = requireActivity().supportFragmentManager,
//            lifecycle = lifecycle
//        )
//
//        root.viewPager.adapter = adapter
//
//        root.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
//            override fun onPageSelected(position: Int) {
//                super.onPageSelected(position)
//                when (position) {
//                    0 -> root.bottomNavBar.menu.findItem(R.id.searchPeople).isChecked = true
//                    1 -> root.bottomNavBar.menu.findItem(R.id.messages).isChecked = true
//                }
//            }
//        })
//    }
//
//    private fun getListFragment() = mutableListOf(SearchFragment(), MessagesFragment())
//
//    private fun makeBottomNavBar() {
//        root.bottomNavBar.setOnNavigationItemSelectedListener {
//            when (it.itemId) {
//                R.id.searchPeople -> {
//                    root.viewPager.currentItem = 0
//                    true
//                }
//                R.id.messages -> {
//                    root.viewPager.currentItem = 1
//                    true
//                }
//                else -> false
//            }
//        }
//    }
//
//}
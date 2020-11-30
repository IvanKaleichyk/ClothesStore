package com.koleychik.clothesstore.ui.screens

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import coil.load
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.koleychik.clothesstore.App
import com.koleychik.clothesstore.R
import com.koleychik.clothesstore.databinding.FragmentNavDrawerBinding
import com.koleychik.clothesstore.databinding.NavHeaderMainBinding
import com.koleychik.clothesstore.ui.screens.activities.MainActivity
import com.koleychik.clothesstore.ui.screens.navDrawer.BasketFragment
import com.koleychik.clothesstore.ui.screens.navDrawer.HomeFragment
import com.koleychik.clothesstore.ui.screens.navDrawer.SettingsFragment
import com.koleychik.clothesstore.ui.screens.settings.AccountFragment
import com.koleychik.clothesstore.ui.viewModelFactory.MainViewModelFactory
import com.koleychik.clothesstore.ui.viewModels.MainViewModel
import com.koleychik.clothesstore.ui.viewPager.ViewPagerAdapter
import javax.inject.Inject

class NavDrawerFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

//    private lateinit var appBarConfiguration: AppBarConfiguration

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    private lateinit var bindingContentMain: FragmentNavDrawerBinding
    private lateinit var bindingHeader: NavHeaderMainBinding
    private lateinit var drawerToggle: ActionBarDrawerToggle

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingContentMain = FragmentNavDrawerBinding.inflate(layoutInflater)
        bindingHeader = NavHeaderMainBinding.inflate(layoutInflater)
        App.component.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        (requireActivity() as MainActivity).setSupportActionBar(bindingContentMain.toolbar)

        createNavDrawer()
        createViewPager()

        createOnClickListener()

        FirebaseAuth.getInstance().currentUser.let {
            if (it != null) {
                setUserInfo(it)
            }
        }

        return bindingContentMain.root
    }

    private fun createOnClickListener() {
        bindingContentMain.goToSearchActivity.setOnClickListener {
            Navigation.findNavController(bindingContentMain.root)
                .navigate(R.id.searchFragment)
        }
    }

    private fun createNavDrawer() {
        (requireActivity() as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setupDrawerContent(bindingContentMain.navView)

        drawerToggle = setupDrawerToggle()

        // Setup toggle to display hamburger icon with nice animation
        drawerToggle.isDrawerIndicatorEnabled = true
        drawerToggle.syncState()

        // Tie DrawerLayout events to the ActionBarToggle
        drawerToggle.let { bindingContentMain.drawerLayout.addDrawerListener(it) }
    }

    private fun setupDrawerToggle(): ActionBarDrawerToggle {
        // NOTE: Make sure you pass in a valid toolbar reference.  ActionBarDrawToggle() does not require it
        // and will not render the hamburger icon without it.
        return ActionBarDrawerToggle(
            requireActivity(),
            bindingContentMain.drawerLayout,
            bindingContentMain.toolbar,
            R.string.app_name,
            R.string.accessories
        )
    }

    override fun onStart() {
        super.onStart()
        drawerToggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        drawerToggle.onConfigurationChanged(newConfig)
    }

    private fun setupDrawerContent(navigationView: NavigationView) {
        navigationView.setNavigationItemSelectedListener { menuItem ->
            selectDrawerItem(menuItem)
            true
        }
    }

    private fun selectDrawerItem(menuItem: MenuItem) {
        removeColorItem(bindingContentMain.navView)

        // Create a new fragment and specify the fragment to show based on nav item clicked
        when (menuItem.itemId) {
            R.id.homeFragment -> bindingContentMain.viewPager.currentItem = 0
            R.id.basketFragment -> bindingContentMain.viewPager.currentItem = 1
            R.id.accountFragment -> bindingContentMain.viewPager.currentItem = 2
            R.id.support -> bindingContentMain.viewPager.currentItem = 3
        }

        // Highlight the selected item has been done by NavigationView
        menuItem.isChecked = true
        requireActivity().title = menuItem.title
        bindingContentMain.drawerLayout.closeDrawers()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // The action bar home/up action should open or close the drawer.
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun removeColorItem(view: NavigationView) {
        for (i in 0 until view.menu.size()) {
            val item = view.menu.getItem(i)
            item.isChecked = false
        }
    }

    private fun setUserInfo(user: FirebaseUser) {
        bindingHeader.name.text = user.displayName
        user.photoUrl.let { bindingHeader.profileImage.load(it)}
    }

    private fun createViewPager() {
        val adapter = ViewPagerAdapter(
            getListFragment(),
            childFragmentManager,
            lifecycle
        )
        bindingContentMain.viewPager.isUserInputEnabled = false
        bindingContentMain.viewPager.adapter = adapter
    }

    private fun getListFragment() = listOf(
        HomeFragment(), BasketFragment(), AccountFragment(),SettingsFragment()
    )

}
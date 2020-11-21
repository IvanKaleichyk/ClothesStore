package com.koleychik.clothesstore.ui.screens

import android.content.res.Configuration
import android.os.Bundle
import android.os.Parcelable
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import com.google.android.material.navigation.NavigationView
import com.koleychik.clothesstore.App
import com.koleychik.clothesstore.R
import com.koleychik.clothesstore.databinding.FragmentNavDrawerBinding
import com.koleychik.clothesstore.databinding.FragmentSearchBinding
import com.koleychik.clothesstore.ui.screens.activities.MainActivity
import com.koleychik.clothesstore.ui.screens.navDrawer.BasketFragment
import com.koleychik.clothesstore.ui.screens.navDrawer.FavoritesFragment
import com.koleychik.clothesstore.ui.screens.navDrawer.HomeFragment
import com.koleychik.clothesstore.ui.screens.navDrawer.SettingsFragment
import com.koleychik.clothesstore.ui.viewModelFactory.MainViewModelFactory
import com.koleychik.clothesstore.ui.viewModels.MainViewModel
import com.koleychik.clothesstore.ui.viewPager.ViewPagerAdapter
import com.koleychik.clothesstore.utils.checkMinAndMaxPrice
import com.koleychik.clothesstore.utils.generateHistoryModel
import com.koleychik.clothesstore.utils.getCategoryById
import com.koleychik.clothesstore.utils.startSearching
import kotlinx.android.synthetic.main.fragment_nav_drawer.*
import javax.inject.Inject

class NavDrawerFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    private lateinit var appBarConfiguration: AppBarConfiguration

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    private lateinit var bindingContentMain: FragmentNavDrawerBinding
    private lateinit var bindingSearchFragment: FragmentSearchBinding
    lateinit var drawerToggle: ActionBarDrawerToggle

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingContentMain = FragmentNavDrawerBinding.inflate(layoutInflater)
        bindingSearchFragment = FragmentSearchBinding.inflate(layoutInflater)
//        setContentView(bindingContentMain.root)
        App.component.inject(this)

        (requireActivity() as MainActivity).setSupportActionBar(bindingContentMain.toolbar)

        createNavDrawer()
        createViewPager()

        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        createOnClickListener()

        createEditText()
        return bindingContentMain.root
    }

    private fun createOnClickListener() {
        val onClickListener = View.OnClickListener {
            when (it.id) {
                R.id.goToSearchActivity -> {
                    bindingContentMain.motionLayout.transitionToState(R.id.end)
                    try {
                        setIconSearch(bindingContentMain.editTextSearch.text.toString())
                        Navigation.findNavController(bindingContentMain.root)
                            .navigate(R.id.searchFragment)
                    } catch (e: Exception) {
                    }
//                    navigation(binding.navHost, R.id.action_startFragment_to_searchFragment)
                }
                R.id.startSearch -> {
                    val textSearch = bindingContentMain.editTextSearch.text.toString().trim()
                    if (textSearch != "") {

                        var minPrice = 50
                        var maxPrice = 1000
                        try {
                            minPrice = bindingSearchFragment.minPriceText.text.toString().toInt()
                            maxPrice = bindingSearchFragment.maxPriceText.text.toString().toInt()
                        } catch (e: NumberFormatException) {
                        }
                        if (checkPrice(minPrice, maxPrice)) {
                            val category = getCategoryById(bindingSearchFragment.spinnerCategory.selectedItemPosition)
                            viewModel.insert(generateHistoryModel(textSearch, category, minPrice, maxPrice))
                            startSearching(
                                Navigation.findNavController(bindingContentMain.root),
                                textSearch,
                                category as Parcelable,
                                minPrice,
                                maxPrice
                            )
                        }
                    } else Navigation.findNavController(bindingContentMain.root).popBackStack()
                    bindingContentMain.motionLayout.transitionToState(R.id.start)
                }
            }
        }

        bindingContentMain.goToSearchActivity.setOnClickListener(onClickListener)
        bindingContentMain.startSearch.setOnClickListener(onClickListener)
    }

    private fun checkPrice(minValue: Int, maxValue: Int): Boolean {
        val res = checkMinAndMaxPrice(minValue, maxValue)
        return if (res == null) true
        else {
            Toast.makeText(requireContext(), requireContext().getString(res), Toast.LENGTH_LONG)
                .show()
            false
        }
    }

    private fun createEditText() {
        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                setIconSearch(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }
        bindingContentMain.editTextSearch.addTextChangedListener(textWatcher)
    }

    fun setIconSearch(newText: String) {
        val text = newText.trim()
        if (text == "") bindingContentMain.startSearch.setImageResource(R.drawable.close_icon_32)
        else bindingContentMain.startSearch.setImageResource(R.drawable.search_icon_32)
    }

    private fun createNavDrawer() {
        (requireActivity() as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setupDrawerContent(bindingContentMain.navView)

        drawerToggle = setupDrawerToggle();

        // Setup toggle to display hamburger icon with nice animation
        drawerToggle.isDrawerIndicatorEnabled = true;
        drawerToggle.syncState();

        // Tie DrawerLayout events to the ActionBarToggle
        drawerToggle.let { bindingContentMain.drawerLayout.addDrawerListener(it) }

        if (bindingContentMain.navView.headerCount > 0) {
            // avoid NPE by first checking if there is at least one Header View available
            val headerLayout: View = bindingContentMain.navView.getHeaderView(0)
        }
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
            R.id.favoritesFragment -> bindingContentMain.viewPager.currentItem = 2
            R.id.settingsFragment -> bindingContentMain.viewPager.currentItem = 3
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

    private fun createViewPager() {
        val adapter = ViewPagerAdapter(
            getListFragment(),
            requireActivity().supportFragmentManager,
            lifecycle
        )
        bindingContentMain.viewPager.isUserInputEnabled = false
        bindingContentMain.viewPager.adapter = adapter
    }

    private fun getListFragment() = listOf(
        HomeFragment(), BasketFragment(), FavoritesFragment(), SettingsFragment()
    )

}
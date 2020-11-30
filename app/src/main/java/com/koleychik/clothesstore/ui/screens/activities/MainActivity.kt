package com.koleychik.clothesstore.ui.screens.activities

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.koleychik.clothesstore.databinding.ActivityMainBinding
import com.koleychik.clothesstore.databinding.AppBarMainBinding
import com.koleychik.clothesstore.ui.viewModelFactory.MainViewModelFactory
import com.koleychik.clothesstore.ui.viewModels.MainViewModel
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

//    var isOpen = false

    private lateinit var appBarConfiguration: AppBarConfiguration

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    private lateinit var bindingContentMain: ActivityMainBinding
    private lateinit var bindingToolbar: AppBarMainBinding


    lateinit var drawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingContentMain = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingContentMain.root)
//        App.component.inject(this)
//
//        setSupportActionBar(bindingContentMain.toolbar)
//
//        createNavDrawer()
//        createViewPager()
//
//        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
//        createOnClickListener()

//        createEditText()
    }
//
//    private fun createOnClickListener() {
//        val onClickListener = View.OnClickListener {
//            when (it.id) {
//                R.id.goToSearchActivity -> {
//                    motion_layout.transitionToState(R.id.end)
//                    try {
//                        setIconSearch(editTextSearch.text.toString())
////                        navController.navigate(R.id.searchFragment) TODO
//                    } catch (e: Exception) {
//                    }
////                    navigation(binding.navHost, R.id.action_startFragment_to_searchFragment)
//                }
//                R.id.startSearch -> {
//                    val textSearch = editTextSearch.text.toString().trim()
//                    if (textSearch != "") {
//                        viewModel.insert(generateHistoryModel(textSearch))
//                        try {
////                            startSearching(navController, textSearch) TODO
//                        } catch (e: java.lang.Exception) {
//                        }
//                    }
////                    else navController.popBackStack() TODO
//                    motion_layout.transitionToState(R.id.start)
//                }
//            }
//        }
//
//        goToSearchActivity.setOnClickListener(onClickListener)
//        startSearch.setOnClickListener(onClickListener)
//    }
//
//    private fun createEditText() {
//        val textWatcher = object : TextWatcher {
//            override fun afterTextChanged(s: Editable?) {
//                setIconSearch(s.toString())
//            }
//
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
//        }
//        editTextSearch.addTextChangedListener(textWatcher)
//    }
//
//    fun setIconSearch(newText: String) {
//        val text = newText.trim()
//        if (text == "") startSearch.setImageResource(R.drawable.close_icon_32)
//        else startSearch.setImageResource(R.drawable.search_icon_32)
//    }
//
//    private fun createNavDrawer() {
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        setupDrawerContent(bindingContentMain.navView)
//
//        drawerToggle = setupDrawerToggle();
//
//        // Setup toggle to display hamburger icon with nice animation
//        drawerToggle.isDrawerIndicatorEnabled = true;
//        drawerToggle.syncState();
//
//        // Tie DrawerLayout events to the ActionBarToggle
//        drawerToggle.let { bindingContentMain.drawerLayout.addDrawerListener(it) }
//
//        if (bindingContentMain.navView.headerCount > 0) {
//            // avoid NPE by first checking if there is at least one Header View available
//            val headerLayout: View = bindingContentMain.navView.getHeaderView(0)
//        }
//    }
//
//    private fun setupDrawerToggle(): ActionBarDrawerToggle {
//        // NOTE: Make sure you pass in a valid toolbar reference.  ActionBarDrawToggle() does not require it
//        // and will not render the hamburger icon without it.
//        return ActionBarDrawerToggle(
//            this,
//            bindingContentMain.drawerLayout,
//            toolbar,
//            //R.string.app,
//            //R.string.drawer_close
//            R.string.app_name,
//            R.string.accessories
//        )
//    }
//
//    override fun onPostCreate(savedInstanceState: Bundle?) {
//        super.onPostCreate(savedInstanceState)
//        drawerToggle.syncState()
//    }
//
//    override fun onConfigurationChanged(newConfig: Configuration) {
//        super.onConfigurationChanged(newConfig)
//        drawerToggle.onConfigurationChanged(newConfig)
//    }
//
//    private fun setupDrawerContent(navigationView: NavigationView) {
//        navigationView.setNavigationItemSelectedListener { menuItem ->
//            selectDrawerItem(menuItem)
//            true
//        }
//    }
//
//    private fun selectDrawerItem(menuItem: MenuItem) {
//        removeColorItem(bindingContentMain.navView)
//
//        // Create a new fragment and specify the fragment to show based on nav item clicked
//        when (menuItem.itemId) {
//            R.id.homeFragment -> bindingContentMain.viewPager.currentItem = 0
//            R.id.basketFragment -> bindingContentMain.viewPager.currentItem = 1
//            R.id.favoritesFragment -> bindingContentMain.viewPager.currentItem = 2
//            R.id.settingsFragment -> bindingContentMain.viewPager.currentItem = 3
//        }
//
//        // Highlight the selected item has been done by NavigationView
//        menuItem.isChecked = true
//        title = menuItem.title
//        bindingContentMain.drawerLayout.closeDrawers()
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        // The action bar home/up action should open or close the drawer.
//        if (drawerToggle.onOptionsItemSelected(item)) {
//            return true
//        }
//        return super.onOptionsItemSelected(item)
//    }
//
//    private fun removeColorItem(view: NavigationView) {
//        for (i in 0 until view.menu.size()) {
//            val item = view.menu.getItem(i)
//            item.isChecked = false
//        }
//    }
//
//    private fun createViewPager() {
//        val adapter = ViewPagerAdapter(
//            getListFragment(),
//            supportFragmentManager,
//            lifecycle
//        )
//        bindingContentMain.viewPager.isUserInputEnabled = false;
//        bindingContentMain.viewPager.adapter = adapter
//    }
//
//    private fun getListFragment() = listOf(
//        HomeFragment(), BasketFragment(), FavoritesFragment(), SettingsFragment()
//    )

//    private fun createNavDrawer() {
//        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
//        val navView: NavigationView = findViewById(R.id.nav_view)
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.homeFragment,
//                R.id.basketFragment,
//                R.id.favoritesFragment,
//                R.id.settingsFragment
//            ), drawerLayout
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)
//    }

//    override fun onSupportNavigateUp(): Boolean {
//        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
//    }
}
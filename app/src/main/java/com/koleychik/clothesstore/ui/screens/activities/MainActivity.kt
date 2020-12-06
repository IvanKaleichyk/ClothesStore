package com.koleychik.clothesstore.ui.screens.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.koleychik.clothesstore.R
import com.koleychik.clothesstore.databinding.ActivityMainBinding
import com.koleychik.clothesstore.ui.viewModelFactory.MainViewModelFactory
import com.koleychik.clothesstore.ui.viewModels.MainViewModel
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    private lateinit var bindingContentMain: ActivityMainBinding

    private val navController: NavController by lazy {
        findNavController(R.id.nav_host)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingContentMain = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingContentMain.root)
    }

    override fun onBackPressed() {
        when (navController.currentDestination?.id) {
            R.id.searchFragment -> navController.popBackStack(R.id.navDrawerFragment, false)
            R.id.navDrawerFragment -> {}
            else -> super.onBackPressed()

        }
    }
}
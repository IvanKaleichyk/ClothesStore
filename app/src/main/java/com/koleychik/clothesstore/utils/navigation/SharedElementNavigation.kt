package com.koleychik.clothesstore.utils.navigation

import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.koleychik.clothesstore.R
import androidx.transition.*
import com.koleychik.clothesstore.models.ProductModel
import com.koleychik.clothesstore.ui.screens.NavDrawerFragmentDirections
import com.koleychik.clothesstore.ui.screens.navDrawer.BasketFragment
import com.koleychik.clothesstore.utils.constants.Constants

class SharedElementNavigation(
    private val fragment: Fragment
) {

    fun prepareTransitions() {
        fragment.exitTransition =
            Fade(Fade.MODE_OUT)
                .apply {
                    duration = 500
                }

        fragment.reenterTransition =
            Fade(Fade.MODE_IN)
                .apply {
                    duration = 500
                }
        fragment.postponeEnterTransition()
    }

    fun prepareSharedElementTransition(view: View) {

        setUpSharedElementTransition(view)
        fragment.postponeEnterTransition()
    }

    fun goTo(navController: NavController, imageView: ImageView, productModel: ProductModel) {

        val direction = NavDrawerFragmentDirections.actionNavDrawerFragmentToProductFragment(
                imageView.transitionName,
                fragment::class.simpleName == BasketFragment::class.simpleName,
                productModel
            )

        val extras = FragmentNavigatorExtras(
            imageView to imageView.transitionName
//            binding.tvMagazineTitle to binding.tvMagazineTitle.transitionName
        )

        Log.d(Constants.TAG, "navController.currentDestination = ${navController.currentDestination}")
        Log.d(Constants.TAG, "navController.currentDestination.id = ${navController.currentDestination?.id}")
        Log.d(Constants.TAG, " R.id.navDrawerFragment = ${ R.id.navDrawerFragment}")
//        if (navController.currentDestination?.id == R.id.navDrawerFragment)
        navController.navigate(direction, extras)
    }

    private fun setUpSharedElementTransition(view: View) {
        fragment.sharedElementReturnTransition =
            TransitionInflater.from(fragment.context).inflateTransition(android.R.transition.move)

        val moveTransition =
            TransitionInflater.from(fragment.context).inflateTransition(android.R.transition.move)
                .apply {
                    duration = 500
                }

        fragment.sharedElementEnterTransition = moveTransition
    }

}
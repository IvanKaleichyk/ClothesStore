package com.koleychik.clothesstore.utils.navigation

import android.media.Image
import android.widget.ImageView
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigator
import com.koleychik.clothesstore.R
import com.koleychik.clothesstore.models.BasketModel
import com.koleychik.clothesstore.models.ProductModel
import com.koleychik.clothesstore.ui.screens.NavDrawerFragmentDirections
import com.koleychik.clothesstore.ui.screens.search.SearchResultFragmentDirections

class SharedElementNavigation {

    fun fromNavDrawerGoToProductFragment(img: ImageView, model: ProductModel) {
        val destination = NavDrawerFragmentDirections.actionNavDrawerFragmentToProductFragment(
            model.transitionName,
            model is BasketModel,
            model
        )
        val navController = Navigation.findNavController(img)
        if (navController.currentDestination?.id == R.id.navDrawerFragment)
            navController.navigate(destination, createExtras(img))
    }

    fun fromSearchResultToProductFragment(img: ImageView, model: ProductModel) {
        val destination =
            SearchResultFragmentDirections.actionSearchResultFragmentToProductFragment(
                model.transitionName,
                model is BasketModel,
                model
            )
        val navController = Navigation.findNavController(img)
        if (navController.currentDestination?.id == R.id.searchResultFragment)
            navController.navigate(destination, createExtras(img))
    }

    private fun createExtras(img: ImageView) = FragmentNavigator.Extras.Builder().addSharedElements(
        mapOf(
            img to img.transitionName
        )
    ).build()

}
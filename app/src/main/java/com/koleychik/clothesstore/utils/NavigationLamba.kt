package com.koleychik.clothesstore.utils

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.Navigator
import com.koleychik.clothesstore.R

fun startSearching(
    root: View,
    textSearch: String,
    category: Parcelable,
    minPrice: Int = 50,
    maxPrice: Int = 1000
) {
    val bundle = Bundle()
    bundle.putString(Constants.SEARCH_TEXT, textSearch)
    bundle.putParcelable(Constants.SEARCH_CATEGORY, category)
    bundle.putInt(Constants.SEARCH_PRICE_MIN, minPrice)
    bundle.putInt(Constants.SEARCH_PRICE_MAX, maxPrice)
    Navigation.findNavController(root).navigate(
        R.id.action_searchFragment_to_searchResultFragment,
        bundle
    )
}

fun startSearching(
    navController: NavController,
    textSearch: String,
    category: Parcelable,
    minPrice: Int = 50,
    maxPrice: Int = 1000
) {
    val bundle = Bundle()
    bundle.putString(Constants.SEARCH_TEXT, textSearch)
    bundle.putParcelable(Constants.SEARCH_CATEGORY, category)
    bundle.putInt(Constants.SEARCH_PRICE_MIN, minPrice)
    bundle.putInt(Constants.SEARCH_PRICE_MAX, maxPrice)
    navController.navigate(
        R.id.action_searchFragment_to_searchResultFragment,
        bundle
    )
}

fun navigation(
    root: View,
    id: Int,
    bundle: Bundle? = null,
    navOptions: NavOptions? = null,
    extras: Navigator.Extras? = null
) {
    Navigation.findNavController(root).navigate(id, bundle, navOptions, extras)
}
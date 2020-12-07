package com.koleychik.clothesstore.utils.navigation

import android.widget.ImageView
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.koleychik.clothesstore.models.BasketModel
import com.koleychik.clothesstore.models.ProductModel
import com.koleychik.clothesstore.ui.screens.NavDrawerFragmentDirections

class SharedElementNavigation {

    fun fromNavDrawerGoToProductFragment(img: ImageView, model: ProductModel) {
        val destination = NavDrawerFragmentDirections.actionNavDrawerFragmentToProductFragment(
            model.transitionName,
            model is BasketModel,
            model
        )
        val extras = FragmentNavigator.Extras.Builder().addSharedElements(
            mapOf(
                img to img.transitionName
            )
        ).build()
        Navigation.findNavController(img).navigate(destination, extras)
    }
}
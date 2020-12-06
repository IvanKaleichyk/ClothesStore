package com.koleychik.clothesstore.utils.navigation

import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.koleychik.clothesstore.models.ProductModel
import com.koleychik.clothesstore.ui.screens.NavDrawerFragmentDirections

class SharedElementNavigationMain(fragment: Fragment) {

    fun goToProductFragment(imageView: ImageView, navigationId: Int, bundle: Bundle?) {
        val extras = FragmentNavigatorExtras(imageView to "transitionElement")

        Navigation.findNavController(imageView).navigate(navigationId, bundle, null, extras)
    }

    fun goToProductFragmentFromNavDrawer(imageView: ImageView, model: ProductModel) {

        val direction: NavDirections =
            NavDrawerFragmentDirections.actionNavDrawerFragmentToProductFragment(
                model.transitionName,
                false,
                model
            )

        val extras = FragmentNavigatorExtras(imageView to imageView.transitionName)

//        Navigation.findNavController(imageView).navigate(navigationId, bundle, null, extras)
        Navigation.findNavController(imageView).navigate(direction, extras)
    }


}
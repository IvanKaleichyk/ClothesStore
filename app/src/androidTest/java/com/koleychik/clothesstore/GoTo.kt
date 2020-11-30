package com.koleychik.clothesstore

import android.content.Intent
import android.os.Bundle
import androidx.navigation.NavDeepLinkBuilder
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.koleychik.clothesstore.ui.screens.activities.MainActivity

fun launchFragment(rule : ActivityTestRule<MainActivity>, destinationId: Int,
                   argBundle: Bundle? = null) {
    val launchFragmentIntent = buildLaunchFragmentIntent(destinationId, argBundle)
    rule.launchActivity(launchFragmentIntent)
}

private fun buildLaunchFragmentIntent(destinationId: Int, argBundle: Bundle?): Intent =
    NavDeepLinkBuilder(InstrumentationRegistry.getInstrumentation().targetContext)
        .setGraph(R.navigation.main_graph)
        .setComponentName(MainActivity::class.java)
        .setDestination(destinationId)
        .setArguments(argBundle)
        .createTaskStackBuilder().intents[0]
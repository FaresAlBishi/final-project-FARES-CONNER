package com.example.finalproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI

/**
 * 00 The Activity serves as the entry point to your app.
 *
 * Our Activity will have an XML that contains a NavHostFragment.
 * That NavHostFragment will be what communicates with the navigation
 * graph, and transitions our screens(Fragments) from one to another.
 *
 * @author Marshall Ladd
 */
class MainActivity : AppCompatActivity() {

    // 04 Get a reference to the NavController
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
        04 Actually get your NavController from the activity layout XML
        The value passed to the findNavController function is the ID of
        the NavHostFragment we just added in the previous step.
        */
        navController = findNavController(R.id.fragment2)
        /*
        04 This line connects the NavController to the ActionBar, which
        is the top title bar in your app.  This will make sure the back
        arrow and titles are displayed properly, and more.
        */
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    /*
    04 Override this function to get the back arrow to function properly
     */
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}

package com.example.notesappydh

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {
    lateinit var navController:NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //navController = findNavController(R.id.fragmentContainerView)
        //        setupActionBarWithNavController(navController)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController

        setupActionBarWithNavController(navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
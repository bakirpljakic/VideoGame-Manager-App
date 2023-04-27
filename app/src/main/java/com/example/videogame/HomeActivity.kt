package com.example.videogame

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            val navView: BottomNavigationView = findViewById(R.id.bottomNavigation)
            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            val navController = navHostFragment.navController
            navView.setupWithNavController(navController)
        } else {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.details_fragment, GameDetailsFragment())
                replace(R.id.home_fragment, HomeFragment())
                commit()
            }
        }
    }
}


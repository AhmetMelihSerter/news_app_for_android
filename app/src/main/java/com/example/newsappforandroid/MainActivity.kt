package com.example.newsappforandroid

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.newsappforandroid.core.constants.ApplicationConstants
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initLog()
        bindingBottomNav()
    }

    private fun initLog() {
        val formatStrategy = PrettyFormatStrategy.newBuilder()
            .methodCount(5)
            .tag(ApplicationConstants.APP_NAME)
            .build()
        Logger.addLogAdapter(AndroidLogAdapter(formatStrategy))
    }

    private fun bindingBottomNav() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation)

        val navController = navHostFragment.navController
        bottomNavigation.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.newsFragment || destination.id == R.id.favoritesFragment) {
                bottomNavigation.visibility = View.VISIBLE
            } else {
                bottomNavigation.visibility = View.GONE
            }
        }
    }
}
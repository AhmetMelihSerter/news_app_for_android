package com.example.newsappforandroid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
        val navController = navHostFragment.navController
        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottomNavigation)
        bottomNavigation.setupWithNavController(navController)
    }
}
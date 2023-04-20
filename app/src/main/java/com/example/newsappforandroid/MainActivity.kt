package com.example.newsappforandroid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.newsappforandroid.core.constants.ApplicationConstants
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.FormatStrategy
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val formatStrategy = PrettyFormatStrategy.newBuilder()
            .methodCount(5)
            .tag(ApplicationConstants.APP_NAME)
            .build()
        Logger.addLogAdapter(AndroidLogAdapter(formatStrategy))
    }
}
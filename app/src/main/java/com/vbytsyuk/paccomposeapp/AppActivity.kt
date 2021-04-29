package com.vbytsyuk.paccomposeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.vbytsyuk.paccomposeapp.resources.Theme


class AppActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            val viewModel = ViewModelProvider(this).get(AppViewModel::class.java)
            viewModel.setTheme(if (isDarkThemeOn()) Theme.Dark else Theme.Light)
        }
        setContent { NavigationHost(navHostController = rememberNavController()) }
    }
}

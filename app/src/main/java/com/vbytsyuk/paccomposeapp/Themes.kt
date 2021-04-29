package com.vbytsyuk.paccomposeapp

import android.annotation.SuppressLint
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


enum class Theme(
    val colors: Colors,
) {
    Light(colors = lightColors(background = Color(0xFFF5F5F5))),
    Dark(colors = darkColors());


    @SuppressLint("ComposableNaming")
    @Composable
    operator fun invoke(content: @Composable () -> Unit) = MaterialTheme(
        colors = this.colors,
        content = content
    )

    companion object {
        @Composable
        fun typography() = MaterialTheme.typography
    }
}

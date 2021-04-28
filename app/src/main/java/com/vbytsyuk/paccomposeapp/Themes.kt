package com.vbytsyuk.paccomposeapp

import androidx.compose.material.Colors
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color


enum class Theme(val colors: Colors) {
    Light(colors = lightColors()),
    Dark(colors = darkColors())
}

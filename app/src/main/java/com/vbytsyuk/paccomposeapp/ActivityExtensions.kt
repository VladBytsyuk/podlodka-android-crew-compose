package com.vbytsyuk.paccomposeapp

import android.content.Context
import android.content.res.Configuration.*


fun Context.isDarkThemeOn(): Boolean =
    resources.configuration.uiMode and UI_MODE_NIGHT_MASK == UI_MODE_NIGHT_YES

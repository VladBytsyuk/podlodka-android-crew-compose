package com.vbytsyuk.paccomposeapp.views

import androidx.compose.foundation.clickable
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.vbytsyuk.paccomposeapp.R
import com.vbytsyuk.paccomposeapp.Theme


@Composable
fun ThemedAppBar(
    title: String,
    theme: Theme,
    onBackClick: (() -> Unit)? = null,
    onThemeChange: () -> Unit
) = TopAppBar {
    if (onBackClick != null) {
        Icon(
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = "Back",
            modifier = Modifier.clickable { onBackClick() }
        )
    }
    Text(
        text = title,
        style = MaterialTheme.typography.h6,
        modifier = Modifier.weight(10f)
    )
    Icon(
        painter = painterResource(
            id = when (theme) {
                Theme.Light -> R.drawable.ic_night
                Theme.Dark -> R.drawable.ic_day
            }
        ),
        contentDescription = "Change theme",
        modifier = Modifier.clickable { onThemeChange() }
    )
}


@Preview
@Composable
private fun MainLight() = MaterialTheme(colors = Theme.Light.colors) {
    ThemedAppBar(title = "Title", theme = Theme.Light, onThemeChange = { /* do nothing */ })
}

@Preview
@Composable
private fun MainDark() = MaterialTheme(colors = Theme.Dark.colors) {
    ThemedAppBar(title = "Title", theme = Theme.Dark, onThemeChange = { /* do nothing */ })
}

@Preview
@Composable
private fun DetailsLight() = MaterialTheme(colors = Theme.Light.colors) {
    ThemedAppBar(
        title = "Title",
        theme = Theme.Light,
        onBackClick = { /* do nothing */ },
        onThemeChange = { /* do nothing */ }
    )
}

@Preview
@Composable
private fun DetailsDark() = MaterialTheme(colors = Theme.Dark.colors) {
    ThemedAppBar(
        title = "Title",
        theme = Theme.Dark,
        onBackClick = { /* do nothing */ },
        onThemeChange = { /* do nothing */ }
    )
}

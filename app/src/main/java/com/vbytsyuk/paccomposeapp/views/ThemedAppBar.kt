package com.vbytsyuk.paccomposeapp.views

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vbytsyuk.paccomposeapp.R
import com.vbytsyuk.paccomposeapp.Theme


@Composable
fun ThemedAppBar(
    title: String,
    theme: Theme,
    onBackClick: (() -> Unit)? = null,
    onThemeChange: () -> Unit
) = TopAppBar {
    val backIconRippleInteractionSource = remember { MutableInteractionSource() }
    val themeIconRippleInteractionSource = remember { MutableInteractionSource() }

    if (onBackClick != null) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .clickable(
                    interactionSource = backIconRippleInteractionSource,
                    indication = rememberRipple(radius = 16.dp),
                    onClick = { onBackClick() }
                )
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "Back",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
    Text(
        text = title,
        style = Theme.typography().h6,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier
            .weight(10f)
            .padding(start = if (onBackClick == null) 16.dp else 0.dp)
    )
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .clickable(
                interactionSource = themeIconRippleInteractionSource,
                indication = rememberRipple(radius = 16.dp),
                onClick = { onThemeChange() }
            )
    ) {
        Crossfade(targetState = theme) { theme ->
            Icon(
                painter = painterResource(
                    id = when (theme) {
                        Theme.Light -> R.drawable.ic_night
                        Theme.Dark -> R.drawable.ic_day
                    }
                ),
                contentDescription = "Change theme",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}


@Preview
@Composable
private fun MainLight() = Theme.Light {
    ThemedAppBar(
        title = "Title",
        theme = Theme.Light,
        onThemeChange = { /* do nothing */ }
    )
}

@Preview
@Composable
private fun MainDark() = Theme.Dark {
    ThemedAppBar(
        title = "Title",
        theme = Theme.Dark,
        onThemeChange = { /* do nothing */ }
    )
}

@Preview
@Composable
private fun DetailsLight() = Theme.Light {
    ThemedAppBar(
        title = "Title",
        theme = Theme.Light,
        onBackClick = { /* do nothing */ },
        onThemeChange = { /* do nothing */ }
    )
}

@Preview
@Composable
private fun DetailsDark() = Theme.Dark {
    ThemedAppBar(
        title = "Title",
        theme = Theme.Dark,
        onBackClick = { /* do nothing */ },
        onThemeChange = { /* do nothing */ }
    )
}

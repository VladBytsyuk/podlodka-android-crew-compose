package com.vbytsyuk.paccomposeapp.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.calculateTargetValue
import androidx.compose.animation.defaultDecayAnimationSpec
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.ScrollScope
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vbytsyuk.paccomposeapp.MockSessions
import com.vbytsyuk.paccomposeapp.Session
import com.vbytsyuk.paccomposeapp.Theme


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun FavoritesList(
    theme: Theme,
    favorites: Set<Session>,
    onSessionClick: (Session) -> Unit,
) = Column {
    Text(
        text = "Избранное",
        color = theme.colors.onBackground,
        style = Theme.typography().h6,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    )
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        item { Spacer(modifier = Modifier.width(8.dp)) }
        favorites.forEach { session ->
            item {
                AnimatedVisibility(visible = true, enter = fadeIn(initialAlpha = 0f)) {
                    FavoriteSessionCard(
                        session,
                        onSessionClick,
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }
        }
        item { Spacer(modifier = Modifier.width(8.dp)) }
    }
}


@Preview(showBackground = true, backgroundColor = 0xF5F5F5)
@Composable
private fun FavoritesViewLight() = Theme.Light {
    FavoritesList(
        Theme.Light,
        favorites = MockSessions.subList(0, 3).toSet(),
        onSessionClick = { /* do nothing */ }
    )
}

@Preview(showBackground = true, backgroundColor = 0x333333)
@Composable
private fun FavoritesViewDark() = Theme.Dark {
    FavoritesList(
        Theme.Dark,
        favorites = MockSessions.subList(0, 3).toSet(),
        onSessionClick = { /* do nothing */ }
    )
}

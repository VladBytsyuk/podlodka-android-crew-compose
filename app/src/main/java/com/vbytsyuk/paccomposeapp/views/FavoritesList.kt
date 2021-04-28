package com.vbytsyuk.paccomposeapp.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vbytsyuk.paccomposeapp.MockSessions
import com.vbytsyuk.paccomposeapp.Session
import com.vbytsyuk.paccomposeapp.Theme


@Composable
fun FavoritesList(
    theme: Theme,
    favorites: Set<Session>,
    onSessionClick: (Session) -> Unit,
) = Column {
    Text(
        text = "Избранное",
        color = theme.colors.onBackground,
        style = MaterialTheme.typography.h6,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    )
    LazyRow {
        item { Spacer(modifier = Modifier.width(8.dp)) }
        items(favorites.toList()) { session ->
            FavoriteSessionCard(
                session,
                onSessionClick,
                modifier = Modifier
                    .padding(start = 8.dp, top = 4.dp, end = 8.dp, bottom = 4.dp)
            )
        }
        item { Spacer(modifier = Modifier.width(8.dp)) }
    }
}


@Preview(showBackground = true)
@Composable
private fun FavoritesViewLight() = Theme.Light {
    FavoritesList(
        Theme.Light,
        favorites = MockSessions.subList(0, 3).toSet(),
        onSessionClick = { /* do nothing */ }
    )
}

@Preview(showBackground = true)
@Composable
private fun FavoritesViewDark() = Theme.Dark {
    FavoritesList(
        Theme.Dark,
        favorites = MockSessions.subList(0, 3).toSet(),
        onSessionClick = { /* do nothing */ }
    )
}

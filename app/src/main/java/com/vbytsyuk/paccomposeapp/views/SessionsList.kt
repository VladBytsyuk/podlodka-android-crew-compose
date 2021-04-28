package com.vbytsyuk.paccomposeapp.views

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
fun SessionsList(
    theme: Theme,
    sessions: List<Session>,
    favorites: Set<Session>,
    onSessionClick: (Session) -> Unit,
    onFavoriteClick: (Session, Boolean) -> Unit
) = LazyColumn {
    if (favorites.isNotEmpty()) {
        item { FavoritesList(theme, favorites, onSessionClick) }
    }
    item {
        Text(
            text = "Сессии",
            color = theme.colors.onBackground,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
    sessions
        .groupBy { it.date }
        .forEach { (date, sessionsOnDate) ->
            item {
                Text(
                    text = date,
                    color = theme.colors.onBackground,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                )
            }
            items(sessionsOnDate) { session ->
                SessionCard(
                    session,
                    isFavorite = session in favorites,
                    onContentClick = { onSessionClick(session) },
                    onFavoriteClick = { isFavorite -> onFavoriteClick(session, isFavorite) },
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                )
            }
        }
}


@Preview(showBackground = true)
@Composable
private fun SessionsListLight() = Theme.Light {
    SessionsList(
        theme = Theme.Light,
        sessions = MockSessions,
        favorites = MockSessions.subList(0, 3).toSet(),
        onSessionClick = { /* do nothing */ },
        onFavoriteClick = { _, _ -> /* do nothing */ },
    )
}

@Preview(showBackground = true)
@Composable
private fun SessionsListDark() = Theme.Dark {
    SessionsList(
        theme = Theme.Dark,
        sessions = MockSessions,
        favorites = MockSessions.subList(0, 3).toSet(),
        onSessionClick = { /* do nothing */ },
        onFavoriteClick = { _, _ -> /* do nothing */ },
    )
}

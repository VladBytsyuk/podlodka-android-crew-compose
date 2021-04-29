package com.vbytsyuk.paccomposeapp.ui.lists

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vbytsyuk.paccomposeapp.domain.MockSessions
import com.vbytsyuk.paccomposeapp.domain.Session
import com.vbytsyuk.paccomposeapp.resources.Texts
import com.vbytsyuk.paccomposeapp.resources.Theme
import com.vbytsyuk.paccomposeapp.ui.cards.SessionCard


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SessionsList(
    theme: Theme,
    sessions: List<Session>,
    favorites: Set<Session>,
    onSessionClick: (Session) -> Unit,
    onFavoriteClick: (Session, Boolean) -> Unit
) = LazyColumn {
    item {
        AnimatedVisibility(visible = favorites.isNotEmpty()) {
            FavoritesList(theme, favorites, onSessionClick)
        }
    }
    item {
        Text(
            text = Texts.Title.SESSIONS,
            color = theme.colors.onBackground,
            style = Theme.typography().h6,
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
                    style = Theme.typography().body1,
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
    item { Spacer(modifier = Modifier.height(88.dp)) }
}


@Preview(showBackground = true, backgroundColor = 0xF5F5F5, widthDp = 360, heightDp = 720)
@Composable
private fun SessionsListPhoneLight() = Theme.Light {
    SessionsList(
        theme = Theme.Light,
        sessions = MockSessions,
        favorites = MockSessions.subList(0, 3).toSet(),
        onSessionClick = { /* do nothing */ },
        onFavoriteClick = { _, _ -> /* do nothing */ },
    )
}

@Preview(showBackground = true, backgroundColor = 0x333333, widthDp = 360, heightDp = 720)
@Composable
private fun SessionsListPhoneDark() = Theme.Dark {
    SessionsList(
        theme = Theme.Dark,
        sessions = MockSessions,
        favorites = MockSessions.subList(0, 3).toSet(),
        onSessionClick = { /* do nothing */ },
        onFavoriteClick = { _, _ -> /* do nothing */ },
    )
}

@Preview(showBackground = true, backgroundColor = 0xF5F5F5, widthDp = 1024, heightDp = 720)
@Composable
private fun SessionsListTabletLight() = Theme.Light {
    SessionsList(
        theme = Theme.Light,
        sessions = MockSessions,
        favorites = MockSessions.subList(0, 3).toSet(),
        onSessionClick = { /* do nothing */ },
        onFavoriteClick = { _, _ -> /* do nothing */ },
    )
}

@Preview(showBackground = true, backgroundColor = 0x333333, widthDp = 1024, heightDp = 720)
@Composable
private fun SessionsListTabletDark() = Theme.Dark {
    SessionsList(
        theme = Theme.Dark,
        sessions = MockSessions,
        favorites = MockSessions.subList(0, 3).toSet(),
        onSessionClick = { /* do nothing */ },
        onFavoriteClick = { _, _ -> /* do nothing */ },
    )
}
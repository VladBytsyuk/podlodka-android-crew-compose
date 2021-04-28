package com.vbytsyuk.paccomposeapp.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.vbytsyuk.paccomposeapp.Session


@Composable
fun SessionsListView(
    sessions: List<Session>,
    favorites: Set<Session>,
    onSessionClick: (Session) -> Unit,
    onFavoriteClick: (Session, Boolean) -> Unit
) = LazyColumn {
    item { Text("Сессии") }
    sessions
        .groupBy { it.date }
        .forEach { (date, sessionsOnDate) ->
            item { Text(date) }
            items(sessionsOnDate) { session ->
                SessionCard(
                    session,
                    isFavorite = session in favorites,
                    onContentClick = { onSessionClick(session) },
                    onFavoriteClick = { isFavorite -> onFavoriteClick(session, isFavorite) }
                )
            }
        }
}

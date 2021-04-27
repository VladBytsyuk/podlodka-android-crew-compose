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
    onFavoriteClick: (Session, Boolean) -> Unit
) = Column {
    Text("Sessions")
    LazyColumn {
        sessions
            .groupBy { it.date }
            .forEach { (date, sessionsOnDate) ->
                item { Text(date) }
                items(sessionsOnDate) { session ->
                    SessionCard(session, isFavorite = session in favorites) { isFavorite ->
                        onFavoriteClick(session, isFavorite)
                    }
                }
            }
    }
}
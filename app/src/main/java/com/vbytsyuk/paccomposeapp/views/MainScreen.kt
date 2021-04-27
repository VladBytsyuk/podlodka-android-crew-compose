package com.vbytsyuk.paccomposeapp.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.vbytsyuk.paccomposeapp.MainViewModel


@Composable
fun MainScreen(viewModel: MainViewModel) = Column {
    val sessions by viewModel.sessions.collectAsState()
    val favorites by viewModel.favorites.collectAsState()

    if (favorites.isNotEmpty()) {
        Text("Favorites")
        LazyRow {
            favorites.forEach {
                item { FavoriteSessionCard(session = it) }
            }
        }
    }
    Text("Sessions")
    LazyColumn() {
        sessions.forEach {
            item {
                SessionCard(session = it, isFavorite = it in favorites) { isFavorite ->
                    if (isFavorite) viewModel.addToFavorites(it)
                    else viewModel.removeFromFavorites(it)
                }
            }
        }
    }
}

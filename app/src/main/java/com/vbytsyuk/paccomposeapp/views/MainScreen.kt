package com.vbytsyuk.paccomposeapp.views

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.vbytsyuk.paccomposeapp.MainViewModel


@Composable
fun MainScreen(viewModel: MainViewModel) = Column {
    val sessions by viewModel.sessions.collectAsState()
    val favorites by viewModel.favorites.collectAsState()

    if (favorites.isNotEmpty()) {
        FavoritesListView(favorites)
    }
    SessionsListView(
        sessions,
        favorites,
        onFavoriteClick = { session, isFavorite ->
            if (isFavorite) viewModel.addToFavorites(session)
            else viewModel.removeFromFavorites(session)
        }
    )
}

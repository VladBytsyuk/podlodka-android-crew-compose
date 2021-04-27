package com.vbytsyuk.paccomposeapp.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vbytsyuk.paccomposeapp.MainViewModel


@Composable
fun MainScreen(viewModel: MainViewModel) = Box {
    val sessions by viewModel.sessions.collectAsState()
    val favorites by viewModel.favorites.collectAsState()
    val snackBarState by viewModel.snackBarState.collectAsState()
    Column {
        if (favorites.isNotEmpty()) {
            FavoritesListView(favorites)
        }
        SessionsListView(
            sessions,
            favorites,
            onSessionClick = {

            },
            onFavoriteClick = { session, isFavorite ->
                if (isFavorite) viewModel.addToFavorites(session)
                else viewModel.removeFromFavorites(session)
            }
        )
    }
    if (snackBarState) {
        Snackbar(
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.BottomCenter)
        ) {
            Text(text = "Не удалось добавить сессию в избранное")
        }
    }
}

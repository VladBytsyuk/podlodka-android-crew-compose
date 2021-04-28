package com.vbytsyuk.paccomposeapp.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vbytsyuk.paccomposeapp.AppViewModel


@Composable
fun MainScreen(
    viewModel: AppViewModel = viewModel(),
    onDetailsClick: (String) -> Unit
) = Box {
    val theme by viewModel.theme.collectAsState()
    val sessions by viewModel.sessions.collectAsState()
    val favorites by viewModel.favorites.collectAsState()
    val snackBarState by viewModel.snackBarState.collectAsState()

    Column {
        ThemedAppBar(
            title = "Podlodka Android Crew Сезон #4",
            theme = theme,
            onThemeChange = { viewModel.changeTheme() }
        )
        SessionsList(
            theme,
            sessions,
            favorites,
            onSessionClick = { session -> onDetailsClick(session.id) },
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

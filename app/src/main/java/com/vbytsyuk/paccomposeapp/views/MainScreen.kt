package com.vbytsyuk.paccomposeapp.views

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
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
import com.vbytsyuk.paccomposeapp.Texts


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen(
    viewModel: AppViewModel = viewModel(),
    onDetailsClick: (String) -> Unit
) = Box {
    val theme by viewModel.theme.collectAsState()
    val sessions by viewModel.sessions.collectAsState()
    val favorites by viewModel.favorites.collectAsState()
    val snackBarActive by viewModel.snackBarActive.collectAsState()

    Column {
        ThemedAppBar(
            title = Texts.Title.APP,
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
    AnimatedVisibility(
        visible = snackBarActive,
        initiallyVisible = false,
        enter = slideInVertically(initialOffsetY = { 50 }),
        exit = fadeOut(animationSpec = tween(durationMillis = 300)),
        modifier = Modifier
            .padding(16.dp)
            .align(Alignment.BottomCenter)
    ) {
        Snackbar { Text(text = Texts.Error.SnackBar.FAVORITES_OVERFLOW) }
    }
}

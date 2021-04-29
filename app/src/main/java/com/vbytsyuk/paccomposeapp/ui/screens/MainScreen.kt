package com.vbytsyuk.paccomposeapp.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vbytsyuk.paccomposeapp.AppViewModel
import com.vbytsyuk.paccomposeapp.R
import com.vbytsyuk.paccomposeapp.resources.Texts
import com.vbytsyuk.paccomposeapp.ui.SessionFilter
import com.vbytsyuk.paccomposeapp.ui.TwoActionsDialog
import com.vbytsyuk.paccomposeapp.ui.lists.SessionsList
import com.vbytsyuk.paccomposeapp.ui.ThemedAppBar


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen(
    viewModel: AppViewModel = viewModel(),
    onDetailsClick: (String) -> Unit,
    onAppExit: () -> Unit,
) = Box {
    val theme by viewModel.theme.collectAsState()
    val sessions by viewModel.filteredSessions.collectAsState(initial = emptyList())
    val favorites by viewModel.favorites.collectAsState()
    val searchText by viewModel.searchText.collectAsState()
    val snackBarActive by viewModel.snackBarActive.collectAsState()
    var exitDialogActive by remember { mutableStateOf(false) }

    BackHandler(
        enabled = true,
        onBack = { exitDialogActive = true }
    )

    Column {
        ThemedAppBar(
            title = Texts.Title.APP,
            theme = theme,
            onThemeChange = viewModel::changeTheme
        )
        SessionFilter(
            theme = theme,
            searchText = searchText,
            onTextChanged = viewModel::updateSearchText,
            modifier = Modifier.fillMaxWidth()
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
    AnimatedVisibility(
        visible = exitDialogActive,
        modifier = Modifier.align(Alignment.BottomCenter)
    ) {
        TwoActionsDialog(
            text = Texts.Dialog.EXIT_MESSAGE,
            confirmText = Texts.Action.YES,
            onConfirm = onAppExit,
            cancelText = Texts.Action.CANCEL,
            onDismiss = { exitDialogActive = false }
        )
    }
}

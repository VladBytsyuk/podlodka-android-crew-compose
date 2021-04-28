package com.vbytsyuk.paccomposeapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class AppViewModel : ViewModel() {
    private val _theme = MutableStateFlow(Theme.Light)
    val theme: StateFlow<Theme> = _theme

    val sessions: StateFlow<List<Session>> = MutableStateFlow(MockSessions)

    private val _favorites = MutableStateFlow<Set<Session>>(emptySet())
    val favorites: StateFlow<Set<Session>> get() = _favorites

    private val _snackBarState = MutableStateFlow(false)
    val snackBarState: StateFlow<Boolean> get() = _snackBarState

    fun addToFavorites(session: Session) {
        viewModelScope.launch {
            if (_favorites.value.size >= 3) {
                _snackBarState.emit(true)
                delay(3000)
                _snackBarState.emit(false)
            } else {
                _favorites.emit(favorites.value + session)
            }
        }
    }

    fun removeFromFavorites(session: Session) {
        viewModelScope.launch {
            _favorites.emit(favorites.value - session)
        }
    }

    fun changeTheme() {
        viewModelScope.launch {
            _theme.emit(
                when (theme.value) {
                    Theme.Light -> Theme.Dark
                    Theme.Dark -> Theme.Light
                }
            )
        }
    }

    fun setTheme(theme: Theme) {
        viewModelScope.launch {
            _theme.emit(theme)
        }
    }
}

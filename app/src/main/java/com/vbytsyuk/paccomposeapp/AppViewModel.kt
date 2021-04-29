package com.vbytsyuk.paccomposeapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vbytsyuk.paccomposeapp.domain.MockSessions
import com.vbytsyuk.paccomposeapp.domain.Session
import com.vbytsyuk.paccomposeapp.resources.Theme
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

    private val _snackBarActive = MutableStateFlow(false)
    val snackBarActive: StateFlow<Boolean> get() = _snackBarActive

    fun addToFavorites(session: Session) {
        viewModelScope.launch {
            if (favorites.value.size >= 3) {
                _snackBarActive.emit(true)
                delay(SNACK_BAR_LIFETIME_MS)
                _snackBarActive.emit(false)
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
            _theme.emit(theme.value.swap())
        }
    }

    fun setTheme(theme: Theme) {
        viewModelScope.launch {
            _theme.emit(theme)
        }
    }

    companion object {
        private const val SNACK_BAR_LIFETIME_MS = 1_500L
    }
}

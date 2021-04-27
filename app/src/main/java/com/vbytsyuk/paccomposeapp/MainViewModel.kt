package com.vbytsyuk.paccomposeapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class MainViewModel : ViewModel() {
    val sessions: StateFlow<List<Session>> = MutableStateFlow(MockSessions)

    val favorites: StateFlow<Set<Session>> get() = _favorites
    private val _favorites = MutableStateFlow<Set<Session>>(emptySet())

    fun addToFavorites(session: Session) {
        viewModelScope.launch {
            _favorites.emit(favorites.value + session)
        }
    }

    fun removeFromFavorites(session: Session) {
        viewModelScope.launch {
            _favorites.emit(favorites.value - session)
        }
    }
}

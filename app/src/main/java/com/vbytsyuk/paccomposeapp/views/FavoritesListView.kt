package com.vbytsyuk.paccomposeapp.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.vbytsyuk.paccomposeapp.Session


@Composable
fun FavoritesListView(
    favorites: Set<Session>
) = Column {
    Text("Favorites")
    LazyRow {
        items(favorites.toList()) { session ->
            FavoriteSessionCard(session)
        }
    }
}

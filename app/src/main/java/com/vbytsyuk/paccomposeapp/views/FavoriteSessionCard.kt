package com.vbytsyuk.paccomposeapp.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vbytsyuk.paccomposeapp.MockSessions
import com.vbytsyuk.paccomposeapp.Session
import com.vbytsyuk.paccomposeapp.Theme


@Composable
fun FavoriteSessionCard(
    session: Session,
    onSessionClick: (Session) -> Unit,
    modifier: Modifier = Modifier
) = Card(
    modifier = modifier
        .width(160.dp)
        .height(128.dp)
        .clickable { onSessionClick(session) }
) {
    Column(
        modifier = Modifier.padding(horizontal = 8.dp)
    ) {
        Text(
            text = session.timeInterval,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(top = 4.dp)
        )
        Text(
            text = session.date,
            style = MaterialTheme.typography.body2,
        )
        Text(
            text = session.speaker,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.subtitle2,
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(
            text = session.description,
            fontSize = 12.sp,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.body2,
            modifier = Modifier.padding(bottom = 8.dp)
        )
    }
}


@Preview
@Composable
private fun FavoriteSessionCardLight() = Theme.Light {
    FavoriteSessionCard(MockSessions.random(), onSessionClick = { /* do nothing */ })
}

@Preview
@Composable
private fun FavoriteSessionCardDark() = Theme.Dark {
    FavoriteSessionCard(MockSessions.random(), onSessionClick = { /* do nothing */ })
}

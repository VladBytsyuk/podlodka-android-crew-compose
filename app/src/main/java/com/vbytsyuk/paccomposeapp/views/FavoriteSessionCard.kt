package com.vbytsyuk.paccomposeapp.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
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


@Composable
fun FavoriteSessionCard(session: Session) = Card(
    modifier = Modifier
        .width(128.dp)
        .padding(4.dp)
) {
    Column {
        Text(
            text = session.timeInterval,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = session.date
        )
        Text(
            text = session.speaker,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(
            text = session.description,
            fontSize = 12.sp,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )
    }
}


@Preview
@Composable
private fun  FavoriteSessionCard_common() =
    FavoriteSessionCard(MockSessions.random())

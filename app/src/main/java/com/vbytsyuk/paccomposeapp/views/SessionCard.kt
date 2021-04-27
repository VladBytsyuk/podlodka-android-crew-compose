package com.vbytsyuk.paccomposeapp.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.transform.CircleCropTransformation
import com.google.accompanist.coil.rememberCoilPainter
import com.vbytsyuk.paccomposeapp.MockSessions
import com.vbytsyuk.paccomposeapp.R
import com.vbytsyuk.paccomposeapp.Session


@Composable
fun SessionCard(session: Session) = Card(
    modifier = Modifier
        .padding(4.dp)
) {
    Row {
        Image(
            painter = rememberCoilPainter(
                request = session.imageUrl,
                requestBuilder = { transformations(CircleCropTransformation()) },
                previewPlaceholder = R.drawable.ic_person_placeholder,
            ),
            contentDescription = session.speaker,
            modifier = Modifier
                .size(64.dp)
                .padding(8.dp)
                .align(Alignment.CenterVertically)
        )
        Column(
            modifier = Modifier
                .weight(10f)
        ) {
            Text(session.speaker, fontWeight = FontWeight.Bold)
            Text(session.timeInterval, fontWeight = FontWeight.Bold)
            Text(session.description)
        }
        Image(
            painter = painterResource(id = R.drawable.ic_star_filled),
            contentDescription = session.speaker,
            modifier = Modifier
                .size(48.dp)
                .padding(8.dp)
                .align(Alignment.CenterVertically)
        )
    }
}


@Preview
@Composable
private fun SessionCard_common() =
    SessionCard(MockSessions.random())
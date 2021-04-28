package com.vbytsyuk.paccomposeapp.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.transform.CircleCropTransformation
import com.google.accompanist.coil.rememberCoilPainter
import com.vbytsyuk.paccomposeapp.*
import com.vbytsyuk.paccomposeapp.R


@Composable
fun DetailScreen(
    viewModel: AppViewModel = viewModel(),
    session: Session,
    onBackClick: () -> Unit
) = Column(
    modifier = Modifier.fillMaxSize()
) {
    ThemedAppBar(
        title = "Podlodka Android Crew Сезон #4",
        theme = viewModel.theme.value,
        onBackClick = onBackClick,
        onThemeChange = { viewModel.changeTheme() }
    )

    Image(
        painter = rememberCoilPainter(
            request = session.imageUrl,
            requestBuilder = { transformations(CircleCropTransformation()) },
            previewPlaceholder = R.drawable.ic_person_placeholder,
        ),
        contentDescription = session.speaker,
        modifier = Modifier
            .size(256.dp)
            .padding(8.dp)
            .align(Alignment.CenterHorizontally)
    )
    Text(
        text = session.speaker,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .padding(8.dp)
            .align(Alignment.CenterHorizontally)
    )
    Row {
        Image(
            painter = painterResource(id = R.drawable.ic_calendar),
            contentDescription = session.speaker,
            modifier = Modifier
                .size(32.dp)
                .padding(8.dp)
                .align(Alignment.CenterVertically)
        )
        Text(
            text = session.date,
            fontSize = 12.sp,
            modifier = Modifier.padding(8.dp)
        )
        Text(
            text = session.timeInterval,
            fontSize = 12.sp,
            modifier = Modifier.padding(8.dp)
        )
    }
    Text(
        text = session.description,
        modifier = Modifier
            .padding(8.dp)
    )
}

@Preview(showBackground = true)
@Composable
private fun DetailLight() = MaterialTheme(colors = Theme.Light.colors) {
    DetailScreen(
        session = MockSessions.random(),
        onBackClick = { /* do nothing */ }
    )
}

@Preview(showBackground = true)
@Composable
private fun DetailDark() = MaterialTheme(colors = Theme.Dark.colors) {
    DetailScreen(
        session = MockSessions.random(),
        onBackClick = { /* do nothing */ }
    )
}


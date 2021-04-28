package com.vbytsyuk.paccomposeapp.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
) = Box(
    modifier = Modifier.fillMaxSize()
) {
    val theme by viewModel.theme.collectAsState()

    ThemedAppBar(
        title = "Podlodka Android Crew Сезон #4",
        theme = theme,
        onBackClick = onBackClick,
        onThemeChange = { viewModel.changeTheme() }
    )

    Box(
        modifier = Modifier
            .align(Alignment.Center)
    ) {
        Column {
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
                color = theme.colors.onBackground,
                style = MaterialTheme.typography.h4,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Row(
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Spacer(modifier = Modifier.width(16.dp))
                Image(
                    painter = painterResource(id = R.drawable.ic_calendar),
                    contentDescription = session.speaker,
                    modifier = Modifier
                        .size(16.dp)
                        .align(Alignment.CenterVertically)
                )
                Text(
                    text = session.date,
                    color = theme.colors.onBackground,
                    style = MaterialTheme.typography.subtitle2,
                    modifier = Modifier.padding(8.dp)
                )
                Text(
                    text = session.timeInterval,
                    color = theme.colors.onBackground,
                    style = MaterialTheme.typography.subtitle2,
                    modifier = Modifier.padding(8.dp)
                )
            }
            Text(
                text = session.description,
                color = theme.colors.onBackground,
                style = MaterialTheme.typography.body1,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
        }
    }
}

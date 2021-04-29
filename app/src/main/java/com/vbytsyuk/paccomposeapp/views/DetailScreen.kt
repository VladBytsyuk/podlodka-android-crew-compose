package com.vbytsyuk.paccomposeapp.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
) = Column (
    modifier = Modifier.fillMaxSize()
) {
    val theme by viewModel.theme.collectAsState()

    ThemedAppBar(
        title = "Podlodka Android Crew Сезон #4",
        theme = theme,
        onBackClick = onBackClick,
        onThemeChange = { viewModel.changeTheme() }
    )

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxHeight()
            .align(Alignment.CenterHorizontally)
    ) {
        if (maxWidth < 720.dp) DetailsScreenPhone(session, theme)
        else DetailsScreenTablet(session, theme)
    }
}

@Composable
private fun DetailsScreenTablet(session: Session, theme: Theme) = Row {
    Avatar(session, modifier = Modifier
        .align(Alignment.CenterVertically)
        .padding(start = 32.dp))
    InfoTexts(session, theme, modifier = Modifier
        .align(Alignment.CenterVertically)
        .padding(horizontal = 32.dp))
}

@Composable
private fun DetailsScreenPhone(session: Session, theme: Theme) = Column {
    Avatar(session, modifier = Modifier
        .align(Alignment.CenterHorizontally)
        .padding(top = 128.dp))
    InfoTexts(session, theme, modifier = Modifier
        .align(Alignment.CenterHorizontally)
        .padding(bottom = 128.dp))
}



@Composable
private fun Avatar(session: Session, modifier: Modifier) = Image(
    painter = rememberCoilPainter(
        request = session.imageUrl,
        requestBuilder = { transformations(CircleCropTransformation()) },
        previewPlaceholder = R.drawable.ic_person_placeholder,
    ),
    contentDescription = session.speaker,
    modifier = modifier
        .size(256.dp)
        .padding(8.dp)
)

@Composable
private fun InfoTexts(session: Session, theme: Theme, modifier: Modifier) = Column(modifier) {
    Text(
        text = session.speaker,
        color = theme.colors.onBackground,
        style = Theme.typography().h4,
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
            style = Theme.typography().subtitle2,
            modifier = Modifier.padding(8.dp)
        )
        Text(
            text = session.timeInterval,
            color = theme.colors.onBackground,
            style = Theme.typography().subtitle2,
            modifier = Modifier.padding(8.dp)
        )
    }
    Text(
        text = session.description,
        color = theme.colors.onBackground,
        style = Theme.typography().body1,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    )
}


@Preview(showBackground = true, backgroundColor = 0xF5F5F5, widthDp = 360, heightDp = 720)
@Composable
private fun DetailsPhoneLight() = Theme.Light {
    DetailsScreenPhone(session = MockSessions.random(), theme = Theme.Light)
}

@Preview(showBackground = true, backgroundColor = 0x333333, widthDp = 360, heightDp = 720)
@Composable
private fun DetailsPhoneDark() = Theme.Dark {
    DetailsScreenPhone(session = MockSessions.random(), theme = Theme.Dark)
}


@Preview(showBackground = true, backgroundColor = 0xF5F5F5, widthDp = 1024, heightDp = 720)
@Composable
private fun DetailsTabletLight() = Theme.Light {
    DetailsScreenTablet(session = MockSessions.random(), theme = Theme.Light)
}

@Preview(showBackground = true, backgroundColor = 0x333333, widthDp = 1024, heightDp = 720)
@Composable
private fun DetailsTabletDark() = Theme.Dark {
    DetailsScreenTablet(session = MockSessions.random(), theme = Theme.Dark)
}

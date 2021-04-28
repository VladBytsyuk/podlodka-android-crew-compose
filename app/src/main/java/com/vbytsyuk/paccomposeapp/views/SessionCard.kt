package com.vbytsyuk.paccomposeapp.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.transform.CircleCropTransformation
import com.google.accompanist.coil.rememberCoilPainter
import com.vbytsyuk.paccomposeapp.MockSessions
import com.vbytsyuk.paccomposeapp.R
import com.vbytsyuk.paccomposeapp.Session
import com.vbytsyuk.paccomposeapp.Theme


@Composable
fun SessionCard(
    session: Session,
    isFavorite: Boolean,
    onContentClick: () -> Unit,
    onFavoriteClick: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) = Card(
    modifier = modifier.clickable { onContentClick() }
) {
    val favoriteIconRippleInteractionSource = remember { MutableInteractionSource() }
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
            Text(
                text = session.speaker,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(top = 8.dp, bottom = 2.dp)
            )
            Text(
                text = session.timeInterval,
                style = MaterialTheme.typography.subtitle2,
            )
            Text(
                text = session.description,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.body2,
                modifier = Modifier.padding(top = 4.dp, bottom = 8.dp)
            )
        }
        Box(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .clickable(
                    interactionSource = favoriteIconRippleInteractionSource,
                    indication = rememberRipple(radius = 16.dp),
                    onClick = { onFavoriteClick(!isFavorite) }
                )
        ) {
            Image(
                painter = painterResource(
                    id = if (isFavorite) R.drawable.ic_star_filled else R.drawable.ic_star_contoured
                ),
                contentDescription = session.speaker,
                modifier = Modifier
                    .size(48.dp)
                    .padding(8.dp)
            )
        }
    }
}


@Preview
@Composable
private fun SessionLight() = Theme.Light {
    SessionCard(
        session = MockSessions.random(),
        isFavorite = false,
        onFavoriteClick = { },
        onContentClick = { }
    )
}

@Preview
@Composable
private fun SessionDark() = Theme.Dark {
    SessionCard(
        session = MockSessions.random(),
        isFavorite = false,
        onFavoriteClick = { },
        onContentClick = { }
    )
}


@Preview
@Composable
private fun SessionLightFavorite() = Theme.Light {
    SessionCard(
        session = MockSessions.random(),
        isFavorite = true,
        onFavoriteClick = { },
        onContentClick = { }
    )
}

@Preview
@Composable
private fun SessionDarkFavorite() = Theme.Dark {
    SessionCard(
        session = MockSessions.random(),
        isFavorite = true,
        onFavoriteClick = { },
        onContentClick = { }
    )
}

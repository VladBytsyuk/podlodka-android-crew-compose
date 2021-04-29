package com.vbytsyuk.paccomposeapp.views

import android.view.MotionEvent
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.transform.CircleCropTransformation
import com.google.accompanist.coil.rememberCoilPainter
import com.vbytsyuk.paccomposeapp.*
import com.vbytsyuk.paccomposeapp.R


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
    val favoritePressed = remember { mutableStateOf(false) }
    val favoriteScale = animateFloatAsState(if (favoritePressed.value) 0.65f else 1f)

    Row {
        Image(
            painter = rememberCoilPainter(
                request = session.imageUrl,
                requestBuilder = { transformations(CircleCropTransformation()) },
                previewPlaceholder = R.drawable.ic_person_placeholder,
            ),
            contentDescription = Texts.ContentDescription.speakerAvatar(session.speaker),
            modifier = Modifier
                .size(64.dp)
                .padding(8.dp)
                .align(Alignment.CenterVertically)
        )
        Column(
            modifier = Modifier.weight(10f)
        ) {
            Text(
                text = session.speaker,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = Theme.typography().h6,
                modifier = Modifier.padding(top = 8.dp, bottom = 2.dp)
            )
            Text(
                text = session.timeInterval,
                style = Theme.typography().subtitle2,
            )
            Text(
                text = session.description,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = Theme.typography().body2,
                modifier = Modifier.padding(top = 4.dp, bottom = 8.dp)
            )
        }
        Box(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .pointerInteropFilter {
                    when (it.action) {
                        MotionEvent.ACTION_DOWN -> {
                            favoritePressed.value = true
                            onFavoriteClick(!isFavorite)
                        }
                        MotionEvent.ACTION_UP -> favoritePressed.value = false
                    }
                    true
                }
        ) {
            Crossfade(targetState = isFavorite) { isFavorite ->
                val (iconId, contentDescription) = when {
                    isFavorite -> R.drawable.ic_star_filled to Texts.ContentDescription.FAVORITE_ON
                    else -> R.drawable.ic_star_contoured to Texts.ContentDescription.FAVORITE_OFF
                }
                Image(
                    painter = painterResource(id = iconId),
                    contentDescription = contentDescription,
                    modifier = Modifier
                        .scale(favoriteScale.value)
                        .size(48.dp)
                        .padding(8.dp)
                )
            }
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

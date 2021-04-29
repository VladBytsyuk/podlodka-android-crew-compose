package com.vbytsyuk.paccomposeapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import com.vbytsyuk.paccomposeapp.R
import com.vbytsyuk.paccomposeapp.resources.Texts
import com.vbytsyuk.paccomposeapp.resources.Theme


@Composable
fun SessionFilter(
    theme: Theme,
    searchText: String,
    onTextChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) = TextField(
    value = searchText,
    onValueChange = onTextChanged,
    leadingIcon = {
        Image(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = Texts.ContentDescription.SEARCH
        )
    },
    shape = RectangleShape,
    textStyle = LocalTextStyle.current.copy(color = theme.colors.onBackground),
    singleLine = true,
    placeholder = { Text(text = Texts.Hint.SEARCH) },
    modifier = modifier
)

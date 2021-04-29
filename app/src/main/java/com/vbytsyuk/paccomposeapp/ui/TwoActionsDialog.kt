package com.vbytsyuk.paccomposeapp.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vbytsyuk.paccomposeapp.resources.Theme


@Composable
fun TwoActionsDialog(
    text: String,
    confirmText: String,
    onConfirm: () -> Unit,
    cancelText: String,
    onDismiss: () -> Unit
) = AlertDialog(
    text = {
        Text(
            text = text,
            style = Theme.typography().subtitle1,
        )
    },
    confirmButton = {
        Text(
            text = confirmText,
            style = Theme.typography().button,
            modifier = Modifier
                .padding(vertical = 4.dp, horizontal = 8.dp)
                .clickable { onConfirm() }
        )
    },
    dismissButton = {
        Text(
            text = cancelText,
            style = Theme.typography().button,
            modifier = Modifier
                .padding(vertical = 4.dp, horizontal = 8.dp)
                .clickable { onDismiss() }
        )
    },
    onDismissRequest = { onDismiss() },
)


@Preview
@Composable
private fun DialogLight() = Theme.Light {
    TwoActionsDialog(
        text = "Message",
        confirmText = "OK",
        onConfirm = { /*do nothing*/ },
        cancelText = "Cancel",
        onDismiss = { /*do nothing*/ }
    )
}

@Preview
@Composable
private fun DialogDark() = Theme.Dark {
    TwoActionsDialog(
        text = "Message",
        confirmText = "OK",
        onConfirm = { /*do nothing*/ },
        cancelText = "Cancel",
        onDismiss = { /*do nothing*/ }
    )
}

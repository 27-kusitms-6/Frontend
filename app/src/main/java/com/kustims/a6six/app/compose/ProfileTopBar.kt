package com.kustims.a6six.app.compose

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kustims.a6six.R
import com.kustims.a6six.app.ui.theme.topAppBarContentColor

class ProfileTopBar(
    onUpdate: () -> Unit,
    onDeleteConfirmed: () -> Unit
) {

}

@Composable
fun DeleteAction(
    openConfirmationDialog: () -> Unit
) {
//    var expanded by remember { mutableStateOf(false) }

    var expanded by remember { mutableStateOf(false) }
    IconButton(onClick = {
        expanded = true
    }) {
        Icon(
            painter = painterResource(id = R.drawable.ic_vertical_menu),
            contentDescription = "Delete Account",
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = {
            expanded = false
        }) {
        DropdownMenuItem(onClick = {
            expanded = false
            openConfirmationDialog()
        }) {
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = "Delete Account",
                style = MaterialTheme.typography.subtitle2
            )
        }
    }
}

@Preview
@Composable
fun ProfileTopBarPreview() {
    ProfileTopBar(onUpdate = { }) {
    }
}

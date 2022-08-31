package ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import models.Note

@Composable
fun ListItem(note: Note) {
    Column() {
        Column(modifier = Modifier.padding(10.dp)) {
            Row {
                Text(text = note.title)
            }
        }
    }
}
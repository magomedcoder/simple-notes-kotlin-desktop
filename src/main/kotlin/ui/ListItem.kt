package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import models.Note

@Composable
fun ListItem(note: Note, onItemClick: (Note) -> Unit) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(150.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.Gray)
            .clickable(true) {
                onItemClick(note)
            }
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Row {
                Text(text = note.title)
                Text(text = note.body)
            }
        }
    }
}
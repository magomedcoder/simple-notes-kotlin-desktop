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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import models.Note
import utils.getDateString

@Composable
fun ListItem(note: Note, onItemClick: (Note) -> Unit) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(150.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color(note.color))
            .clickable(true) {
                onItemClick(note)
            }
    ) {
        Text(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(.92f),
            text = note.title,
            fontSize = 20.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier
                .padding(start = 10.dp, bottom = 18.dp),
            text = note.body,
            fontSize = 16.sp,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
        )
        Spacer(modifier = Modifier.height(22.dp))
        Text(
            modifier = Modifier.padding(end = 18.dp)
                .fillMaxWidth(),
            text = getDateString(note.createdAt),
            textAlign = TextAlign.Right
        )
    }
}
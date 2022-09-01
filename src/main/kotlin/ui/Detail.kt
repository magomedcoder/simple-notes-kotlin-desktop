package ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import utils.DatabaseHelper

@Composable
fun Detail(onBack: () -> Unit, id: Int) {

    val queries = DatabaseHelper.queries
    val note = queries.selectNote(id = id.toLong()).executeAsOneOrNull()
    val title = remember { mutableStateOf(note?.title ?: "") }
    val body = remember { mutableStateOf(note?.body ?: "") }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 10.dp)
                .clip(RoundedCornerShape(10.dp)),
            onValueChange = { title.value = it },
            label = { Text("Название", color = Color.Gray) },
            value = title.value
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, start = 20.dp, end = 20.dp, bottom = 60.dp)
                .clip(RoundedCornerShape(10.dp)),
            label = { Text("Заметка...", color = Color.Gray) },
            onValueChange = { body.value = it },
            value = body.value
        )
        ExtendedFloatingActionButton(
            text = { Text(text = "Сохранить") },
            onClick = {
                if (queries.selectNote(id.toLong()).executeAsOneOrNull() == null)
                    queries.insert(title.value, body.value)
                else queries.updateNote(title.value, body.value, id.toLong())
                onBack.invoke()
            }
        )
        if (queries.selectNote(id.toLong()).executeAsOneOrNull() != null)
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = "del",
            modifier = Modifier
                .padding(end = 20.dp, top = 15.dp)
                .clickable {
                    queries.deleteNote(id.toLong())
                    onBack.invoke()
                })
    }
}
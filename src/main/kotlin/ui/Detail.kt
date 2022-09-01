package ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                tint = Color.Gray,
                contentDescription = "Назад",
                modifier = Modifier
                    .padding(start = 20.dp, top = 15.dp)
                    .size(25.dp)
                    .clickable {
                        onBack.invoke()
                    })
            Row() {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Сохранить",
                    modifier = Modifier
                        .padding(end = 20.dp, top = 15.dp)
                        .size(25.dp)
                        .clickable {
                            if (queries.selectNote(id.toLong()).executeAsOneOrNull() == null)
                                queries.insert(title.value, body.value)
                            else queries.updateNote(title.value, body.value, id.toLong())
                            onBack.invoke()
                        })
                if (queries.selectNote(id.toLong()).executeAsOneOrNull() != null)
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "del",
                        modifier = Modifier
                            .padding(end = 20.dp, top = 15.dp)
                            .size(25.dp)
                            .clickable {
                                queries.deleteNote(id.toLong())
                                onBack.invoke()
                            })
            }
        }
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 10.dp)
                .clip(RoundedCornerShape(10.dp)),
            textStyle = TextStyle(
                color = Color.Gray,
                fontSize = 20.sp,
            ),
            onValueChange = { title.value = it },
            label = { Text("Название", color = Color.Gray) },
            value = title.value,
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(top = 20.dp, start = 20.dp, end = 20.dp, bottom = 60.dp)
                .clip(RoundedCornerShape(10.dp)),
            textStyle = TextStyle(
                color = Color.Gray,
                fontSize = 16.sp,
            ),
            label = { Text("Заметка...", color = Color.Gray) },
            onValueChange = { body.value = it },
            value = body.value,
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )

    }
}
package ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import models.Note
import models.NoteQueries
import theme.listColor
import utils.DatabaseHelper

@ExperimentalFoundationApi
@Composable
fun Home(onItemClick: (Note) -> Unit) {

    val searchQuery = remember { mutableStateOf("") }
    val playerQueries: NoteQueries = DatabaseHelper.queries
    val list = playerQueries.selectAll().executeAsList()

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                TextField(
                    value = searchQuery.value,
                    onValueChange = {
                        searchQuery.value = it
                    },
                    placeholder = {
                        Text("Поиск", fontSize = 15.sp, color = Color.Gray)
                    },
                    modifier = Modifier
                        .padding(
                            start = 10.dp,
                            top = 10.dp,
                            end = 0.dp,
                            bottom = 10.dp
                        )
                        .clip(RoundedCornerShape(10.dp))
                        .weight(1f)
                        .defaultMinSize(minHeight = 40.dp),
                    leadingIcon = {
                        Icon(Icons.Filled.Search, contentDescription = "Поиск")
                    },
                    trailingIcon = {
                        if (searchQuery.value.isNotEmpty()) {
                            Icon(
                                imageVector = Icons.Rounded.Clear,
                                contentDescription = "Очистить",
                                modifier = Modifier.clickable { searchQuery.value = "" }
                            )
                        }
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Создать",
                    modifier = Modifier
                        .padding(
                            start = 15.dp,
                            top = 20.dp,
                            end = 20.dp,
                            bottom = 20.dp
                        )
                        .size(30.dp)
                        .clickable {
                            onItemClick.invoke(Note(0, "", "", listColor[0], null, null))
                        }
                )
            }
        }
    ) {
        LazyVerticalGrid(
            cells = GridCells.Adaptive(300.dp)
        ) {
            itemsIndexed(
                items = list.filter {
                    it.title.contains(searchQuery.value, ignoreCase = true)
                }
            ) { _, note ->
                ListItem(note, onItemClick)
            }
        }
    }
}
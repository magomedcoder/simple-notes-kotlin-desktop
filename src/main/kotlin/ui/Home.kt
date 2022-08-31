package ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import utils.DatabaseHelper
import models.NoteQueries

@ExperimentalFoundationApi
@Composable
fun Home(onItemClick: () -> Unit) {

    val searchQuery = remember { mutableStateOf("") }
    val playerQueries: NoteQueries = DatabaseHelper.queries
    val list = playerQueries.selectAll().executeAsList()

    Scaffold(
        topBar = {
            Row {
                TextField(
                    value = searchQuery.value,
                    onValueChange = { searchQuery.value = it },
                    placeholder = {
                        Text("Поиск", fontSize = 15.sp, color = Color.Gray)
                    },
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp, top = 10.dp)
                        .clip(RoundedCornerShape(10.dp))
                )
                FloatingActionButton(
                    modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 10.dp),
                    onClick = { onItemClick.invoke()  }
                ) {
                    Icon(Icons.Sharp.Add, contentDescription = "add")
                }
            }
        }
    ) {
        LazyVerticalGrid(
            cells = GridCells.Adaptive(300.dp)
        ) {
            itemsIndexed(
                items = list
            ) { _, note ->
                ListItem(note)
            }
        }
    }
}
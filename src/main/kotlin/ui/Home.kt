package ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@ExperimentalFoundationApi
@Composable
fun Home() {
    val searchQuery = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TextField(
                value = searchQuery.value,
                onValueChange = { searchQuery.value = it },
                placeholder = {
                    Text("Поиск", fontSize = 15.sp, color = Color.Gray)
                },
            )
        }
    ) {

    }
}
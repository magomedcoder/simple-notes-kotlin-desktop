import androidx.compose.animation.Crossfade
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import ui.Detail
import ui.Home

@ExperimentalFoundationApi
fun main() = application {

    var screenState by remember { mutableStateOf<Screens>(Screens.Home) }

    Window(
        onCloseRequest = ::exitApplication,
        title = "Заметки",
        state = WindowState(size = DpSize(500.dp, 800.dp))
    ) {
        Crossfade(
            targetState = screenState
        ) { screen ->
            when (screen) {
                is Screens.Home -> Home(onItemClick = {
                    screenState = (Screens.Detail(id = it.id.toInt()))
                })
                is Screens.Detail -> Detail(
                    onBack = { screenState = (Screens.Home) },
                    id = screen.id
                )
            }
        }
    }
}
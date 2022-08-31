import androidx.compose.animation.Crossfade
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import ui.Detail
import ui.Home

@ExperimentalFoundationApi
fun main() = application {
    var screenState by remember { mutableStateOf<Screens>(Screens.Home) }
    Window(onCloseRequest = ::exitApplication) {
        Crossfade(
            targetState = screenState
        ) { screen ->
            when (screen) {
                is Screens.Home -> Home(onItemClick = {
                    screenState = (Screens.Detail)
                })
                is Screens.Detail -> Detail(onBack = { screenState = (Screens.Home) })
            }
        }
    }
}
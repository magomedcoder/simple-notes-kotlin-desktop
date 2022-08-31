import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import ui.Home

@ExperimentalFoundationApi
fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        Home()
    }
}
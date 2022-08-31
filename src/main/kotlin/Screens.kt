sealed class Screens {
    object Home : Screens()
    data class Detail(val id: Int) : Screens()
}
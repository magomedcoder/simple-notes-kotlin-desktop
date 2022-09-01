package utils

import java.text.SimpleDateFormat

fun getDateString(time: Long?): String =
    if (time != null) SimpleDateFormat("dd MMMM HH:mm").format(time * 1000L) else ""
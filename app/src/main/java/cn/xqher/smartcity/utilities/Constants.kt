package cn.xqher.smartcity.utilities

import android.content.Context
import android.widget.Toast


object Constants {


    const val BANNER_LIST = "/prod-api/api/rotation/list"
    const val SERVICE_LIST = "/prod-api/api/service/list"
    const val NEWS_LIST = "/prod-api/press/press/list"

    fun showToast(
        context: Context? = null,
        toastText: Any,
    ) {
        if (context == null) return

        val text: CharSequence = when (toastText) {
            is Int -> context.getString(toastText)
            is String -> toastText
            else -> ""
        }

        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
}
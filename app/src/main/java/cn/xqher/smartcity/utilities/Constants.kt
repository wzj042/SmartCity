package cn.xqher.smartcity.utilities

import android.content.Context
import android.widget.Toast

object Constants {

//    读取Preference后设置
    var BASE_URL = ""

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
package mobg5.g51999.foodrating.utils

import android.content.Context
import android.widget.Toast

@Suppress("NOTHING_TO_INLINE")
inline fun showToast(context: Context, text: String) {
    Toast.makeText(context, text, Toast.LENGTH_LONG).show()
}
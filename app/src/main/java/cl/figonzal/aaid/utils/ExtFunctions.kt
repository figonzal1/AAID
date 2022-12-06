package cl.figonzal.aaid.utils

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes


fun Context.toast(@StringRes resId: Int) {
    Toast.makeText(this, this.getString(resId), Toast.LENGTH_SHORT)
        .show()
}
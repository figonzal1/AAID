package cl.figonzal.aaid.utils

import android.content.Context
import android.content.Intent
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.text.AnnotatedString
import cl.figonzal.aaid.R

object Utils {

    fun copyToClipBoard(
        context: Context,
        clipboardManager: ClipboardManager,
        aaid: String
    ) {
        clipboardManager.setText(AnnotatedString(aaid))
        context.toast(R.string.copy_to_clipboard)
    }

    fun shareAAID(
        context: Context,
        clipboardManager: ClipboardManager
    ) {

        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, clipboardManager.getText())
            type = "text/plain"
        }

        val shareIntent =
            Intent.createChooser(sendIntent, context.getString(R.string.intent_chooser))
        context.startActivity(shareIntent)
    }
}
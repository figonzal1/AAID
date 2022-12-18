/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE', which is part of this source code package
 *
 * Author: Felipe González Alarcón
 * email: felipe.gonzalezalarcon94@gmail.com
 *
 * Copyright (c) 2022
 *
 * Project: AAID
 * Module: AAID.app.main
 * Last modified: 14-12-22 19:28
 */

package cl.figonzal.aaid.utils

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.text.AnnotatedString
import cl.figonzal.aaid.R


fun Context.toast(@StringRes resId: Int) {
    Toast.makeText(this, this.getString(resId), Toast.LENGTH_SHORT).show()
}

fun copyToClipBoard(clipboardManager: ClipboardManager, aaid: String) {
    clipboardManager.setText(AnnotatedString(aaid))
}

fun Context.shareAAID(clipboardManager: ClipboardManager) {

    val sendIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, clipboardManager.getText())
        type = "text/plain"
    }

    val shareIntent = Intent.createChooser(sendIntent, getString(R.string.intent_chooser))
    startActivity(shareIntent)
}

fun Context.contactIntent() {
    Intent(Intent.ACTION_SEND).apply {
        putExtra(Intent.EXTRA_EMAIL, arrayOf(getString(R.string.mail_to_felipe)))
        putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_subject))
        type = "text/plain"

        when {
            resolveActivity(packageManager) != null -> {
                startActivity(
                    Intent.createChooser(
                        this,
                        getString(R.string.email_chooser_title)
                    )
                )
            }
            else -> toast(R.string.email_intent_fail)
        }
    }
}
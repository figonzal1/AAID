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

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_SEND
import android.content.Intent.ACTION_SENDTO
import android.content.Intent.EXTRA_SUBJECT
import android.content.Intent.EXTRA_TEXT
import android.content.Intent.createChooser
import android.net.Uri
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.text.AnnotatedString
import cl.figonzal.aaid.R
import com.google.android.ump.UserMessagingPlatform
import timber.log.Timber


fun Context.toast(@StringRes resId: Int) {
    Toast.makeText(this, this.getString(resId), Toast.LENGTH_SHORT).show()
}

fun copyToClipBoard(clipboardManager: ClipboardManager, aaid: String) {
    clipboardManager.setText(AnnotatedString(aaid))
}

fun Context.shareAAID(clipboardManager: ClipboardManager) {

    val sendIntent = Intent().apply {
        action = ACTION_SEND
        putExtra(EXTRA_TEXT, clipboardManager.getText())
        type = "text/plain"
    }

    val shareIntent = createChooser(sendIntent, getString(R.string.intent_chooser))
    startActivity(shareIntent)
}

fun Context.contactIntent() {
    Intent(
        ACTION_SENDTO,
        Uri.parse(
            "mailto:${getString(R.string.mail_to_felipe)}" +
                    "?subject=${getString(R.string.email_subject)}"
        )
    ).apply {
        putExtra(EXTRA_SUBJECT, getString(R.string.email_subject))
        startActivity(createChooser(this, getString(R.string.email_chooser_title)))
    }
}

fun contactPrivacy(activity: Activity) =
    UserMessagingPlatform.showPrivacyOptionsForm(activity) { formError ->
        formError?.let {
            Timber.w("Privacy options form: ${it.message}")
        }
    }
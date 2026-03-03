/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE', which is part of this source code package
 *
 * Author: Felipe González Alarcón
 * email: felipe.gonzalezalarcon94@gmail.com
 *
 * Copyright (c) 2026
 *
 * Project: AAID
 * Module: AAID.app.main
 * Last modified: 02-03-26, 21:00
 */

package cl.figonzal.aaid.utils

import android.app.Activity
import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_SEND
import android.content.Intent.ACTION_SENDTO
import android.content.Intent.EXTRA_SUBJECT
import android.content.Intent.EXTRA_TEXT
import android.content.Intent.createChooser
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.ui.platform.ClipEntry
import androidx.compose.ui.platform.Clipboard
import androidx.core.net.toUri
import cl.figonzal.aaid.R
import com.google.android.ump.UserMessagingPlatform
import timber.log.Timber


fun Context.toast(@StringRes resId: Int) {
    Toast.makeText(this, this.getString(resId), Toast.LENGTH_SHORT).show()
}

suspend fun copyToClipBoard(clipboard: Clipboard, aaid: String) {
    clipboard.setClipEntry(ClipEntry(ClipData.newPlainText(null, aaid)))
}

fun Context.shareAAID(aaid: String) {

    val sendIntent = Intent().apply {
        action = ACTION_SEND
        putExtra(EXTRA_TEXT, aaid)
        type = "text/plain"
    }

    val shareIntent = createChooser(sendIntent, getString(R.string.intent_chooser))
    startActivity(shareIntent)
}

fun Context.contactIntent() {
    Intent(
        ACTION_SENDTO,
        ("mailto:${getString(R.string.mail_to_felipe)}" +
                "?subject=${getString(R.string.email_subject)}").toUri()
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
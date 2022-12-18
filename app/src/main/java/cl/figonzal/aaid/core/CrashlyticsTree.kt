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
 * Last modified: 15-12-22 22:46
 */

package cl.figonzal.aaid.core

import android.util.Log
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import timber.log.Timber


class CrashlyticsTree : Timber.Tree() {

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {

        when (priority) {
            Log.VERBOSE, Log.DEBUG, Log.INFO -> return
            else -> {
                Firebase.crashlytics.log(message)
                if (t != null) {
                    Firebase.crashlytics.recordException(t)
                }
            }
        }
    }
}
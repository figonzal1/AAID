package cl.figonzal.aaid

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
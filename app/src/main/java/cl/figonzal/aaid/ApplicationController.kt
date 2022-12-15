package cl.figonzal.aaid

import android.app.Application
import com.google.android.gms.ads.MobileAds
import timber.log.Timber

class ApplicationController : Application() {

    override fun onCreate() {
        super.onCreate()

        when {
            BuildConfig.DEBUG -> Timber.plant(Timber.DebugTree())
            else -> Timber.plant(CrashlyticsTree())
        }

        MobileAds.initialize(this) {}
    }
}
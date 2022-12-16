package cl.figonzal.aaid.ui.screens.main

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import timber.log.Timber

class AAIDViewModel : ViewModel() {

    var aaid by mutableStateOf("")

    fun requestAAID(context: Context, dispatcher: CoroutineDispatcher) {
        viewModelScope.launch(dispatcher) {

            val adInfo = AdvertisingIdClient.getAdvertisingIdInfo(context)

            when {
                adInfo.id != null -> {
                    aaid = adInfo.id!!
                    Timber.d("Advertising id: $aaid")
                }
                else -> {
                    Timber.e("AAID information not available")
                }
            }

        }
    }
}
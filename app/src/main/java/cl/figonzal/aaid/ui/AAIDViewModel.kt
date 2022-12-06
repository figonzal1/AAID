package cl.figonzal.aaid.ui

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class AAIDViewModel : ViewModel() {

    var aaid by mutableStateOf("")

    fun requestAAID(context: Context, dispatcher: CoroutineDispatcher) {
        viewModelScope.launch(dispatcher) {

            val adInfo = AdvertisingIdClient.getAdvertisingIdInfo(context)

            when {
                adInfo.id != null -> {
                    aaid = adInfo.id!!
                    Log.i("VIEW_MODEL", "Adverstising id: $aaid")
                }
                else -> {
                    Log.e("VIEW_MODEL", "AAID information not available")
                }
            }

        }
    }
}
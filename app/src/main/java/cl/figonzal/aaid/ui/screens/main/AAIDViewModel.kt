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

package cl.figonzal.aaid.ui.screens.main

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.IOException

class AAIDViewModel : ViewModel() {

    private var _aaid by mutableStateOf("")
    val aaid: String get() = _aaid

    fun requestAAID(context: Context, dispatcher: CoroutineDispatcher) {
        viewModelScope.launch(dispatcher) {

            try {
                val adInfo = AdvertisingIdClient.getAdvertisingIdInfo(context)
                adInfo.id?.let { id ->
                    _aaid = id
                    Timber.d("Advertising id: $id")
                } ?: Timber.e("AAID information not available")
            } catch (e: GooglePlayServicesNotAvailableException) {
                Timber.e("AAID information not available: ${e.message}")
            } catch (e: GooglePlayServicesRepairableException) {
                Timber.e("AAID information not available: ${e.message}")
            } catch (e: IOException) {
                Timber.e("AAID information not available: ${e.message}")
            }
        }
    }
}
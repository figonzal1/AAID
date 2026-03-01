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

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

sealed interface AaidState {
    data object Loading : AaidState
    data class Success(val aaid: String) : AaidState
    data class Error(val message: String) : AaidState
}

class AAIDViewModel(
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    var state: AaidState by mutableStateOf(AaidState.Loading)
        private set

    fun requestAAID(fetchAaid: suspend () -> String) {
        viewModelScope.launch(ioDispatcher) {
            state = try {
                val id = fetchAaid()
                Timber.d("Advertising id: $id")
                AaidState.Success(id)
            } catch (e: Exception) {
                Timber.e("AAID not available: ${e.message}")
                AaidState.Error(e.message ?: "Unknown error")
            }
        }
    }
}

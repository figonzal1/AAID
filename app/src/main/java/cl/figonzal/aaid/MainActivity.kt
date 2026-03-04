/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE', which is part of this source code package
 *
 * Author: Felipe González Alarcón
 * email: felipe.gonzalezalarcon94@gmail.com
 *
 * Copyright (c) 2025
 *
 * Project: AAID
 * Module: AAID.app.main
 * Last modified: 08-03-25, 00:06
 */

package cl.figonzal.aaid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import cl.figonzal.aaid.ui.screens.main.AAIDViewModel
import com.google.android.gms.ads.identifier.AdvertisingIdClient


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {

            val viewModel: AAIDViewModel = viewModel()
            val context = LocalContext.current
            LaunchedEffect(Unit) {
                viewModel.requestAAID {
                    AdvertisingIdClient.getAdvertisingIdInfo(context).id
                        ?: throw Exception("AAID not available")
                }
            }

            val navController = rememberNavController()
            AppNavHost(navController, viewModel)
        }
    }

}

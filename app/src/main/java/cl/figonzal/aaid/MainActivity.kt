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
 * Last modified: 15-12-22 22:59
 */

package cl.figonzal.aaid

import android.content.Intent.*
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import cl.figonzal.aaid.ui.navigation.NavigationItem.*
import cl.figonzal.aaid.ui.screens.main.AAIDViewModel
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import kotlinx.coroutines.Dispatchers


@OptIn(ExperimentalAnimationApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {

            val viewModel: AAIDViewModel = viewModel()
            viewModel.requestAAID(LocalContext.current, Dispatchers.IO)

            val navController = rememberAnimatedNavController()
            AppNavHost(navController, viewModel)
        }
    }
}
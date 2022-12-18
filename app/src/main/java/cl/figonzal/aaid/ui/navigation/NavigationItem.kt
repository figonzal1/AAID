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
 * Last modified: 14-12-22 19:28
 */

package cl.figonzal.aaid.ui.navigation

sealed class NavigationItem(
    val baseRoute: String
) {
    object MainNavItem : NavigationItem("main_screen")
    object SettingsNavItem : NavigationItem("settings_screen")
}

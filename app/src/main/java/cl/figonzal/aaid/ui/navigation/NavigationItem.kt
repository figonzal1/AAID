package cl.figonzal.aaid.ui.navigation

sealed class NavigationItem(
    val baseRoute: String
) {
    object MainNavItem : NavigationItem("main_screen")
    object SettingsNavItem : NavigationItem("settings_screen")
}

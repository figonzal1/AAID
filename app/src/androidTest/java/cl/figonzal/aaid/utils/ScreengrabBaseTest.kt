package cl.figonzal.aaid.utils

import org.junit.Before
import org.junit.Rule
import tools.fastlane.screengrab.Screengrab
import tools.fastlane.screengrab.UiAutomatorScreenshotStrategy
import tools.fastlane.screengrab.cleanstatusbar.BluetoothState
import tools.fastlane.screengrab.cleanstatusbar.CleanStatusBar
import tools.fastlane.screengrab.cleanstatusbar.MobileDataType
import tools.fastlane.screengrab.locale.LocaleTestRule

abstract class ScreengrabBaseTest {

    @Rule
    @JvmField
    val localeTestRule = LocaleTestRule()

    @Before
    fun setupScreengrab() {
        Screengrab.setDefaultScreenshotStrategy(UiAutomatorScreenshotStrategy())
        CleanStatusBar()
            .setBluetoothState(BluetoothState.DISCONNECTED)
            .setMobileNetworkDataType(MobileDataType.LTE)
            .setClock("0900")
            .setBatteryLevel(100)
            .enable()
    }
}

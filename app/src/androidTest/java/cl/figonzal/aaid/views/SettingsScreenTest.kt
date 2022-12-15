package cl.figonzal.aaid.views

import android.content.Context
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.platform.app.InstrumentationRegistry
import cl.figonzal.aaid.BuildConfig
import cl.figonzal.aaid.R
import cl.figonzal.aaid.ui.settings.SettingsView
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SettingsScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun setupAppNavHost() {

        composeTestRule.setContent {
            SettingsView(onNavigateUp = { }, onDevContact = {})
        }
    }

    @Test
    fun appNavHost_verifyResources() {
        composeTestRule.onRoot().printToLog("TAG")

        //About section
        composeTestRule
            .onNodeWithContentDescription(
                context.getString(R.string.cd_about),
                useUnmergedTree = true
            )
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(
                context.getString(R.string.about_app_description),
                useUnmergedTree = true
            )
            .assertIsDisplayed()

        //Version preference
        composeTestRule
            .onNodeWithText(context.getString(R.string.version), useUnmergedTree = true)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(BuildConfig.VERSION_NAME, useUnmergedTree = true)
            .assertIsDisplayed()

        //Contact preference
        composeTestRule
            .onNodeWithText(context.getString(R.string.contact_developer), useUnmergedTree = true)
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(
                context.getString(R.string.suggestions_problems),
                useUnmergedTree = true
            )
            .assertIsDisplayed()
    }
}
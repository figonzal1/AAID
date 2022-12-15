package cl.figonzal.aaid.views.setting

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.printToLog
import cl.figonzal.aaid.ui.settings.Preference
import org.junit.Rule
import org.junit.Test

class PreferenceViewTest {

    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun appNavHost_verifyResources_whenTitleIsPresent() {

        composeTestRule.setContent {
            Preference(title = "Test title", subTitle = "test subtitle", isTitlePresent = true) {}
        }

        composeTestRule.onRoot().printToLog("TAG")

        //About section
        composeTestRule
            .onNodeWithText("test subtitle")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Test title")
            .assertIsDisplayed()

    }

    @Test
    fun appNavHost_verifyResources_whenTitleIsAusent() {

        composeTestRule.setContent {
            Preference(title = "Test title", subTitle = "test subtitle", isTitlePresent = false) {}
        }

        composeTestRule.onRoot().printToLog("TAG")

        //About section
        composeTestRule
            .onNodeWithText("test subtitle")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Test title")
            .assertDoesNotExist()

    }
}
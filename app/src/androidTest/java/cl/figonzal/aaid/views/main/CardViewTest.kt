package cl.figonzal.aaid.views.main

import android.content.Context
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import cl.figonzal.aaid.BuildConfig
import cl.figonzal.aaid.R
import cl.figonzal.aaid.ui.main.CardAAID
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CardViewTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun setupAppNavHost() {

        composeTestRule.setContent {
            CardAAID(aaid = "01bv0b8c-578c-4e26-bb5a-10ca1ad1abe1") { }
        }
    }

    @Test
    fun cardView_verifyResources() {

        //Card view text
        composeTestRule
            .onNodeWithText(context.getString(R.string.cv_title))
            .assertIsDisplayed()

        //Description text
        composeTestRule
            .onNodeWithText(context.getString(R.string.cv_subtitle))
            .assertIsDisplayed()

        //Version text
        composeTestRule
            .onNodeWithText("v${BuildConfig.VERSION_NAME}")
            .assertIsDisplayed()

        //Background image in card view
        composeTestRule
            .onNodeWithContentDescription(context.getString(R.string.cd_background_image))
            .assertIsDisplayed()

        //Settings button
        composeTestRule
            .onNodeWithContentDescription(context.getString(R.string.cd_about_button))
            .assertIsDisplayed()

        //Buttons
        composeTestRule
            .onNodeWithText(context.getString(R.string.btn_copy))
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(context.getString(R.string.btn_share))
            .assertIsDisplayed()
    }
}
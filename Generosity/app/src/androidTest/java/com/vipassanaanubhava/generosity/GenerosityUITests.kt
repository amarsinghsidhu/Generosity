package com.vipassanaanubhava.generosity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.vipassanaanubhava.generosity.ui.theme.GenerosityTheme
import com.vipassanaanubhava.generosity.Render
import com.vipassanaanubhava.generosity.generosity
import com.vipassanaanubhava.generosity.onBack
import com.vipassanaanubhava.generosity.onBenefits
import com.vipassanaanubhava.generosity.onCalculation
import com.vipassanaanubhava.generosity.onFixPortion
import com.vipassanaanubhava.generosity.onRoundUpHundreds
import com.vipassanaanubhava.generosity.onRoundUpOnes
import com.vipassanaanubhava.generosity.onRoundUpTens
import com.vipassanaanubhava.generosity.onRoundUpThousands
import com.vipassanaanubhava.generosity.onVariablePortion
import com.vipassanaanubhava.generosity.touchHandling
import org.junit.Rule
import org.junit.Test
import java.text.NumberFormat

class GenerosityUITests {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun render_indexPageBack_from_benefitsPage() {
        generosity.landingPage = false
        generosity.fixPortion = false
        generosity.variablePortion = false
        generosity.benefitsPage = true
        composeTestRule.setContent {
            GenerosityTheme {
                Render(generosity)

                onBack = { generosity ->
                    touchHandling(
                        generosity, "Back"
                    )
                }
            }
        }
        composeTestRule.onNodeWithText("Back").performClick()

        composeTestRule.onNodeWithText("Benefits").assertIsDisplayed()
        composeTestRule.onNodeWithText("Daswandh Calculation").assertIsDisplayed()
        composeTestRule.onNodeWithText("Generosity Calculation").assertIsDisplayed()
    }

    @Test
    fun render_indexPageBack_from_DaswandhCalculationPage() {
        generosity.landingPage = false
        generosity.fixPortion = true
        generosity.variablePortion = false
        generosity.benefitsPage = false
        composeTestRule.setContent {
            GenerosityTheme {
                Render(generosity)

                onBack = {
                        generosity ->
                    touchHandling(
                        generosity, "Back"
                    )
                }
            }
        }
        composeTestRule.onNodeWithText("Back").performClick()

        composeTestRule.onNodeWithText("Benefits").assertIsDisplayed()
        composeTestRule.onNodeWithText("Daswandh Calculation").assertIsDisplayed()
        composeTestRule.onNodeWithText("Generosity Calculation").assertIsDisplayed()
    }

    @Test
    fun render_indexPageBack_from_variablePortionCalculationPage() {
        generosity.landingPage = false
        generosity.fixPortion = false
        generosity.variablePortion = true
        generosity.benefitsPage = false
        composeTestRule.setContent {
            GenerosityTheme {
                Render(generosity)

                onBack = {
                        generosity ->
                    touchHandling(
                        generosity, "Back"
                    )
                }
            }
        }
        composeTestRule.onNodeWithText("Back").performClick()

        composeTestRule.onNodeWithText("Benefits").assertIsDisplayed()
        composeTestRule.onNodeWithText("Daswandh Calculation").assertIsDisplayed()
        composeTestRule.onNodeWithText("Generosity Calculation").assertIsDisplayed()
    }

    @Test
    fun render_calculationPage_fixPortion_noRoundUp() {
        generosity.landingPage = false
        generosity.fixPortion = true
        generosity.variablePortion = false
        generosity.benefitsPage = false
        generosity.roundUpOnes = false
        generosity.roundUpTens = false
        generosity.roundUpHundreds = false
        generosity.roundUpThousands = false
        composeTestRule.setContent {
            GenerosityTheme {
                Render(generosity)

                onCalculation = {
                    generosity ->
                    touchHandling(
                        generosity, "Calculation"
                    )
                }
            }
        }

        composeTestRule.onNodeWithText("Income").performTextInput("18043")
        composeTestRule.onNodeWithText("Calculation").performClick()
        val targetDonation = NumberFormat.getCurrencyInstance().format(1804.3)
        composeTestRule.onNodeWithText("Donation Amount: $targetDonation")
            .assertExists("No node with such text.")
    }

    @Test
    fun render_calculationPage_variablePortion_noRoundUp() {
        generosity.landingPage = false
        generosity.fixPortion = false
        generosity.variablePortion = true
        generosity.benefitsPage = false
        composeTestRule.setContent {
            GenerosityTheme {
                Render(generosity)

                onVariablePortion = {
                        generosity ->
                    touchHandling(
                        generosity, "Variable Portion"
                    )
                }
            }
        }

        composeTestRule.onNodeWithText("Income").performTextInput("18043")
        composeTestRule.onNodeWithText("Portion").performTextInput("10")
        composeTestRule.onNodeWithText("1x").assertIsDisplayed()
        composeTestRule.onNodeWithText("10x").assertIsDisplayed()
        composeTestRule.onNodeWithText("100x").assertIsDisplayed()
        composeTestRule.onNodeWithText("1000x").assertIsDisplayed()
        composeTestRule.onNodeWithText("Calculation").assertIsDisplayed()
        composeTestRule.onNodeWithText("Back").assertIsDisplayed()
    }

    // Start of Index(...) function tests
    @Test
    fun indexRendering() {
        generosity.landingPage = true
        generosity.fixPortion = false
        generosity.variablePortion = false
        generosity.benefitsPage = false
        composeTestRule.setContent {
            GenerosityTheme {
                Render(generosity)
            }
        }
        composeTestRule.onNodeWithText("Benefits").assertIsDisplayed()
        composeTestRule.onNodeWithText("Daswandh Calculation").assertIsDisplayed()
        composeTestRule.onNodeWithText("Generosity Calculation").assertIsDisplayed()
    }

    @Test
    fun indexRendering_to_benefitsPage() {
        generosity.landingPage = true
        generosity.fixPortion = false
        generosity.variablePortion = false
        generosity.benefitsPage = false
        composeTestRule.setContent {
            GenerosityTheme {
                Render(generosity)

                onBenefits = {
                        generosity ->
                    touchHandling(
                        generosity, "Benefits"
                    )
                }
            }
        }
        composeTestRule.onNodeWithText("Benefits").performClick()
        assert(generosity.benefitsPage)
    }

    @Test
    fun benefitsRendering() {
        generosity.landingPage = false
        generosity.fixPortion = false
        generosity.variablePortion = false
        generosity.benefitsPage = true
        composeTestRule.setContent {
            GenerosityTheme {
                Render(generosity)
            }
        }
        composeTestRule.onNodeWithText("A Gift With Six Factors").assertIsDisplayed()
        composeTestRule.onNodeWithText("Chaḷaṅgadānasutta").assertIsDisplayed()
        composeTestRule.onNodeWithText("Six factors of a religious donation:").assertIsDisplayed()
        composeTestRule.onNodeWithText("1. Three factors of the donor").assertIsDisplayed()
        composeTestRule.onNodeWithText(
            "Before Donation:",
            substring = true
        ).assertIsDisplayed()
        composeTestRule.onNodeWithText("2. Three factors of the recipient").assertIsDisplayed()
        composeTestRule.onNodeWithText(
            "No greed",
            substring = true
        ).assertIsDisplayed()
        composeTestRule.onNodeWithText(
            "measurement",
            substring = true
        ).assertIsDisplayed()
        composeTestRule.onNodeWithText("- Source: Aṅguttara Nikāya 6.37").assertIsDisplayed()
        composeTestRule.onNodeWithText("Back").assertIsDisplayed()
    }

    // Start of Render(...) function tests
    @Test
    fun calculationRendering_variablePortion_noRoundUp() {
        generosity.landingPage = false
        generosity.fixPortion = false
        generosity.variablePortion = true
        generosity.benefitsPage = false
        generosity.roundUpOnes = false
        generosity.roundUpTens = false
        generosity.roundUpHundreds = false
        generosity.roundUpThousands = false
        composeTestRule.setContent {
            GenerosityTheme {
                Render(generosity)

                onCalculation = {
                        generosity ->
                    touchHandling(generosity, "Calculation")
                }
            }
        }
        composeTestRule.onNodeWithText("Income").performTextInput("18043")
        composeTestRule.onNodeWithText("Portion").performTextInput("10")

        composeTestRule.onNodeWithText("Calculation").performClick()
        val targetDonation = NumberFormat.getCurrencyInstance().format(1804.3)
        composeTestRule.onNodeWithText("Donation Amount: $targetDonation")
            .assertExists("No node with such text.")
    }

    @Test
    fun calculationRendering_variablePortion_RoundUpOnes() {
        generosity.landingPage = false
        generosity.fixPortion = false
        generosity.variablePortion = true
        generosity.benefitsPage = false
        composeTestRule.setContent {
            GenerosityTheme {
                Render(generosity)

                onRoundUpOnes = { generosity -> touchHandling(generosity, "Ones") }
                onCalculation = {
                        generosity ->
                    touchHandling(generosity, "Calculation")
                }
            }
        }

        composeTestRule.onNodeWithText("Income").performTextInput("18043")
        composeTestRule.onNodeWithText("Portion").performTextInput("10")
        composeTestRule.onNodeWithText("1x").performClick()

        composeTestRule.onNodeWithText("Calculation").performClick()
        val targetDonation = NumberFormat.getCurrencyInstance().format(1805)
        composeTestRule.onNodeWithText("Donation Amount: $targetDonation")
            .assertExists("No node with such text.")


    }

    @Test
    fun calculationRendering_variablePortion_roundUpTens() {
        generosity.landingPage = false
        generosity.fixPortion = false
        generosity.variablePortion = true
        generosity.benefitsPage = false
        composeTestRule.setContent {
            GenerosityTheme {
                Render(generosity)

                onRoundUpTens = { generosity -> touchHandling(generosity, "Tens") }
                onCalculation = {
                        generosity ->
                    touchHandling(generosity, "Calculation")
                }
            }
        }

        composeTestRule.onNodeWithText("Income").performTextInput("18043")
        composeTestRule.onNodeWithText("Portion").performTextInput("10")
        composeTestRule.onNodeWithText("10x").performClick()

        composeTestRule.onNodeWithText("Calculation").performClick()
        val targetDonation = NumberFormat.getCurrencyInstance().format(1810)
        composeTestRule.onNodeWithText("Donation Amount: $targetDonation")
            .assertExists("No node with such text.")


    }

    @Test
    fun calculationRendering_variablePortion_roundUpHundreds() {
        generosity.landingPage = false
        generosity.fixPortion = false
        generosity.variablePortion = true
        generosity.benefitsPage = false
        composeTestRule.setContent {
            GenerosityTheme {
                Render(generosity)

                onRoundUpHundreds = {
                        generosity ->
                    touchHandling(generosity, "Hundreds")
                }
                onCalculation = {
                        generosity ->
                    touchHandling(generosity, "Calculation")
                }
            }
        }

        composeTestRule.onNodeWithText("Income").performTextInput("18043")
        composeTestRule.onNodeWithText("Portion").performTextInput("10")
        composeTestRule.onNodeWithText("100x").performClick()

        composeTestRule.onNodeWithText("Calculation").performClick()
        val targetDonation = NumberFormat.getCurrencyInstance().format(1900)
        composeTestRule.onNodeWithText("Donation Amount: $targetDonation")
            .assertExists("No node with such text.")


    }

    @Test
    fun calculationRendering_variablePortion_onRoundUpThousands() {
        generosity.landingPage = false
        generosity.fixPortion = false
        generosity.variablePortion = true
        generosity.benefitsPage = false
        composeTestRule.setContent {
            GenerosityTheme {
                Render(generosity)

                onRoundUpThousands = {
                        generosity ->
                    touchHandling(
                        generosity, "Thousands"
                    )
                }
                onCalculation = {
                        generosity ->
                    touchHandling(
                        generosity, "Calculation"
                    )
                }
            }
        }

        composeTestRule.onNodeWithText("Income").performTextInput("18043")
        composeTestRule.onNodeWithText("Portion").performTextInput("10")
        composeTestRule.onNodeWithText("1000x").performClick()

        composeTestRule.onNodeWithText("Calculation").performClick()
        val targetDonation = NumberFormat.getCurrencyInstance().format(2000)
        composeTestRule.onNodeWithText("Donation Amount: $targetDonation")
            .assertExists("No node with such text.")


    }

    // End of Render(...) function Tests

    // Start of Buttons(...) function testing
    @Test
    fun calculationButtons_Ones() {
        generosity.landingPage = false
        generosity.fixPortion = false
        generosity.variablePortion = true
        generosity.benefitsPage = false
        composeTestRule.setContent {
            GenerosityTheme {

                Render(generosity)

                onRoundUpOnes = { generosity -> touchHandling(generosity, "Ones") }
            }
        }

        composeTestRule.onNodeWithText("1x").performClick()
        assert(generosity.roundUpOnes)
    }

    @Test
    fun calculationButtons_Tens() {
        generosity.landingPage = false
        generosity.fixPortion = false
        generosity.variablePortion = true
        generosity.benefitsPage = false
        composeTestRule.setContent {
            GenerosityTheme {

                Render(generosity)

                onRoundUpTens = { generosity -> touchHandling(generosity, "Tens") }
            }
        }

        composeTestRule.onNodeWithText("10x").performClick()
        assert(generosity.roundUpTens)
    }

    @Test
    fun calculationButtons_Hundreds() {
        generosity.landingPage = false
        generosity.fixPortion = false
        generosity.variablePortion = true
        generosity.benefitsPage = false
        composeTestRule.setContent {
            GenerosityTheme {

                Render(generosity)

                onRoundUpHundreds = {
                        generosity ->
                    touchHandling(
                        generosity, "Hundreds"
                    )
                }
            }
        }

        composeTestRule.onNodeWithText("100x").performClick()
        assert(generosity.roundUpHundreds)
    }

    @Test
    fun calculationButtons_Thousands() {
        generosity.landingPage = false
        generosity.fixPortion = false
        generosity.variablePortion = true
        generosity.benefitsPage = false
        composeTestRule.setContent {
            GenerosityTheme {

                Render(generosity)

                onRoundUpThousands = {
                        generosity ->
                    touchHandling(
                        generosity, "Thousands"
                    )
                }
            }
        }

        composeTestRule.onNodeWithText("1000x").performClick()
        assert(generosity.roundUpThousands)
    }

    @Test
    fun navigationButtons_indexPage_to_benefitsPage() {
        generosity.landingPage = true
        generosity.fixPortion = false
        generosity.variablePortion = false
        generosity.benefitsPage = false
        composeTestRule.setContent {
            GenerosityTheme {

                Render(generosity)

                onBenefits = { generosity -> touchHandling(generosity, "Benefits") }
            }
        }

        composeTestRule.onNodeWithText("Benefits").performClick()
        assert(generosity.benefitsPage)
    }

    @Test
    fun navigationButtons_indexPage_to_fixPortionPage() {
        generosity.landingPage = true
        generosity.fixPortion = false
        generosity.variablePortion = false
        generosity.benefitsPage = false
        composeTestRule.setContent {
            GenerosityTheme {

                Render(generosity)

                onFixPortion = {
                        generosity ->
                    touchHandling(
                        generosity, "Fix Portion"
                    )
                }
            }
        }

        composeTestRule.onNodeWithText("Daswandh Calculation").performClick()
        assert(generosity.fixPortion)
    }

    @Test
    fun navigationButtons_indexPage_to_variablePortionPage() {
        generosity.landingPage = true
        generosity.fixPortion = false
        generosity.variablePortion = false
        generosity.benefitsPage = false
        composeTestRule.setContent {
            GenerosityTheme {

                Render(generosity)

                onVariablePortion = {
                        generosity ->
                    touchHandling(
                        generosity, "Variable Portion"
                    )
                }
            }
        }

        composeTestRule.onNodeWithText("Generosity Calculation").performClick()
        assert(generosity.variablePortion)
    }

    @Test
    fun navigationButtons_backButton() {
        generosity.landingPage = true
        generosity.fixPortion = false
        generosity.variablePortion = false
        generosity.benefitsPage = false
        composeTestRule.setContent {
            GenerosityTheme {

                Render(generosity)

                onVariablePortion = {
                        generosity ->
                    touchHandling(
                        generosity, "Variable Portion"
                    )
                }
                onFixPortion = {
                        generosity ->
                    touchHandling(
                        generosity, "Fix Portion"
                    )
                }
                onBenefits = {
                        generosity ->
                    touchHandling(
                        generosity, "Benefits"
                    )
                }
                onBack = { generosity -> touchHandling(generosity, "Back") }
            }
        }
        composeTestRule.onNodeWithText("Benefits").performClick()
        composeTestRule.onNodeWithText("Back").performClick()
        assert(generosity.landingPage)

        composeTestRule.onNodeWithText("Daswandh Calculation").performClick()
        composeTestRule.onNodeWithText("Back").performClick()
        assert(generosity.landingPage)

        composeTestRule.onNodeWithText("Generosity Calculation").performClick()
        composeTestRule.onNodeWithText("Back").performClick()
        assert(generosity.landingPage)
    }
}
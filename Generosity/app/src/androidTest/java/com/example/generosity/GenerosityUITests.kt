package com.example.generosity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.example.generosity.ui.theme.GenerosityTheme
import org.junit.Rule
import org.junit.Test
import java.text.NumberFormat

class GenerosityUITests {

    @get:Rule
    val composeTestRule = createComposeRule()

    // Start of Render(...) function tests
    @Test
    fun render_noRoundUp() {
        composeTestRule.setContent {
            GenerosityTheme {
                var generosity by remember { mutableStateOf(randomColor()) }

                Render(generosity)

                onCalculation = {
                    generosity -> touchHandling(generosity, "Calculation")
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
    fun render_RoundUpOnes() {
        composeTestRule.setContent {
            GenerosityTheme {
                var generosity by remember { mutableStateOf(randomColor()) }

                Render(generosity)

                onRoundUpOnes = { generosity -> touchHandling(generosity, "Ones") }
                onCalculation = {
                    generosity -> touchHandling(generosity, "Calculation")
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
    fun render_roundUpTens() {
        composeTestRule.setContent {
            GenerosityTheme {
                var generosity by remember { mutableStateOf(randomColor()) }

                Render(generosity)

                onRoundUpTens = { generosity -> touchHandling(generosity, "Tens") }
                onCalculation = {
                    generosity -> touchHandling(generosity, "Calculation")
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
    fun render_roundUpHundreds() {
        composeTestRule.setContent {
            GenerosityTheme {
                var generosity by remember { mutableStateOf(randomColor()) }

                Render(generosity4)

                onRoundUpHundreds = {
                    generosity -> touchHandling(generosity, "Hundreds")
                }
                onCalculation = {
                    generosity -> touchHandling(generosity, "Calculation")
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
    fun render_onRoundUpThousands() {
        composeTestRule.setContent {
            GenerosityTheme {
                Render(generosity5)
                onRoundUpThousands = { generosity -> touchHandling(generosity, "Thousands") }
                onCalculation = { generosity -> touchHandling(generosity, "Calculation") }
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

    // Start of TextFieldModule(...) function testing
    @Test
    fun textFieldModule() {
        composeTestRule.setContent {
            GenerosityTheme {
                BigBang()
            }
        }

        composeTestRule.onNodeWithText("Income").performTextInput("${generosity.income}")
    }
    // End of TextFieldModule(...) function testing

    // Start of Buttons(...) function testing
    @Test
    fun buttons_Ones() {
        composeTestRule.setContent {
            GenerosityTheme {
                var generosity by remember { mutableStateOf(randomColor()) }

                Render(generosity)

                onRoundUpOnes = { generosity -> touchHandling(generosity, "Ones") }
            }
        }

        composeTestRule.onNodeWithText("1x").performClick()
        assert(generosity.roundUpOnes)
    }

    @Test
    fun buttons_Tens() {

        composeTestRule.setContent {
            GenerosityTheme {
                var generosity by remember { mutableStateOf(randomColor()) }

                Render(generosity)

                onRoundUpTens = { generosity -> touchHandling(generosity, "Tens") }
            }
        }

        composeTestRule.onNodeWithText("10x").performClick()
        assert(generosity.roundUpTens)
    }

    @Test
    fun buttons_Hundreds() {
        composeTestRule.setContent {
            GenerosityTheme {
                var generosity by remember { mutableStateOf(randomColor()) }

                Render(generosity)

                onRoundUpHundreds = { generosity -> touchHandling(generosity, "Hundreds") }
            }
        }

        composeTestRule.onNodeWithText("100x").performClick()
        assert(generosity.roundUpHundreds)
    }

    @Test
    fun buttons_Thousands() {
        composeTestRule.setContent {
            GenerosityTheme {
                var generosity by remember { mutableStateOf(randomColor()) }

                Render(generosity)

                onRoundUpThousands = { generosity -> touchHandling(generosity, "Thousands") }
            }
        }

        composeTestRule.onNodeWithText("1000x").performClick()
        assert(generosity.roundUpThousands)
    }
}
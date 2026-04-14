package com.vipassanaanubhava.generosity

import junit.framework.TestCase.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class GenerosityTests {

    @Test
    fun touchHandling_roundUpOnes() {
        val keyEvent = "Ones"
        touchHandling(generosity1, keyEvent)
        assertEquals(true, generosity1.roundUpOnes)
    }

    @Test
    fun touchHandling_roundUpTens() {
        val keyEvent = "Tens"
        touchHandling(generosity2, keyEvent)
        assertEquals(true, generosity2.roundUpTens)
    }

    @Test
    fun touchHandling_roundUpHundreds() {
        val keyEvent = "Hundreds"
        touchHandling(generosity3, keyEvent)
        assertEquals(true, generosity3.roundUpHundreds)
    }

    @Test
    fun touchHandling_roundUpThousands() {
        val keyEvent = "Thousands"
        touchHandling(generosity4, keyEvent)
        assertEquals(true, generosity4.roundUpThousands)
    }

    @Test
    fun touchHandling_roundUpHundreds_roundUpTensOn() {
        val keyEvent = "Hundreds"
        touchHandling(generosity1, keyEvent)
        assertEquals(false, generosity1.roundUpTens)
        assertEquals(true, generosity1.roundUpHundreds)
    }

    @Test
    fun touchHandling_roundUpHundreds_roundUpTensOn_roundUpThousandsOn() {
        val keyEvent = "Thousands"
        generosity4.roundUpOnes = true
        generosity4.roundUpTens = true
        generosity4.roundUpThousands = true
        touchHandling(generosity4, keyEvent)
        assertEquals(false, generosity3.roundUpTens)
        assertEquals(false, generosity3.roundUpThousands)
        assertEquals(true, generosity3.roundUpHundreds)
    }

    @Test
    fun touchHandling_benefits() {
        val keyEvent = "Benefits"
        touchHandling(generosity, keyEvent)
        assertEquals(true, generosity.benefitsPage)
    }

    @Test
    fun touchHandling_fixPortionTen() {
        val keyEvent = "Fix Portion 10"
        touchHandling(generosity, keyEvent)
        assertEquals(true, generosity.fixPortionTen)
    }

    @Test
    fun touchHandling_fixPortionTwoFive() {
        val keyEvent = "Fix Portion 2.5"
        touchHandling(generosity, keyEvent)
        assertEquals(true, generosity.fixPortionTwoFive)
    }

    @Test
    fun touchHandling_variablePortion() {
        val keyEvent = "Variable Portion"
        touchHandling(generosity, keyEvent)
        assertEquals(true, generosity.variablePortion)
    }

    @Test
    fun touchHandling_backButton() {
        val keyEvent = "Back"
        touchHandling(generosity, keyEvent)
        assertEquals(true, generosity.landingPage)
    }

    //Start of toggle() function testing
    @Test
    fun toggle_generosity1() {
        val toggle1 = toggle(false)
        assertEquals(true, toggle1)
    }
    //End of toggle() function testing

    @Test
    fun calculation_noRoundUp() {
        val targetDonation = 1804.3
        val actualDonation = calculation(
            18043.0,
            10.0,
            false,
            false,
            false,
            false
        )
        assertEquals(targetDonation, actualDonation, 0.0001)
    }

    @Test
    fun calculation_roundUpOnes() {
        val targetDonation = 1805.0
        val actualDonation = calculation(
            18043.0,
            10.0,
            true,
            false,
            false,
            false
        )
        assertEquals(targetDonation, actualDonation, 0.0001)
    }

    @Test
    fun calculation_roundUpTens() {
        val targetDonation = 1810.0
        val actualDonation = calculation(
            18043.0,
            10.0,
            false,
            true,
            false,
            false
        )
        assertEquals(targetDonation, actualDonation, 0.0001)
    }

    @Test
    fun calculation_roundUpHundreds() {
        val targetDonation = 1900.0
        val actualDonation = calculation(
            18043.0,
            10.0,
            false,
            false,
            true,
            false
        )
        assertEquals(targetDonation, actualDonation, 0.0001)
    }

    @Test
    fun calculation_roundUpThousands() {
        val targetDonation = 2000.0
        val actualDonation = calculation(
            18043.0,
            10.0,
            false,
            false,
            false,
            true
        )
        assertEquals(targetDonation, actualDonation, 0.0001)
    }

    @Test
    fun calculation_fixPortionTen_noRoundUp() {
        val targetDonation = 1804.3
        val actualDonation = calculation(
            18043.0,
            10.0,
            false,
            false,
            false,
            false
        )
        assertEquals(targetDonation, actualDonation, 0.0001)
    }

    @Test
    fun calculation_fixPortionTwoFive_noRoundUp() {
        val targetDonation = 451.075
        val actualDonation = calculation(
            18043.0,
            2.5,
            false,
            false,
            false,
            false
        )
        assertEquals(targetDonation, actualDonation, 0.0001)
    }

    @Test
    fun randomColor_generosity1() {
        randomColor()
        assertNotEquals(generosity1.backgroundColor, null)
        assertNotEquals(generosity1.buttonsColor, null)
        assertNotEquals(generosity1.buttonsTextColor, null)
        assertNotEquals(generosity1.otherTextColor, null)
    }
}
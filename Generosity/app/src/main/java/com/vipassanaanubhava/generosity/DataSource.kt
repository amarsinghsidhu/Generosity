package com.vipassanaanubhava.generosity

import com.vipassanaanubhava.generosity.Generosity

// Examples
class DataSource {
    val listOfGenerosity = listOf(
        Generosity(
            income = 0.0,
            portion = 0.0,
            donation = 0.0,
            roundUpOnes = false,
            roundUpTens = false,
            roundUpHundreds = false,
            roundUpThousands = false,
            landingPage = true,
            variablePortion = false,
            fixPortionTen = false,
            fixPortionTwoFive = false,
            benefitsPage = false
            ),

        Generosity(
            income = 18043.0,
            portion = 10.0,
            donation = 1804.3,
            roundUpOnes = false,
            roundUpTens = false,
            roundUpHundreds = false,
            roundUpThousands = false,
            landingPage = false,
            variablePortion = true,
            fixPortionTen = false,
            fixPortionTwoFive = false,
            benefitsPage = false
            ),

        Generosity(
            income = 18043.0,
            portion = 10.0,
            donation = 1805.0,
            roundUpOnes = true,
            roundUpTens = false,
            roundUpHundreds = false,
            roundUpThousands = false,
            landingPage = false,
            variablePortion = true,
            fixPortionTen = false,
            fixPortionTwoFive = false,
            benefitsPage = false
            ),

        Generosity(
            income = 18043.0,
            portion = 10.0,
            donation = 1810.0,
            roundUpOnes = false,
            roundUpTens = true,
            roundUpHundreds = false,
            roundUpThousands = false,
            landingPage = false,
            variablePortion = true,
            fixPortionTen = false,
            fixPortionTwoFive = false,
            benefitsPage = false
            ),

        Generosity(
            income = 18043.0,
            portion = 10.0,
            donation = 1900.0,
            roundUpOnes = false,
            roundUpTens = false,
            roundUpHundreds = true,
            roundUpThousands = false,
            landingPage = false,
            variablePortion = true,
            fixPortionTen = false,
            fixPortionTwoFive = false,
            benefitsPage = false
            ),

        Generosity(
            income = 18043.0,
            portion = 10.0,
            donation = 2000.0,
            roundUpOnes = false,
            roundUpTens = false,
            roundUpHundreds = false,
            roundUpThousands = true,
            landingPage = false,
            variablePortion = true,
            fixPortionTen = false,
            fixPortionTwoFive = false,
            benefitsPage = false
            )
    )
}
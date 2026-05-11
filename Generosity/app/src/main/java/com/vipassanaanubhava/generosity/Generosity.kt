package com.vipassanaanubhava.generosity

import androidx.annotation.ColorRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

//Data Definition Section
//Compound data type declaration:
class Generosity (
    income: Double,
    portion: Double,
    donation: Double,
    roundUpOnes: Boolean,
    roundUpTens: Boolean,
    roundUpHundreds: Boolean,
    roundUpThousands: Boolean,
    landingPage: Boolean,
    variablePortion: Boolean,
    fixPortionTen: Boolean,
    fixPortionTwoFive: Boolean,
    benefitsPage: Boolean
) {
    var income by mutableDoubleStateOf(income)
    var portion by mutableDoubleStateOf(portion)
    var donation by mutableDoubleStateOf(donation)
    var roundUpOnes by mutableStateOf(roundUpOnes)
    var roundUpTens by mutableStateOf(roundUpTens)
    var roundUpHundreds by mutableStateOf(roundUpHundreds)
    var roundUpThousands by mutableStateOf(roundUpThousands)
    var landingPage by mutableStateOf(landingPage)
    var variablePortion by mutableStateOf(variablePortion)
    var fixPortionTen by mutableStateOf(fixPortionTen)
    var fixPortionTwoFive by mutableStateOf(fixPortionTwoFive)
    var benefitsPage by mutableStateOf(benefitsPage)
}

// Generosity as
//        val generosity: Generosity = Generosity(
//                - Number[0, Infinite)
//                - Number[0, 100]
//                - Number[0, Infinite)
//                - Boolean
//                - Boolean
//                - Boolean
//                - Boolean
//                - Boolean
//                - Boolean
//                - Boolean
//                - Boolean
//                - Boolean
//                - Boolean
//                - Boolean
//        )
// Interpretation:
//  - Income = 1000
//  - Portion = 10
//  - Donation Amount = 100
//  - Round Up Level 1 (Upper 1's place) = false
//  - Round Up Level 2 (Upper 10's place) = false
//  - Round Up Level 3 (Upper 100's place) = false
//  - Round Up Level 4 (Upper 1000's place) = false
//  - Portion = [1, 100]
//  - Portion = 10 (fix)
//  - Landing Page of the app
//  - Full Generosity calculation Page
//  - Daswandh calculation Page
//  - Zakat Calculation Page
//  - Benefits of Generosity Page

/* Data Template
fun fnForGenerosity(generosity: Generosity) {
    ... generosity.income
        generosity.portion
        generosity.roundUpOnes
        generosity.roundUpTens
        generosity.roundUpHundreds
        generosity.roundUpThousands
        generosity.landingPage
        generosity.variablePortion
        generosity.fixPortionTen
        generosity.fixPortionTwoFive
        generosity.benefitsPage
}
*/

// Template rules in use:
//  - compound: 11 fields


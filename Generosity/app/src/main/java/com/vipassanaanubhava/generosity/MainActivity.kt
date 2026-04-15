package com.vipassanaanubhava.generosity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vipassanaanubhava.generosity.ui.theme.GenerosityTheme
import org.jetbrains.annotations.VisibleForTesting
import java.text.NumberFormat
import kotlin.math.ceil

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
    colorMode: Boolean,
    @ColorRes backgroundColor: Int,
    @ColorRes buttonsColor: Int,
    @ColorRes buttonsTextColor: Int,
    @ColorRes otherTextColor: Int,
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
    var colorMode by mutableStateOf(colorMode)
    var backgroundColor by mutableIntStateOf(backgroundColor)
    var buttonsColor by mutableIntStateOf(buttonsColor)
    var buttonsTextColor by mutableIntStateOf(buttonsTextColor)
    var otherTextColor by mutableIntStateOf(otherTextColor)
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
//                - Color [Random]
//                - Color [Random]
//                - Color [Random]
//                - Color [Random]
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
//  - colorMode (true for light and false for dark) = true
//  - background color = Ochre
//  - Buttons color = Dark molasses
//  - Button text color = Ochre
//  - Other text color = Dark molasses
//  - Landing Page of the app
//  - Full Generosity calculation Page
//  - Daswandh calculation Page
//  - Zakat Calculation Page
//  - Benefits of Generosity Page

//Examples
var generosity = Generosity(
    income = 0.0,
    portion = 0.0,
    donation = 0.0,
    roundUpOnes = false,
    roundUpTens = false,
    roundUpHundreds = false,
    roundUpThousands = false,
    colorMode = true,
    backgroundColor = R.color.ochre,
    buttonsColor = R.color.dark_molasses,
    buttonsTextColor = R.color.ochre,
    otherTextColor = R.color.dark_molasses,
    landingPage = true,
    variablePortion = true,
    fixPortionTen = false,
    fixPortionTwoFive = false,
    benefitsPage = false
)

var generosity1 = Generosity(
    income = 18043.0,
    portion = 10.0,
    donation = 1804.3,
    roundUpOnes = false,
    roundUpTens = false,
    roundUpHundreds = false,
    roundUpThousands = false,
    colorMode = true,
    backgroundColor = R.color.ochre,
    buttonsColor = R.color.dark_molasses,
    buttonsTextColor = R.color.ochre,
    otherTextColor = R.color.dark_molasses,
    landingPage = false,
    variablePortion = true,
    fixPortionTen = false,
    fixPortionTwoFive = false,
    benefitsPage = false
)

var generosity2 = Generosity(
    income = 18043.0,
    portion = 10.0,
    donation = 1805.0,
    roundUpOnes = true,
    roundUpTens = false,
    roundUpHundreds = false,
    roundUpThousands = false,
    colorMode = true,
    backgroundColor = R.color.ochre,
    buttonsColor = R.color.dark_molasses,
    buttonsTextColor = R.color.ochre,
    otherTextColor = R.color.dark_molasses,
    landingPage = false,
    variablePortion = true,
    fixPortionTen = false,
    fixPortionTwoFive = false,
    benefitsPage = false
)

var generosity3 = Generosity(
    income = 18043.0,
    portion = 10.0,
    donation = 1810.0,
    roundUpOnes = false,
    roundUpTens = true,
    roundUpHundreds = false,
    roundUpThousands = false,
    colorMode = true,
    backgroundColor = R.color.ochre,
    buttonsColor = R.color.dark_molasses,
    buttonsTextColor = R.color.ochre,
    otherTextColor = R.color.dark_molasses,
    landingPage = false,
    variablePortion = true,
    fixPortionTen = false,
    fixPortionTwoFive = false,
    benefitsPage = false
)

var generosity4 = Generosity(
    income = 18043.0,
    portion = 10.0,
    donation = 1900.0,
    roundUpOnes = false,
    roundUpTens = false,
    roundUpHundreds = true,
    roundUpThousands = false,
    colorMode = true,
    backgroundColor = R.color.ochre,
    buttonsColor = R.color.dark_molasses,
    buttonsTextColor = R.color.ochre,
    otherTextColor = R.color.dark_molasses,
    landingPage = false,
    variablePortion = true,
    fixPortionTen = false,
    fixPortionTwoFive = false,
    benefitsPage = false
)

var generosity5 = Generosity(
    income = 18043.0,
    portion = 10.0,
    donation = 2000.0,
    roundUpOnes = false,
    roundUpTens = false,
    roundUpHundreds = false,
    roundUpThousands = true,
    colorMode = true,
    backgroundColor = R.color.ochre,
    buttonsColor = R.color.dark_molasses,
    buttonsTextColor = R.color.ochre,
    otherTextColor = R.color.dark_molasses,
    landingPage = false,
    variablePortion = true,
    fixPortionTen = false,
    fixPortionTwoFive = false,
    benefitsPage = false
)


var generosityLightColor1 = Generosity(
    income = 18043.0,
    portion = 10.0,
    donation = 1804.3,
    roundUpOnes = false,
    roundUpTens = false,
    roundUpHundreds = false,
    roundUpThousands = false,
    colorMode = true,
    backgroundColor = R.color.ochre,
    buttonsColor = R.color.dark_molasses,
    buttonsTextColor = R.color.ochre,
    otherTextColor = R.color.dark_molasses,
    landingPage = true,
    variablePortion = false,
    fixPortionTen = false,
    fixPortionTwoFive = false,
    benefitsPage = false
)

var generosityLightColor2 = Generosity(
    income = 18043.0,
    portion = 10.0,
    donation = 1804.3,
    roundUpOnes = false,
    roundUpTens = false,
    roundUpHundreds = false,
    roundUpThousands = false,
    colorMode = true,
    backgroundColor = R.color.warm_cream,
    buttonsColor = R.color.dark_molasses,
    buttonsTextColor = R.color.warm_cream,
    otherTextColor = R.color.dark_molasses,
    landingPage = true,
    variablePortion = false,
    fixPortionTen = false,
    fixPortionTwoFive = false,
    benefitsPage = false,
)

var generosityLightColor3 = Generosity(
    income = 18043.0,
    portion = 10.0,
    donation = 1804.3,
    roundUpOnes = false,
    roundUpTens = false,
    roundUpHundreds = false,
    roundUpThousands = false,
    colorMode = true,
    backgroundColor = R.color.saffron,
    buttonsColor = R.color.umber_like,
    buttonsTextColor = R.color.saffron,
    otherTextColor = R.color.umber_like,
    landingPage = true,
    variablePortion = false,
    fixPortionTen = false,
    fixPortionTwoFive = false,
    benefitsPage = false,
)

var generosityLightColor4 = Generosity(
    income = 18043.0,
    portion = 10.0,
    donation = 1804.3,
    roundUpOnes = false,
    roundUpTens = false,
    roundUpHundreds = false,
    roundUpThousands = false,
    colorMode = true,
    backgroundColor = R.color.turmeric_yellow,
    buttonsColor = R.color.deep_mahogany,
    buttonsTextColor = R.color.turmeric_yellow,
    otherTextColor = R.color.deep_mahogany,
    landingPage = true,
    variablePortion = false,
    fixPortionTen = false,
    fixPortionTwoFive = false,
    benefitsPage = false,
)

var generosityDarkColor1 = Generosity(
    income = 18043.0,
    portion = 10.0,
    donation = 1804.3,
    roundUpOnes = false,
    roundUpTens = false,
    roundUpHundreds = false,
    roundUpThousands = false,
    colorMode = false,
    backgroundColor = R.color.emerald_green,
    buttonsColor = R.color.pale_moss,
    buttonsTextColor = R.color.emerald_green,
    otherTextColor = R.color.pale_moss,
    landingPage = true,
    variablePortion = false,
    fixPortionTen = false,
    fixPortionTwoFive = false,
    benefitsPage = false,
)

var generosityDarkColor2 = Generosity(
    income = 18043.0,
    portion = 10.0,
    donation = 1804.3,
    roundUpOnes = false,
    roundUpTens = false,
    roundUpHundreds = false,
    roundUpThousands = false,
    colorMode = false,
    backgroundColor = R.color.terracotta,
    buttonsColor = R.color.peach_sand,
    buttonsTextColor = R.color.terracotta,
    otherTextColor = R.color.peach_sand,
    landingPage = true,
    variablePortion = false,
    fixPortionTen = false,
    fixPortionTwoFive = false,
    benefitsPage = false,
)

var generosityDarkColor3 = Generosity(
    income = 18043.0,
    portion = 10.0,
    donation = 1804.3,
    roundUpOnes = false,
    roundUpTens = false,
    roundUpHundreds = false,
    roundUpThousands = false,
    colorMode = false,
    backgroundColor = R.color.vermillion,
    buttonsColor = R.color.blush_rose,
    buttonsTextColor = R.color.vermillion,
    otherTextColor = R.color.blush_rose,
    landingPage = true,
    variablePortion = false,
    fixPortionTen = false,
    fixPortionTwoFive = false,
    benefitsPage = false,
)

var generosityDarkColor4 = Generosity(
    income = 18043.0,
    portion = 10.0,
    donation = 1804.3,
    roundUpOnes = false,
    roundUpTens = false,
    roundUpHundreds = false,
    roundUpThousands = false,
    colorMode = false,
    backgroundColor = R.color.indigo,
    buttonsColor = R.color.pale_sky,
    buttonsTextColor = R.color.indigo,
    otherTextColor = R.color.pale_sky,
    landingPage = true,
    variablePortion = false,
    fixPortionTen = false,
    fixPortionTwoFive = false,
    benefitsPage = false,
)

/* Data Template
fun fnForGenerosity(generosity: Generosity) {
    ... generosity.income
        generosity.portion
        generosity.roundUpOnes
        generosity.roundUpTens
        generosity.roundUpHundreds
        generosity.roundUpThousands
        generosity.colorMode
        generosity.backgroundColor
        generosity.buttonsColor
        generosity.lightColor
        generosity.darkColor
        generosity.landingPage
        generosity.variablePortion
        generosity.fixPortionTen
        generosity.fixPortionTwoFive
        generosity.benefitsPage
}
*/

// Template rules in use:
//  - compound: 16 fields

// Functions definition section

// Generosity -> Generosity
// Start of world with initial state "generosity" e.g.

//var generosity = Generosity(
//                income = 0.0,
//                portion = 0.0,
//                donation = 0.0,
//                roundUpOnes = false,
//                roundUpTens = false,
//                roundUpHundreds = false,
//                roundUpThousands = false,
//                colorMode = true,
//                backgroundColor = R.color.ochre,
//                buttonsColor = R.color.dark_molasses,
//                buttonsTextColor = R.color.ochre,
//                otherTextColor = R.color.dark_molasses,
//                landingPage = true
//                variablePortion = false
//                fixPortionTen = false
//                fixPortionTwoFive = false
//                benefitsPage = false
//)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GenerosityTheme {
                BigBang()
            }
        }
    }
}

@Composable
fun BigBang() {
    //Data "Generosity"
    var generosity by remember {
        mutableStateOf(randomColor())
    }

    Render(generosity)

    // Function touchHandling: Generosity keyEvent -> Generosity
    //KeyEvent
    onRoundUpOnes = { generosity -> touchHandling(generosity, "Ones") }
    //KeyEvent
    onRoundUpTens = { generosity -> touchHandling(generosity, "Tens") }
    //KeyEvent
    onRoundUpHundreds = { generosity -> touchHandling(generosity, "Hundreds") }
    //KeyEvent
    onRoundUpThousands = { generosity -> touchHandling(generosity, "Thousands") }
    //KeyEvent
    onCalculation = { generosity -> touchHandling(generosity, "Calculation") }
    //KeyEvent
    onBack = { generosity -> touchHandling(generosity, "Back") }
    //KeyEvent
    onVariablePortion = { generosity -> touchHandling(generosity, "Variable Portion") }
    //KeyEvent
    onFixPortionTen = { generosity -> touchHandling(generosity, "Fix Portion 10") }
    //KeyEvent
    onFixPortionTwoFive = { generosity -> touchHandling(generosity, "Fix Portion 2.5") }
    //KeyEvent
    onBenefits = { generosity -> touchHandling(generosity, "Benefits") }
}

// Generosity -> Image
// With State Generosity as input, output of specific page
// !!!
//@Composable
//fun Render(generosity: Generosity) {
//        Text(text = "Index Page")
//}

@Composable
fun Render(generosity: Generosity) {
    if (generosity.landingPage) {
        IndexRendering(generosity)
    } else if (generosity.benefitsPage) {
        BenefitsRendering()
    } else if (generosity.variablePortion) {
        CalculationRendering(generosity)
    } else if (generosity.fixPortionTen) {
        CalculationRendering(generosity)
    } else if (generosity.fixPortionTwoFive) {
        CalculationRendering(generosity)
    }
}

// Generosity -> Image
// With Generosity as input output of Landing page on screen

// Test in file "GenerosityUITest.kt"

@Composable
fun IndexRendering(generosity: Generosity) {
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(generosity.backgroundColor))
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.app_name),
            color = colorResource(generosity.otherTextColor),
            fontSize = 48.sp,
            modifier = Modifier
                .fillMaxWidth(0.8f)
        )
        Text(
            text = stringResource(R.string.app_name_pali),
            color = colorResource(generosity.otherTextColor),
            fontSize = 20.sp,
            modifier = Modifier
                .fillMaxWidth(0.8f)
        )
        Spacer(modifier = Modifier.height(64.dp))
        NavigationButtons(
            label = R.string.button1,
            action = { onBenefits(it) },
            buttonsColor = generosity.buttonsColor,
            textColor = generosity.buttonsTextColor,
            modifier = Modifier
                .fillMaxWidth(0.8f)
        )
        NavigationButtons(
            label = R.string.button2,
            action = { onFixPortionTen(it) },
            buttonsColor = generosity.buttonsColor,
            textColor = generosity.buttonsTextColor,
            modifier = Modifier
                .fillMaxWidth(0.8f)
        )
        NavigationButtons(
            label = R.string.button3,
            action = { onFixPortionTwoFive(it) },
            buttonsColor = generosity.buttonsColor,
            textColor = generosity.buttonsTextColor,
            modifier = Modifier
                .fillMaxWidth(0.8f)
        )
        NavigationButtons(
            label = R.string.button4,
            action = { onVariablePortion(it) },
            buttonsColor = generosity.buttonsColor,
            textColor = generosity.buttonsTextColor,
            modifier = Modifier
                .fillMaxWidth(0.8f)
        )
    }
}

// Generosity -> Image
// With Generosity as input, output of Benefits Page on Screen
// !!!
//@Composable
//fun BenefitsRendering(generosity: Generosity) {
//        Text(text = "Benefits Stub")
//}

@Composable
fun BenefitsRendering() {

    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.ochre))
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.title),
            color = colorResource(R.color.dark_molasses),
            fontSize = 28.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier
                .fillMaxWidth(0.8f)
        )
        Text(
            text = stringResource(R.string.subtitle),
            color = colorResource(R.color.dark_molasses),
            fontSize = 24.sp,
            fontWeight = FontWeight.ExtraLight,
            textDecoration = TextDecoration.Underline,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth(0.8f)
        )
        Spacer(modifier = Modifier.height(64.dp))
        Text(
            text = stringResource(R.string.heading1),
            color = colorResource(R.color.dark_molasses),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth(0.8f)

        )
        Text(
            text = stringResource(R.string.subheading1),
            color = colorResource(R.color.dark_molasses),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth(0.8f)
        )
        Text(
            text = stringResource(R.string.sh1_contents),
            color = colorResource(R.color.dark_molasses),
            fontSize = 16.sp,
            fontWeight = FontWeight.Light,
            modifier = Modifier
                .fillMaxWidth(0.8f)
        )
        Text(
            text = stringResource(R.string.subheading2),
            color = colorResource(R.color.dark_molasses),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth(0.8f)
        )
        Text(
            text = stringResource(R.string.sh2_contents),
            color = colorResource(R.color.dark_molasses),
            fontSize = 16.sp,
            fontWeight = FontWeight.Light,
            modifier = Modifier
                .fillMaxWidth(0.8f)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.heading2),
            color = colorResource(R.color.dark_molasses),
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .fillMaxWidth(0.8f)
        )
        Text(
            text = stringResource(R.string.h2_contents),
            color = colorResource(R.color.dark_molasses),
            fontSize = 16.sp,
            fontWeight = FontWeight.Light,
            modifier = Modifier
                .fillMaxWidth(0.8f)
        )
        Text(
            text = stringResource(R.string.source),
            color = colorResource(R.color.dark_molasses),
            fontSize = 12.sp,
            fontWeight = FontWeight.Thin,
            textAlign = TextAlign.End,
            modifier = Modifier
                .fillMaxWidth(0.8f)

        )
        NavigationButtons(
            label = R.string.back,
            action = { onBack(it) },
            buttonsColor = R.color.dark_molasses,
            textColor = R.color.ochre,
            modifier = Modifier
                .fillMaxWidth(0.8f)
        )
    }
}

// Generosity -> Image
// With Generosity as input, output of app visuals on device screen
// Tests in the file: "GenerosityUITests.kt"

//A Stub
//@Composable
//fun CalculationRendering(Generosity: Generosity) {
//    Text(text = "Stub")
//}

@Composable
internal fun CalculationRendering(generosity: Generosity) {

    var incomeAmount by remember { mutableStateOf("") }
    var portionAmount by remember { mutableStateOf("") }

    generosity.income = incomeAmount.toDoubleOrNull()?: 0.0
    generosity.portion = portionAmount.toDoubleOrNull()?: 0.0

    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = if (generosity.variablePortion) {
                    colorResource(generosity.backgroundColor)
                } else if (generosity.fixPortionTwoFive) {
                    colorResource(R.color.emerald_green)
                } else {
                    colorResource(R.color.saffron)
                }
            )
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        if (generosity.fixPortionTen) {
            Image(
                painter = painterResource(R.drawable.ikonkar),
                contentDescription = stringResource(R.string.sikhi_logo_description),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .size(120.dp)
            )
        } else if (generosity.fixPortionTwoFive) {
            Image(
                painter = painterResource(R.drawable.zakat_icon),
                contentDescription = stringResource(R.string.zakat_logo_description),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .size(120.dp)
            )
        } else {
            Text(
                text = stringResource(R.string.app_action_label),
                color = colorResource(generosity.otherTextColor),
                fontSize = 16.sp,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
            )
        }
        TextFieldModule(
            label = R.string.amount_label,
            leadingIcon = R.drawable.money,
            value = incomeAmount,
            onValueChange = { incomeAmount = it },
            // newValue -> generosity.income = newValue
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = if (!generosity.fixPortionTen && !generosity.fixPortionTwoFive) ImeAction.Next else ImeAction.Done
            ),
            enabled = true
        )
        Spacer(modifier = Modifier.height(20.dp))
        if (generosity.variablePortion) {
            TextFieldModule(
                label = R.string.percentage_label,
                leadingIcon = R.drawable.percent_icon,
                value = portionAmount,
                onValueChange = { portionAmount = it },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                enabled = !generosity.fixPortionTen && !generosity.fixPortionTwoFive
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Amount Round Up?",
                color = if (generosity.variablePortion) {
                    colorResource(generosity.otherTextColor)
                } else if (generosity.fixPortionTwoFive){
                    colorResource(R.color.pale_moss)
                } else {
                    colorResource(R.color.umber_like)
                },
                fontSize = 12.sp,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.8f),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CalculationButtons(
                    label = R.string.level0,
                    action = { onRoundUpOnes(it) },
                    active = generosity.roundUpOnes,
                    smallIncome = false,
                    modifier = Modifier
                        .weight(1f)
                )
                CalculationButtons(
                    label = R.string.level1,
                    action = { onRoundUpTens(it) },
                    active = generosity.roundUpTens,
                    smallIncome = generosity.income < 10,
                    modifier = Modifier
                        .weight(1f)
                )
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth(0.8f),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CalculationButtons(
                    label = R.string.level2,
                    action = { onRoundUpHundreds(it) },
                    active = generosity.roundUpHundreds,
                    smallIncome = generosity.income < 100,
                    modifier = Modifier
                        .weight(1f)
                )
                CalculationButtons(
                    label = R.string.level3,
                    action = { onRoundUpThousands(it) },
                    active = generosity.roundUpThousands,
                    smallIncome = generosity.income < 1000,
                    modifier = Modifier
                        .weight(1f)
                )
            }
        }
        Text(
            text = stringResource(
                R.string.result_amount_label,
                NumberFormat
                    .getCurrencyInstance()
                    .format(generosity.donation),

                ),
            color = if (generosity.variablePortion) {
                colorResource(generosity.otherTextColor)
            } else if (generosity.fixPortionTwoFive) {
                colorResource(R.color.pale_moss)
            } else {
                colorResource(R.color.umber_like)
            },
            fontSize = 20.sp,
            fontFamily = MaterialTheme.typography.headlineMedium.fontFamily,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(top = 16.dp)
        )
        CalculationButtons(
            label = R.string.submit_button,
            action = { onCalculation(it) },
            active = false,
            smallIncome = false,
            modifier = Modifier
                .fillMaxWidth(0.8f)
        )
        NavigationButtons(
            label = R.string.back,
            action = { onBack(it) },
            buttonsColor = if (generosity.variablePortion) {
                generosity.buttonsColor
            } else if (generosity.fixPortionTwoFive) {
                R.color.pale_moss
            } else {
                R.color.umber_like
            },
            textColor = if (generosity.variablePortion) {
                generosity.buttonsTextColor
            } else if (generosity.fixPortionTwoFive) {
                R.color.emerald_green
            } else {
                R.color.saffron
            },
            modifier = Modifier
                .fillMaxWidth(0.8f)
        )
    }
}

// Generosity KeyEvent -> Generosity
// Generosity and KeyEvent as input, changes in Generosity as per KeyEvent
// Tests in the file: "GenerosityTests.kt"

@VisibleForTesting
internal fun touchHandling(
    generosity: Generosity,
    keyEvent: String
) {
    when (keyEvent) {
        "Ones" -> {
            generosity.roundUpTens = false
            generosity.roundUpHundreds = false
            generosity.roundUpThousands = false
            generosity.roundUpOnes = toggle(generosity.roundUpOnes)
        }
        "Tens" -> {
            if (generosity.income > 10) {
                generosity.roundUpOnes = false
                generosity.roundUpHundreds = false
                generosity.roundUpThousands = false
                generosity.roundUpTens = toggle(generosity.roundUpTens)
            }
        }
        "Hundreds" -> {
            if (generosity.income > 100) {
                generosity.roundUpOnes = false
                generosity.roundUpTens = false
                generosity.roundUpThousands = false
                generosity.roundUpHundreds = toggle(generosity.roundUpHundreds)
            }

        }
        "Thousands" -> {
            if (generosity.income > 1000) {
                generosity.roundUpOnes = false
                generosity.roundUpTens = false
                generosity.roundUpHundreds = false
                generosity.roundUpThousands = toggle(generosity.roundUpThousands)
            }
        }
        "Benefits" -> {
            generosity.fixPortionTen = false
            generosity.fixPortionTwoFive = false
            generosity.variablePortion = false
            generosity.landingPage = false
            generosity.benefitsPage = true
        }
        "Fix Portion 10" -> {
            generosity.landingPage = false
            generosity.variablePortion = false
            generosity.fixPortionTen = true
            generosity.fixPortionTwoFive = false
        }
        "Fix Portion 2.5" -> {
            generosity.landingPage = false
            generosity.variablePortion = false
            generosity.fixPortionTen = false
            generosity.fixPortionTwoFive = true
        }
        "Variable Portion" -> {
            generosity.landingPage = false
            generosity.fixPortionTen = false
            generosity.fixPortionTwoFive = false
            generosity.variablePortion = true
        }
        "Back" -> {
            generosity.variablePortion = false
            generosity.fixPortionTen = false
            generosity.fixPortionTwoFive = false
            generosity.benefitsPage = false
            generosity.landingPage = true
        }
        "Calculation" -> {
            generosity.donation = calculation(
                generosity.income,
                if (generosity.fixPortionTen) {
                    10.0
                } else if (generosity.fixPortionTwoFive) {
                    2.5
                } else {
                    generosity.portion
                },
                generosity.roundUpOnes,
                generosity.roundUpTens,
                generosity.roundUpHundreds,
                generosity.roundUpThousands
            )
        }
    }
}


//Additional functions

// Boolean -> Boolean
// With Boolean as input toggle ON/OFF the state of boolean, and output of Boolean
// Tests in the file "GenerosityTest.kt"

//fun toggle(roundUpOption: Boolean): Boolean {
//        roundUpOption = false
//        return         roundUpOption
//}
@VisibleForTesting
internal fun toggle(roundUpOption: Boolean): Boolean {
    var roundUpOption = roundUpOption
    roundUpOption = !roundUpOption
    return roundUpOption
}

// Double Double Boolean Boolean Boolean Boolean -> Double
// With income, portion, roundUp, roundUpTens, roundUpHundreds and roundUpThousands as input,
// output of Donation Amount
// Tests in the file "GenerosityTest.kt"

//fun calculation(
//        income: Double,
//        portion: Double,
//        roundUp: Boolean,
//        roundUpTens: Boolean,
//        roundUpHundreds: Boolean,
//        roundUpThousands: Boolean
//): Double {
//        return donation
//}

@VisibleForTesting
internal fun calculation(
    income: Double,
    portion: Double,
    roundUpOnes: Boolean,
    roundUpTens: Boolean,
    roundUpHundreds: Boolean,
    roundUpThousands: Boolean
): Double {
    var donation: Double
    if (roundUpOnes) {
        donation = income * (portion / 100)
        donation = ceil(donation)
    } else if (roundUpTens && income > 10) {
        donation = income * (portion / 100)
        donation = ceil(donation / 10) * 10
    } else if (roundUpHundreds && income > 100) {
        donation = income * (portion / 100)
        donation = ceil(donation / 100) * 100
    } else if (roundUpThousands && income > 1000) {
        donation = income * (portion / 100)
        donation = ceil(donation / 1000) * 1000
    } else {
        donation = income * (portion / 100)
    }
    return donation
}

// () -> Generosity
// Output of a state with a different color scheme randomly
// Tests in the file "GenerosityTest.kt"

//fun randomColor() {
//            return generosity.backgroundColor = R.color.black
//            return generosity.buttonsColor = R.color.black
//            return generosity.lightColor = R.color.black
//            return generosity.darkColor = R.color.black
//}
@VisibleForTesting
internal fun randomColor(): Generosity {
    val generosityInstance = (1..8).random()
    generosity = when (generosityInstance) {
        1 -> generosityLightColor1
        2 -> generosityLightColor2
        3 -> generosityLightColor3
        4 -> generosityLightColor4
        5 -> generosityDarkColor1
        6 -> generosityDarkColor2
        7 -> generosityDarkColor3
        else -> generosityDarkColor4
    }
    generosity.donation = 0.0
    return generosity
}


// Int String ((String) -> Unit) -> Image
// With a label, A value in text and a lambda(newValue -> Unit) as input, output of text field as
// image
// Tests in file "GenerosityUITests"

//@Composable
//fun TextFieldModule(
//        @StringRes label: Int,
//        value: String,
//        onValueChange: (String) -> Unit
//) {
//        Text(text="stub")
//}

@Composable
internal fun TextFieldModule(
    @StringRes label: Int,
    @DrawableRes leadingIcon: Int,
    value: String,
    onValueChange: (String) -> Unit,
    singleLine: Boolean = true,
    keyboardOptions: KeyboardOptions,
    enabled: Boolean
) {
    TextField(
        label = {
            Text(
                text = stringResource(label),
                color = if (generosity.variablePortion) {
                    colorResource(generosity.buttonsTextColor)
                } else if (generosity.fixPortionTwoFive) {
                    colorResource(R.color.emerald_green)
                } else {
                    colorResource(R.color.saffron)
                }
            )
        },
        leadingIcon = {
            Icon(
                painter = painterResource(leadingIcon),
                contentDescription = null,
                tint = if (generosity.variablePortion) {
                    colorResource(generosity.buttonsTextColor)
                } else if (generosity.fixPortionTwoFive) {
                    colorResource(R.color.emerald_green)
                } else {
                    colorResource(R.color.saffron)
                }
            )
        },
        value = value,
        onValueChange = onValueChange,
        singleLine = singleLine,
        keyboardOptions = keyboardOptions,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = if (generosity.colorMode)
                colorResource(R.color.black)
            else
                colorResource(R.color.white),
            unfocusedContainerColor = if (generosity.variablePortion) {
                colorResource(generosity.buttonsColor)
            } else if (generosity.fixPortionTwoFive) {
                colorResource(R.color.pale_moss)
            } else {
                colorResource(R.color.umber_like)
            },
            disabledContainerColor = colorResource(R.color.gray),
            cursorColor = if (generosity.variablePortion) {
                colorResource(generosity.buttonsTextColor)
            } else if (generosity.fixPortionTwoFive) {
                colorResource(R.color.emerald_green)
            } else {
                colorResource(R.color.saffron)
            },
            focusedLabelColor = if (generosity.variablePortion) {
                colorResource(generosity.buttonsTextColor)
            } else if (generosity.fixPortionTwoFive) {
                colorResource(R.color.emerald_green)
            } else {
                colorResource(R.color.saffron)
            },
            focusedTextColor = if (generosity.variablePortion) {
                colorResource(generosity.buttonsTextColor)
            } else if (generosity.fixPortionTwoFive) {
                colorResource(R.color.emerald_green)
            } else {
                colorResource(R.color.saffron)
            },
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedTextColor = if (generosity.variablePortion) {
                colorResource(generosity.buttonsTextColor)
            } else if (generosity.fixPortionTwoFive) {
                colorResource(R.color.emerald_green)
            } else {
                colorResource(R.color.saffron)
            },
            unfocusedLabelColor = if (generosity.variablePortion) {
                colorResource(generosity.buttonsTextColor)
            } else if (generosity.fixPortionTwoFive) {
                colorResource(R.color.emerald_green)
            } else {
                colorResource(R.color.saffron)
            }
        ),
        enabled = enabled,
        modifier = Modifier
            .fillMaxWidth(0.8f)
    )
}

// Int Generosity ((Generosity) -> Unit ) -> Unit
// With input of a label from strings.xml as int, class Generosity and a lambda as action, output of an execution
// ready button
// Tests in the GenerosityUITest.kt file

//@Composable
//fun CalculationButtons(
//        @StringRes label: int,
//        action: (Generosity) -> Unit,
//        active: Boolean
//        smallIncome: Boolean
//) {
//        Text(text = "stub")
//}

@Composable
internal fun CalculationButtons(
    @StringRes label: Int,
    action: (Generosity) -> Unit,
    active: Boolean,
    smallIncome: Boolean,
    modifier: Modifier
) {
    Button(
        onClick = { action(generosity) },
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (!active) {
                if (generosity.variablePortion) {
                    colorResource(generosity.buttonsColor)
                } else if (generosity.fixPortionTwoFive) {
                    colorResource(R.color.pale_moss)
                } else if (smallIncome) {
                    colorResource(R.color.gray)
                } else {
                    colorResource(R.color.umber_like)
                }
            } else {
                if (generosity.colorMode && !smallIncome)
                    colorResource(R.color.black)
                else if (!generosity.colorMode && !smallIncome)
                    colorResource(R.color.white)
                else
                    colorResource(R.color.gray)
            }
        ),
        modifier = modifier
    ) {
        Text(
            text = stringResource(label),
            color = if (generosity.variablePortion) {
                colorResource(generosity.buttonsTextColor)
            } else if (generosity.fixPortionTwoFive) {
                colorResource(R.color.emerald_green)
            } else if (smallIncome) {
                colorResource(R.color.black)
            } else {
                colorResource(R.color.saffron)
            },
        )
    }
}

// Int ((Generosity) -> Unit ) -> Unit
// With input of a label from strings.xml as int and a lambda as action, output of an execution
// ready button
// Tests in the GenerosityUITest.kt file

//@Composable
//fun NavigationButtons(
//        @StringRes label: int,
//        action: (Generosity) -> Unit
//) {
//        Text(text = "stub")
//}

@Composable
internal fun NavigationButtons(
    @StringRes label: Int,
    action: (Generosity) -> Unit,
    @ColorRes buttonsColor: Int,
    @ColorRes textColor: Int,
    modifier: Modifier
) {
    Button(
        onClick = { action(generosity) },
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(buttonsColor)),
        modifier = modifier
    ) {
        Text(
            text = stringResource(label),
            color = colorResource(textColor),
        )
    }
}

var onRoundUpOnes: (Generosity) -> Unit = {}
var onRoundUpTens: (Generosity) -> Unit = {}
var onRoundUpHundreds: (Generosity) -> Unit = {}
var onRoundUpThousands: (Generosity) -> Unit = {}
var onFixPortionTen: (Generosity) -> Unit = {}
var onFixPortionTwoFive: (Generosity) -> Unit = {}
var onBenefits: (Generosity) -> Unit = {}
var onVariablePortion: (Generosity) -> Unit = {}
var onBack: (Generosity) -> Unit = {}
var onCalculation: (Generosity) -> Unit = {}
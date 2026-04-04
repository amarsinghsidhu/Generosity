package com.example.generosity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.generosity.ui.theme.GenerosityTheme
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
    fixPortion: Boolean,
    colorMode: Boolean,
    @ColorRes backgroundColor: Int,
@ColorRes buttonsColor: Int,
@ColorRes buttonsTextColor: Int,
@ColorRes otherTextColor: Int
) {
    var income by mutableDoubleStateOf(income)
    var portion by mutableDoubleStateOf(portion)
    var donation by mutableDoubleStateOf(donation)
    var roundUpOnes by mutableStateOf(roundUpOnes)
    var roundUpTens by mutableStateOf(roundUpTens)
    var roundUpHundreds by mutableStateOf(roundUpHundreds)
    var roundUpThousands by mutableStateOf(roundUpThousands)
    var fixPortion by mutableStateOf(fixPortion)
    var colorMode by mutableStateOf(colorMode)
    var backgroundColor by mutableIntStateOf(backgroundColor)
    var buttonsColor by mutableIntStateOf(buttonsColor)
    var buttonsTextColor by mutableIntStateOf(buttonsTextColor)
    var otherTextColor by mutableIntStateOf(otherTextColor)
}
// Generosity as
//	val generosity: Generosity = Generosity(
//		- Number[0, Infinite)
//		- Number[0, 100]
//		- Number[0, Infinite)
//		- Boolean
//		- Boolean
//		- Boolean
//		- Boolean
//		- Boolean
//		- Boolean
//		- Color [Random]
//		- Color [Random]
//		- Color [Random]
//		- Color [Random]
//	)
// Interpretation:
//  - Income = 1000
//  - Portion = 10
//  - Donation Amount = 100
//  - Round Up Level 1 (Upper 1's place) = false
//  - Round Up Level 2 (Upper 10's place) = false
//  - Round Up Level 3 (Upper 100's place) = false
//  - Round Up Level 4 (Upper 1000's place) = false
//  - FixPortion (10%)
//  - colorMode (true for light and false for dark) = true
//  - background color = Ochre
//  - Buttons color = Dark molasses
//  - Button text color = Ochre
//  - Other text color = Dark molasses

//Examples
var generosity = Generosity(
    income = 0.0,
    portion = 0.0,
    donation = 0.0,
    roundUpOnes = false,
    roundUpTens = false,
    roundUpHundreds = false,
    roundUpThousands = false,
    fixPortion = false,
    colorMode = true,
    backgroundColor = R.color.ochre,
    buttonsColor = R.color.dark_molasses,
    buttonsTextColor = R.color.ochre,
    otherTextColor = R.color.dark_molasses
)

var generosity1 = Generosity(
    income = 18043.0,
    portion = 10.0,
    donation = 1804.3,
    roundUpOnes = false,
    roundUpTens = false,
    roundUpHundreds = false,
    roundUpThousands = false,
    fixPortion = false,
    colorMode = true,
    backgroundColor = R.color.ochre,
    buttonsColor = R.color.dark_molasses,
    buttonsTextColor = R.color.ochre,
    otherTextColor = R.color.dark_molasses
)

var generosity2 = Generosity(
    income = 18043.0,
    portion = 10.0,
    donation = 1805.0,
    roundUpOnes = true,
    roundUpTens = false,
    roundUpHundreds = false,
    roundUpThousands = false,
    fixPortion = false,
    colorMode = true,
    backgroundColor = R.color.ochre,
    buttonsColor = R.color.dark_molasses,
    buttonsTextColor = R.color.ochre,
    otherTextColor = R.color.dark_molasses
)

var generosity3 = Generosity(
    income = 18043.0,
    portion = 10.0,
    donation = 1810.0,
    roundUpOnes = false,
    roundUpTens = true,
    roundUpHundreds = false,
    roundUpThousands = false,
    fixPortion = false,
    colorMode = true,
    backgroundColor = R.color.ochre,
    buttonsColor = R.color.dark_molasses,
    buttonsTextColor = R.color.ochre,
    otherTextColor = R.color.dark_molasses
)

var generosity4 = Generosity(
    income = 18043.0,
    portion = 10.0,
    donation = 1900.0,
    roundUpOnes = false,
    roundUpTens = false,
    roundUpHundreds = true,
    roundUpThousands = false,
    fixPortion = false,
    colorMode = true,
    backgroundColor = R.color.ochre,
    buttonsColor = R.color.dark_molasses,
    buttonsTextColor = R.color.ochre,
    otherTextColor = R.color.dark_molasses
)

var generosity5 = Generosity(
    income = 18043.0,
    portion = 10.0,
    donation = 2000.0,
    roundUpOnes = false,
    roundUpTens = false,
    roundUpHundreds = false,
    roundUpThousands = true,
    fixPortion = false,
    colorMode = true,
    backgroundColor = R.color.ochre,
    buttonsColor = R.color.dark_molasses,
    buttonsTextColor = R.color.ochre,
    otherTextColor = R.color.dark_molasses
)

var generosityLightColor1 = Generosity(
    income = 18043.0,
    portion = 10.0,
    donation = 1804.3,
    roundUpOnes = false,
    roundUpTens = false,
    roundUpHundreds = false,
    roundUpThousands = false,
    fixPortion = false,
    colorMode = true,
    backgroundColor = R.color.ochre,
    buttonsColor = R.color.dark_molasses,
    buttonsTextColor = R.color.ochre,
    otherTextColor = R.color.dark_molasses
)

var generosityLightColor2 = Generosity(
    income = 18043.0,
    portion = 10.0,
    donation = 1804.3,
    roundUpOnes = false,
    roundUpTens = false,
    roundUpHundreds = false,
    roundUpThousands = false,
    fixPortion = false,
    colorMode = true,
    backgroundColor = R.color.warm_cream,
    buttonsColor = R.color.dark_molasses,
    buttonsTextColor = R.color.warm_cream,
    otherTextColor = R.color.dark_molasses
)

var generosityLightColor3 = Generosity(
    income = 18043.0,
    portion = 10.0,
    donation = 1804.3,
    roundUpOnes = false,
    roundUpTens = false,
    roundUpHundreds = false,
    roundUpThousands = false,
    fixPortion = false,
    colorMode = true,
    backgroundColor = R.color.saffron,
    buttonsColor = R.color.umber_like,
    buttonsTextColor = R.color.saffron,
    otherTextColor = R.color.umber_like
)

var generosityLightColor4 = Generosity(
    income = 18043.0,
    portion = 10.0,
    donation = 1804.3,
    roundUpOnes = false,
    roundUpTens = false,
    roundUpHundreds = false,
    roundUpThousands = false,
    fixPortion = false,
    colorMode = true,
    backgroundColor = R.color.turmeric_yellow,
    buttonsColor = R.color.deep_mahogany,
    buttonsTextColor = R.color.turmeric_yellow,
    otherTextColor = R.color.deep_mahogany
)

var generosityDarkColor1 = Generosity(
    income = 18043.0,
    portion = 10.0,
    donation = 1804.3,
    roundUpOnes = false,
    roundUpTens = false,
    roundUpHundreds = false,
    roundUpThousands = false,
    fixPortion = false,
    colorMode = false,
    backgroundColor = R.color.emerald_green,
    buttonsColor = R.color.pale_moss,
    buttonsTextColor = R.color.emerald_green,
    otherTextColor = R.color.pale_moss
)

var generosityDarkColor2 = Generosity(
    income = 18043.0,
    portion = 10.0,
    donation = 1804.3,
    roundUpOnes = false,
    roundUpTens = false,
    roundUpHundreds = false,
    roundUpThousands = false,
    fixPortion = false,
    colorMode = false,
    backgroundColor = R.color.terracotta,
    buttonsColor = R.color.peach_sand,
    buttonsTextColor = R.color.terracotta,
    otherTextColor = R.color.peach_sand
)

var generosityDarkColor3 = Generosity(
    income = 18043.0,
    portion = 10.0,
    donation = 1804.3,
    roundUpOnes = false,
    roundUpTens = false,
    roundUpHundreds = false,
    roundUpThousands = false,
    fixPortion = false,
    colorMode = false,
    backgroundColor = R.color.vermillion,
    buttonsColor = R.color.blush_rose,
    buttonsTextColor = R.color.vermillion,
    otherTextColor = R.color.blush_rose
)

var generosityDarkColor4 = Generosity(
    income = 18043.0,
    portion = 10.0,
    donation = 1804.3,
    roundUpOnes = false,
    roundUpTens = false,
    roundUpHundreds = false,
    roundUpThousands = false,
    fixPortion = false,
    colorMode = false,
    backgroundColor = R.color.indigo,
    buttonsColor = R.color.pale_sky,
    buttonsTextColor = R.color.indigo,
    otherTextColor = R.color.pale_sky
)

/* Data Template
fun fnForGenerosity(generosity: Generosity) {
    ... generosity.income
        generosity.portion
        generosity.roundUpOnes
        generosity.roundUpTens
        generosity.roundUpHundreds
        generosity.roundUpThousands
        generosity.fixPortion
        generosity.colorMode
        generosity.backgroundColor
        generosity.buttonsColor
        generosity.lightColor
        generosity.darkColor
}
*/

// Template rules in use:
//  - compound: 13 fields

// Functions definition section

// Generosity -> Generosity
// Start of app with the initial state, generosity: Generosity
//    income = 0.0,
//    portion = 0.0,
//    donation = 0.0,
//    roundUpOnes = false,
//    roundUpTens = false,
//    roundUpHundreds = false,
//    roundUpThousands = false,
//    fixPortion,
//    colorMode,
//    backgroundColor = random,
//    buttonsColor = random,
//    lightColor = random,
//    darkColor = random

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

    // Function Render: Generosity -> Image
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
    onFixPortion = { generosity -> touchHandling(generosity, "Fix Portion")}
    //KeyEvent
    onCalculation = { generosity -> touchHandling(generosity, "Calculation") }

}

// Generosity -> Image
// With Generosity as input, output of app visuals on device screen
// Tests in the file: "GenerosityUITests.kt"

//A Stub
//@Composable
//fun Render(generosity: Generosity) {
//    Text(text = "Stub")
//}

@Composable
internal fun Render(generosity: Generosity) {
    var incomeAmount by remember { mutableStateOf("") }
    var portionAmount by remember { mutableStateOf("") }

    generosity.income = incomeAmount.toDoubleOrNull()?: 0.0
    generosity.portion = portionAmount.toDoubleOrNull()?: 0.0

    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .wrapContentSize()
            .fillMaxSize()
            .background(color = colorResource(generosity.backgroundColor))
            .verticalScroll(rememberScrollState())
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .wrapContentSize()
                .fillMaxSize()
        ){
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 64.dp),
                horizontalArrangement = Arrangement.End
            ){
                Buttons(
                    label = R.string.fixPortion_label,
                    action = { onFixPortion(it) },
                    active = generosity.fixPortion,
                    modifier = Modifier
                )
            }
        }
        Spacer(modifier = Modifier.height(64.dp))
        Text(
            text = stringResource(R.string.app_action_label),
            color = colorResource(generosity.otherTextColor),
            fontSize = 16.sp,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(horizontal = 64.dp)
        )
        TextFieldModule(
            label = R.string.amount_label,
            leadingIcon = R.drawable.money,
            value = incomeAmount,
            onValueChange = { incomeAmount = it }, // newValue -> generosity.income = newValue
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = if (!generosity.fixPortion) ImeAction.Next else ImeAction.Done
            ),
            enabled = true
        )
        Spacer(modifier = Modifier.height(20.dp))
        TextFieldModule(
            label = R.string.percentage_label,
            leadingIcon = R.drawable.percent_icon,
            value = portionAmount,
            onValueChange = { portionAmount = it },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            enabled = !generosity.fixPortion
        )
        Spacer(modifier = Modifier.height(20.dp))
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .wrapContentSize()
                .fillMaxSize()
        ) {
            Text(
                text = "Amount Round Up?",
                color = colorResource(generosity.otherTextColor),
                fontSize = 12.sp,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(horizontal = 64.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 64.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Buttons(
                    label = R.string.level0,
                    action = { onRoundUpOnes(it) },
                    active = generosity.roundUpOnes,
                    modifier = Modifier
                        .weight(1f)
                )
                Buttons(
                    label = R.string.level1,
                    action = { onRoundUpTens(it) },
                    active = generosity.roundUpTens,
                    modifier = Modifier
                        .weight(1f)
                )
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 64.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Buttons(
                    label = R.string.level2,
                    action = { onRoundUpHundreds(it) },
                    active = generosity.roundUpHundreds,
                    modifier = Modifier
                        .weight(1f)
                )
                Buttons(
                    label = R.string.level3,
                    action = { onRoundUpThousands(it) },
                    active = generosity.roundUpThousands,
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
            color = colorResource(generosity.otherTextColor),
            fontSize = 20.sp,
            fontFamily = MaterialTheme.typography.headlineMedium.fontFamily,
            modifier = Modifier
                .padding(vertical = 16.dp)
        )
        Buttons(
            label = R.string.submit_button,
            action = { onCalculation(it) },
            active = false,
            modifier = Modifier
        )
    }
}

// Generosity KeyEvent -> Generosity
// Generosity and KeyEvent as input, changes in Generosity as per KeyEvent
// Tests in the file: "GenerosityUITests.kt"

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
            generosity.roundUpOnes = false
            generosity.roundUpHundreds = false
            generosity.roundUpThousands = false
            generosity.roundUpTens = toggle(generosity.roundUpTens)
        }
        "Hundreds" -> {
            generosity.roundUpOnes = false
            generosity.roundUpTens = false
            generosity.roundUpThousands = false
            generosity.roundUpHundreds = toggle(generosity.roundUpHundreds)

        }
        "Thousands" -> {
            generosity.roundUpOnes = false
            generosity.roundUpTens = false
            generosity.roundUpHundreds = false
            generosity.roundUpThousands = toggle(generosity.roundUpThousands)
        }
        "Fix Portion" -> {
            generosity.fixPortion = toggle(generosity.fixPortion)
        }
        "Calculation" -> {
            generosity.donation = calculation(
                generosity.income,
                if (generosity.fixPortion) 10.0 else generosity.portion,
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
//	roundUpOption = false
//	return 	roundUpOption
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
//	income: Double,
//	portion: Double,
//	roundUp: Boolean,
//	roundUpTens: Boolean,
//	roundUpHundreds: Boolean,
//	roundUpThousands: Boolean
//): Double {
//	return donation
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
    if (!roundUpOnes && !roundUpTens && !roundUpHundreds && !roundUpThousands) {
        donation = income * (portion / 100)
    } else if (roundUpOnes) {
        donation = income * (portion / 100)
        donation = ceil(donation)
    } else if (roundUpTens) {
        donation = income * (portion / 100)
        donation = ceil(donation / 10) * 10
    } else if (roundUpHundreds) {
        donation = income * (portion / 100)
        donation = ceil(donation / 100) * 100
    } else {
        donation = income * (portion / 100)
        donation = ceil(donation / 1000) * 1000
    }
    return donation
}

// Empty -> Generosity
// Output of a state with a different color scheme randomly
// Tests in the file "GenerosityTest.kt"

//fun randomColor() {
//	    return generosity.backgroundColor = R.color.black
//    	return generosity.buttonsColor = R.color.black
//    	return generosity.lightColor = R.color.black
//    	return generosity.darkColor = R.color.black
//}

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
// !!!

//@Composable
//fun TextFieldModule(
//	@StringRes label: Int,
//	value: String,
//	onValueChange: (String) -> Unit
//) {
//	Text(text="stub")
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
                color = colorResource(generosity.buttonsTextColor)
            )
        },
        leadingIcon = {
            Icon(
                painter = painterResource(leadingIcon),
                contentDescription = null,
                tint = colorResource(generosity.buttonsTextColor)
            )
        },
        value = value,
        onValueChange = onValueChange,
        singleLine = singleLine,
        keyboardOptions = keyboardOptions,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = if (generosity.colorMode)
                colorResource(R.color.white)
            else
                colorResource(R.color.black),
            unfocusedContainerColor = colorResource(generosity.buttonsColor),
            disabledContainerColor = colorResource(R.color.muted_gray),
            cursorColor = colorResource(generosity.buttonsTextColor),
            focusedLabelColor = colorResource(generosity.buttonsTextColor),
            focusedTextColor = colorResource(generosity.buttonsTextColor),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedTextColor = colorResource(generosity.buttonsTextColor),
            unfocusedLabelColor = colorResource(generosity.buttonsTextColor)
        ),
        enabled = enabled,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 64.dp),
    )
}

// Int ((Generosity) -> Unit ) -> Unit
// With input of a label from strings.xml as int and a lambda as action, output of an execution
// ready button
// !!!

//@Composable
//fun Buttons(
//	@StringRes label: int,
//	action: (Generosity) -> Unit
//) {
//	Text(text = "stub")
//}

@Composable
internal fun Buttons(
    @StringRes label: Int,
    action: (Generosity) -> Unit,
    active: Boolean,
    modifier: Modifier
) {
    Button(
        onClick = { action(generosity) },
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (!active) {
                colorResource(generosity.buttonsColor)
            } else {
                colorResource(R.color.white)
            }
        ),
        modifier = modifier
    ) {
        Text(
            text = stringResource(label),
            color = colorResource(generosity.buttonsTextColor),
        )
    }
}

var onRoundUpOnes: (Generosity) -> Unit = {}
var onRoundUpTens: (Generosity) -> Unit = {}
var onRoundUpHundreds: (Generosity) -> Unit = {}
var onRoundUpThousands: (Generosity) -> Unit = {}
var onFixPortion: (Generosity) -> Unit = {}
var onCalculation: (Generosity) -> Unit = {}
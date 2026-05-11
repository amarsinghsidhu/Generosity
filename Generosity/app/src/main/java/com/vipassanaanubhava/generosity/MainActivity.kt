package com.vipassanaanubhava.generosity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
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
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.dimensionResource
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
//                buttonsTextColor = R.color.ochre,chatgpt misinterpreting the word as 'hunter' or gemini’s lack of elaboration on 'Dhātu Manasikāra.'
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenerosityTopAppBar() {
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.generosity_pixel_foreground),
                    contentDescription = null,
                    modifier = Modifier
                        .size(dimensionResource(R.dimen.image_size))
                        .padding(dimensionResource(R.dimen.padding_small))
                )
                /*Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.displayMedium
                )*/
            }
        }
    )
}

@Composable
fun BigBang() {
    //Data "Generosity"
    //var generosity by remember { mutableStateOf(DataSource().listOfGenerosity) }

    //val layoutDirection = LocalLayoutDirection.current

    Scaffold(
        topBar = {
            GenerosityTopAppBar()
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
        ) {
            Render(
                generosity = DataSource().listOfGenerosity[0],
                modifier = Modifier
            )
        }
    }

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
fun Render(
    generosity: Generosity,
    modifier: Modifier
) {
    if (generosity.landingPage) {
        IndexRendering(generosity)
    } else if (generosity.benefitsPage) {
        BenefitsRendering(generosity = generosity)
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
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.displayLarge,
            modifier = Modifier
                .fillMaxWidth(0.8f)
        )
        Text(
            text = stringResource(R.string.app_name_pali),
            style = MaterialTheme.typography.headlineMedium,
            fontSize = 20.sp,
            modifier = Modifier
                .fillMaxWidth(0.8f)
        )
        Spacer(modifier = Modifier.height(64.dp))
        NavigationButtons(
            generosity = generosity,
            label = R.string.button1,
            action = { onBenefits(it) },
            modifier = Modifier
                .fillMaxWidth(0.8f)
        )
        NavigationButtons(
            generosity = generosity,
            label = R.string.button2,
            action = { onFixPortionTen(it) },
            modifier = Modifier
                .fillMaxWidth(0.8f)
        )
        NavigationButtons(
            generosity = generosity,
            label = R.string.button3,
            action = { onFixPortionTwoFive(it) },
            modifier = Modifier
                .fillMaxWidth(0.8f)
        )
        NavigationButtons(
            generosity = generosity,
            label = R.string.button4,
            action = { onVariablePortion(it) },
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
fun BenefitsRendering(generosity: Generosity) {

    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.title),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier
                .fillMaxWidth(0.8f)
        )
        Text(
            text = stringResource(R.string.subtitle),
            style = MaterialTheme.typography.headlineMedium,
            textDecoration = TextDecoration.Underline,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth(0.8f)
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacer2_height)))
        Text(
            text = stringResource(R.string.heading1),
            style = MaterialTheme.typography.headlineSmall,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth(0.8f)

        )

        Card {
            Text(
                text = stringResource(R.string.subheading1),
                style = MaterialTheme.typography.titleMedium,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
            )
            Text(
                text = stringResource(R.string.sh1_contents),
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 16.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
            )
        }
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacer1_height)))
        Card {
            Text(
                text = stringResource(R.string.subheading2),
                style = MaterialTheme.typography.titleMedium,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
            )
            Text(
                text = stringResource(R.string.sh2_contents),
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 16.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
            )
        }
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacer1_height)))
        Text(
            text = stringResource(R.string.heading2),
            style = MaterialTheme.typography.titleMedium,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .fillMaxWidth(0.8f)
        )

        Card {
            Text(
                text = stringResource(R.string.h2_contents),
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 16.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
            )
            Text(
                text = stringResource(R.string.source),
                style = MaterialTheme.typography.labelMedium,
                fontSize = 12.sp,
                fontWeight = FontWeight.Thin,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth(0.8f)

            )
        }
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacer1_height)))
        NavigationButtons(
            generosity = generosity,
            label = R.string.back,
            action = { onBack(it) },
            modifier = Modifier
                .fillMaxWidth(0.8f)
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacer2_height)))
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
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        if (generosity.fixPortionTen) {
            Text(
                text = stringResource(R.string.sikhi_logo),
                style = MaterialTheme.typography.headlineLarge
            )
        } else if (generosity.fixPortionTwoFive) {
            Text(
               text = stringResource(R.string.zakat_logo),
                style = MaterialTheme.typography.headlineLarge
            )
        } else {
            Text(
                text = stringResource(R.string.app_action_label),
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
                imeAction = if (!generosity.fixPortionTen && !generosity.fixPortionTwoFive)
                    ImeAction.Next else ImeAction.Done
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
                    generosity = generosity,
                    label = R.string.level0,
                    action = { onRoundUpOnes(it) },
                    smallIncome = false,
                    modifier = Modifier
                        .weight(1f)
                )
                CalculationButtons(
                    generosity = generosity,
                    label = R.string.level1,
                    action = { onRoundUpTens(it) },
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
                    generosity = generosity,
                    label = R.string.level2,
                    action = { onRoundUpHundreds(it) },
                    smallIncome = generosity.income < 100,
                    modifier = Modifier
                        .weight(1f)
                )
                CalculationButtons(
                    generosity = generosity,
                    label = R.string.level3,
                    action = { onRoundUpThousands(it) },
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
            fontSize = 20.sp,
            fontFamily = MaterialTheme.typography.headlineMedium.fontFamily,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(top = 16.dp)
        )
        CalculationButtons(
            generosity = generosity,
            label = R.string.submit_button,
            action = { onCalculation(it) },
            smallIncome = false,
            modifier = Modifier
                .fillMaxWidth(0.8f)
        )
        NavigationButtons(
            generosity = generosity,
            label = R.string.back,
            action = { onBack(it) },
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

// Double, Double, Boolean, Boolean, Boolean, Boolean -> Double
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
                text = stringResource(label)
            )
        },
        leadingIcon = {
            Icon(
                painter = painterResource(leadingIcon),
                contentDescription = null
            )
        },
        value = value,
        onValueChange = onValueChange,
        singleLine = singleLine,
        keyboardOptions = keyboardOptions,
        shape = MaterialTheme.shapes.small,
        enabled = enabled,
        modifier = Modifier
            .fillMaxWidth(0.8f)
    )
}

// Int Generosity ((Generosity) -> Unit ) -> Unit
// With input of a label from strings.xml as int, class Generosity and a lambda as action,
// output of an execution
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
    generosity: Generosity,
    @StringRes label: Int,
    action: (Generosity) -> Unit,
    smallIncome: Boolean,
    modifier: Modifier
) {
    Button(
        onClick = { action(generosity) },
        shape = MaterialTheme.shapes.small,
        enabled = !smallIncome,
        modifier = modifier
            .clip(MaterialTheme.shapes.small)
    ) {
        Text(
            text = stringResource(label),
            style = MaterialTheme.typography.bodyMedium
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
    generosity: Generosity,
    @StringRes label: Int,
    action: (Generosity) -> Unit,
    modifier: Modifier
) {
    Button(
        onClick = { action(generosity) },
        shape = MaterialTheme.shapes.small,
        modifier = modifier
            .clip(MaterialTheme.shapes.small)
    ) {
        Text(
            text = stringResource(label),
            style = MaterialTheme.typography.bodyMedium
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
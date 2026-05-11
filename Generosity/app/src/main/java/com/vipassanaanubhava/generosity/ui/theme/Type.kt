package com.vipassanaanubhava.generosity.ui.theme


import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.vipassanaanubhava.generosity.R

val noto_sans_regular = FontFamily(
    Font(
        R.font.noto_sans_regular
    )

)
// Set of Material typography styles to start with
val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = noto_sans_regular,
        fontWeight = FontWeight.ExtraLight,
        fontSize = 48.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = noto_sans_regular,
        fontWeight = FontWeight.Bold,
        fontSize = 48.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = noto_sans_regular,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = noto_sans_regular,
        fontWeight = FontWeight.Thin,
        fontSize = 24.sp
    ),
    titleMedium = TextStyle(
        fontFamily = noto_sans_regular,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = noto_sans_regular,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    labelMedium = TextStyle(
        fontFamily = noto_sans_regular,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
)
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)
package com.example.littlelemon.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.littlelemon.R

val karlaFontFamily = FontFamily(
    Font(R.font.karla_regular)
)

val markaziFontFamily = FontFamily(
    Font(R.font.markazi_regular)
)

// Set of Material typography styles to start with
val Typography = Typography(
    //Headline
    headlineLarge = TextStyle(
        fontFamily = markaziFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 32.sp
    ),
    //Subheading
    headlineMedium = TextStyle(
        fontFamily = markaziFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 26.sp
    ),
    //Navigation Title
    bodyMedium = TextStyle(
        fontFamily = markaziFontFamily,
        fontWeight = FontWeight.Thin,
        fontSize = 20.sp
    ),
    //Metadata(Small Captions)
    bodySmall = TextStyle(
        fontFamily = karlaFontFamily,
        fontWeight = FontWeight.Thin,
        fontSize = 14.sp
    ),
    //Product Heading
    labelLarge = TextStyle(
        fontFamily = markaziFontFamily,
        fontWeight = FontWeight.Thin,
        fontSize = 24.sp
    ),
    //Product Description
    labelMedium = TextStyle(
        fontFamily = karlaFontFamily,
        fontWeight = FontWeight.Thin,
        fontSize = 18.sp
    ),
    //Button Text
    labelSmall = TextStyle(
        fontFamily = karlaFontFamily,
        fontWeight = FontWeight.Thin,
        fontSize = 16.sp
    )
)


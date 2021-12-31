package com.akinci.gymbercompose.ui.feature.splash.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.akinci.gymbercompose.R
import com.akinci.gymbercompose.ui.components.PageNavigator
import com.akinci.gymbercompose.ui.theme.GymberComposeTheme

@Composable
fun SplashScreenBody(
    onClick : ()->Unit
) {
    PageNavigator(
        R.string.splash_page,
        onClick = onClick
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GymberComposeTheme {
        SplashScreenBody(onClick = { })
    }
}
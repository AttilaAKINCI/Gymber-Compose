package com.akinci.gymbercompose.ui.feature.detail.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.akinci.gymbercompose.R
import com.akinci.gymbercompose.ui.components.PageNavigator
import com.akinci.gymbercompose.ui.theme.GymberComposeTheme

@Composable
fun DetailScreenBody(
    onClick : ()->Unit
) {
    PageNavigator(
        navigatorMessageId = R.string.detail_page,
        navigateButtonMessageId = R.string.button_prev_page,
        onClick = onClick
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GymberComposeTheme {
        DetailScreenBody(onClick = { })
    }
}
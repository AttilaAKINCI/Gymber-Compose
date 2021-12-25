package com.akinci.gymbercompose.ui.feature.dashboard

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.akinci.gymbercompose.R
import com.akinci.gymbercompose.ui.components.PageNavigator
import com.akinci.gymbercompose.ui.theme.GymberComposeTheme

@Composable
fun DashboardScreenBody(
    onClick : ()->Unit
) {
    PageNavigator(
        R.string.dashboard_page,
        onClick = onClick
    )
}

@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    GymberComposeTheme {
        DashboardScreenBody(onClick = { })
    }
}
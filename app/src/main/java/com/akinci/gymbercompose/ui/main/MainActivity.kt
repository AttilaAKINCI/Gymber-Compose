package com.akinci.gymbercompose.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.akinci.gymbercompose.ui.components.NetworkDependentScreen
import com.akinci.gymbercompose.ui.feature.dashboard.view.DashboardScreenBody
import com.akinci.gymbercompose.ui.feature.detail.view.DetailScreenBody
import com.akinci.gymbercompose.ui.feature.splash.view.SplashScreenBody
import com.akinci.gymbercompose.ui.main.navigation.Navigation
import com.akinci.gymbercompose.ui.main.util.GymberAppState
import com.akinci.gymbercompose.ui.main.util.rememberGymberAppState
import com.akinci.gymbercompose.ui.theme.GymberComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GymberApp()
        }
    }
}

@Composable
fun GymberApp(
    appState: GymberAppState = rememberGymberAppState()
){
    GymberComposeTheme {
        MainNavHost(appState)
    }
}

@Composable
fun MainNavHost(
    appState: GymberAppState,
    modifier: Modifier = Modifier
){
    NavHost(
        navController = appState.navController,
        startDestination = Navigation.Splash.route,
        modifier = modifier
    ){
        composable(route = Navigation.Splash.route){
            SplashScreenBody(onClick = { appState.navigate(Navigation.Dashboard, from = it) })
        }
        composable(route = Navigation.Dashboard.route){
            /** For a trial Dashboard Screen is marked as "Network Dependent Screen" (NDS) **/
            NetworkDependentScreen(retryAction = { appState.navigateBack() }) {
                DashboardScreenBody(onClick = { appState.navigate(Navigation.Detail, from = it) })
            }
        }
        composable(route = Navigation.Detail.route){
            DetailScreenBody(onClick = { appState.navigateBack() })
        }
    }
}
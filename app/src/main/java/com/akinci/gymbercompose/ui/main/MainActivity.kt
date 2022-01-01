package com.akinci.gymbercompose.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.akinci.gymbercompose.ui.feature.dashboard.view.DashboardScreen
import com.akinci.gymbercompose.ui.feature.detail.view.DetailScreenBody
import com.akinci.gymbercompose.ui.feature.splash.view.SplashScreen
import com.akinci.gymbercompose.ui.main.navigation.Navigation
import com.akinci.gymbercompose.ui.main.util.GymberAppState
import com.akinci.gymbercompose.ui.main.util.rememberGymberAppState
import com.akinci.gymbercompose.ui.theme.GymberComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: ComponentActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GymberApp()
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun GymberApp(
    appState: GymberAppState = rememberGymberAppState()
){
    GymberComposeTheme {
        MainNavHost(appState)
    }
}

@ExperimentalAnimationApi
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
            SplashScreen(onTimeout = { appState.navigate(Navigation.Dashboard, from = it) })
        }
        composable(route = Navigation.Dashboard.route){
            DashboardScreen(onNavigateToDetail = { appState.navigate(Navigation.Detail, from = it) })
        }
        composable(route = Navigation.Detail.route){
            DetailScreenBody(onClick = { appState.navigateBack() })
        }
    }
}
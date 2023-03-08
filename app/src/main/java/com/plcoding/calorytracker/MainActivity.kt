package com.plcoding.calorytracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.plcoding.calorytracker.navigation.navigate
import com.plcoding.calorytracker.ui.theme.CaloryTrackerTheme
import com.plcoding.core.navigation.Route
import com.plcoding.onboarding_presentation.welcome.WelcomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CaloryTrackerTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Route.WELCOME_ROUTE
                ) {
                    composable(route = Route.WELCOME_ROUTE) {
                        WelcomeScreen(onNavigate = navController::navigate)
                    }
                    composable(route = Route.AGE_ROUTE) {
                    }
                    composable(route = Route.GENDER_ROUTE) {
                    }
                    composable(route = Route.HEIGHT_ROUTE) {

                    }
                    composable(route = Route.WEIGHT_ROUTE) {

                    }
                    composable(route = Route.NUTRIENT_GOAL_ROUTE) {

                    }
                    composable(route = Route.ACTIVITY_ROUTE) {

                    }
                    composable(route = Route.GOAL_ROUTE) {

                    }
                    composable(route = Route.TRACKER_OVERVIEW_ROUTE) {

                    }
                    composable(route = Route.SEARCH_ROUTE) {

                    }

                }
            }
        }
    }
}
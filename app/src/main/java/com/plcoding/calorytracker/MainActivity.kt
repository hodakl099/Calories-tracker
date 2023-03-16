package com.plcoding.calorytracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.plcoding.calorytracker.navigation.navigate
import com.plcoding.calorytracker.ui.theme.CaloryTrackerTheme
import com.plcoding.core.navigation.Route
import com.plcoding.onboarding_presentation.activity.ActivityScreen
import com.plcoding.onboarding_presentation.age.AgeScreen
import com.plcoding.onboarding_presentation.gender.GenderScreen
import com.plcoding.onboarding_presentation.goal.GoalScreen
import com.plcoding.onboarding_presentation.height.HeightScreen
import com.plcoding.onboarding_presentation.nutrient_goal.NutrientScreen
import com.plcoding.onboarding_presentation.weight.WeightScreen
import com.plcoding.onboarding_presentation.welcome.WelcomeScreen
import com.plcoding.tracker_presentation.search.SearchScreen
import com.plcoding.tracker_presentation.tracker_overview.TrackerOverviewScreen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CaloryTrackerTheme {
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()

                Scaffold(
                    scaffoldState = scaffoldState
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Route.WELCOME_ROUTE
                    ) {
                        composable(route = Route.WELCOME_ROUTE) {
                            WelcomeScreen(onNavigate = navController::navigate)
                        }
                        composable(route = Route.AGE_ROUTE) {
                            AgeScreen(
                                onNavigate = navController::navigate,
                                scaffoldState = scaffoldState
                            )
                        }
                        composable(route = Route.GENDER_ROUTE) {
                            GenderScreen (onNavigate = navController::navigate)
                        }
                        composable(route = Route.HEIGHT_ROUTE) {
                            HeightScreen(
                                scaffoldState = scaffoldState,
                                onNavigate = navController::navigate
                            )
                        }
                        composable(route = Route.WEIGHT_ROUTE) {
                            WeightScreen(
                                scaffoldState = scaffoldState,
                                onNavigate = navController::navigate
                            )
                        }
                        composable(route = Route.NUTRIENT_GOAL_ROUTE) {
                            NutrientScreen(
                                scaffoldState = scaffoldState,
                                onNavigate = navController::navigate
                            )

                        }
                        composable(route = Route.ACTIVITY_ROUTE) {
                            ActivityScreen(
                                onNavigate = navController::navigate
                            )
                        }
                        composable(route = Route.GOAL_ROUTE) {
                            GoalScreen(
                                onNavigate = navController::navigate
                            )
                        }
                        composable(route = Route.TRACKER_OVERVIEW_ROUTE) {
                            TrackerOverviewScreen(
                                onNavigate = navController::navigate
                            )
                        }
                        composable(
                            route = Route.SEARCH_ROUTE + "/{mealName}/{dayOfMonth}/{month}/{year}",
                            arguments = listOf(
                                navArgument("mealName") {
                                    type = NavType.StringType
                                },
                                navArgument("dayOfMonth") {
                                    type = NavType.IntType
                                },
                                navArgument("month") {
                                    type = NavType.IntType
                                },
                                navArgument("year") {
                                    type = NavType.IntType
                                },
                            )
                        ) {
                            val mealName = it.arguments?.getString("mealName")!!
                            val dayOfMonth = it.arguments?.getInt("dayOfMonth")!!
                            val month = it.arguments?.getInt("month")!!
                            val year = it.arguments?.getInt("year")!!
                            SearchScreen(
                                scaffoldState = scaffoldState,
                                mealName = mealName,
                                dayOfMonth = dayOfMonth,
                                month = month,
                                year = year,
                                onNavigateUp = {
                                    navController.navigateUp()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
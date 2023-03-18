package com.plcoding.calorytracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.plcoding.calorytracker.ui.theme.CaloryTrackerTheme
import com.plcoding.core.domain.preferences.Preferences
import com.plcoding.calorytracker.navigation.Route
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
import javax.inject.Inject


@ExperimentalComposeUiApi
@ExperimentalCoilApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val shouldShowOnboarding = preferences.loadShouldShowOnBoarding()
        setContent {
            CaloryTrackerTheme {
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    scaffoldState = scaffoldState
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = if(shouldShowOnboarding) {
                            Route.WELCOME_ROUTE
                        } else Route.TRACKER_OVERVIEW_ROUTE
                    ) {
                        composable(Route.WELCOME_ROUTE) {
                            WelcomeScreen(onNextClick = {
                                navController.navigate(Route.GENDER_ROUTE)
                            })
                        }
                        composable(Route.AGE_ROUTE) {
                            AgeScreen(
                                scaffoldState = scaffoldState,
                                onNextClick = {
                                    navController.navigate(Route.HEIGHT_ROUTE)
                                }
                            )
                        }
                        composable(Route.GENDER_ROUTE) {
                            GenderScreen(onNextClick = {
                                navController.navigate(Route.AGE_ROUTE)
                            })
                        }
                        composable(Route.HEIGHT_ROUTE) {
                            HeightScreen(
                                scaffoldState = scaffoldState,
                                onNextClick = {
                                    navController.navigate(Route.WEIGHT_ROUTE)
                                }
                            )
                        }
                        composable(Route.WEIGHT_ROUTE) {
                            WeightScreen(
                                scaffoldState = scaffoldState,
                                onNextClick = {
                                    navController.navigate(Route.ACTIVITY_ROUTE)
                                }
                            )
                        }
                        composable(Route.NUTRIENT_GOAL_ROUTE) {
                            NutrientScreen(
                                scaffoldState = scaffoldState,
                                onNextClick = {
                                    navController.navigate(Route.TRACKER_OVERVIEW_ROUTE)
                                }
                            )
                        }
                        composable(Route.ACTIVITY_ROUTE) {
                            ActivityScreen(onNextClick = {
                                navController.navigate(Route.GOAL_ROUTE)
                            })
                        }
                        composable(Route.GOAL_ROUTE) {
                            GoalScreen(onNextClick = {
                                navController.navigate(Route.NUTRIENT_GOAL_ROUTE)
                            })
                        }

                        composable(Route.TRACKER_OVERVIEW_ROUTE) {
                            TrackerOverviewScreen(
                                onNavigateToSearch = { mealName, day, month, year ->
                                    navController.navigate(
                                        Route.SEARCH_ROUTE + "/$mealName" +
                                                "/$day" +
                                                "/$month" +
                                                "/$year"
                                    )
                                }
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
package com.plcoding.calorytracker.navigation

import androidx.navigation.NavController
import com.plcoding.core.util.UiEvent

fun NavController.navigate(navigate: UiEvent.Navigate) {
    this.navigate(navigate.route)
}
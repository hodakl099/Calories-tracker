package com.plcoding.tracker_domain.model

import java.util.*

sealed class MealType(val name: String) {
    object BreakFast : MealType("breakfast")
    object Lunch : MealType("lunch")
    object Dinner : MealType("dinner")
    object Snack : MealType("snack")


    companion object {
        fun fromString(name: String) : MealType {
            return when(name.lowercase(Locale.ROOT)) {
                "breakfast" -> BreakFast
                "lunch" -> Lunch
                "dinner" -> Dinner
                "snack" -> Snack
                else -> BreakFast
            }
        }
    }

}

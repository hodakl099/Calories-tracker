package com.plcoding.tracker_presentation

import com.plcoding.tracker_domain.model.TrackedFood

sealed class TrackerViewEvent {

    object OnNextDayClick : TrackerViewEvent()
    object OnPreviousDayClick : TrackerViewEvent()
    data class OnToggleMealClick(val meal : Meal) : TrackerViewEvent()
    data class OnDeleteTrackedFoodClick(val trackedFood: TrackedFood) : TrackerViewEvent()
    data class OnAddFoodClick(val meal: Meal) : TrackerViewEvent()

}
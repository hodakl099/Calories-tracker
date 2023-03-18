package com.plcoding.tracker_presentation.tracker_overview

import com.plcoding.tracker_domain.model.TrackedFood

sealed class TrackerViewEvent {

    object OnNextDayClick : TrackerViewEvent()
    object OnPreviousDayClick : TrackerViewEvent()
    data class OnToggleMealClick(val meal : Meal) : TrackerViewEvent()
    data class OnDeleteTrackedFoodClick(val trackedFood: TrackedFood) : TrackerViewEvent()

}
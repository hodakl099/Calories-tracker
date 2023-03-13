package com.plcoding.tracker_domain.di

import com.plcoding.core.domain.preferences.Preferences
import com.plcoding.tracker_domain.repository.TrackerRepository
import com.plcoding.tracker_domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Singleton


@Module
@InstallIn(ViewModelComponent::class)
object TrackerDomainModule {

    @Provides
    @Singleton
    fun providesTrackerUseCases(
        repository: TrackerRepository,
        preferences: Preferences
    ) : TrackerUseCases {
        return TrackerUseCases(
            calculateMealNutrients = CalculateMealNutrients(preferences),
            deleteTrackedFood = DeleteTrackedFood(repository),
            getFoodsForDate = GetFoodsForDate(repository),
            searchFood = SearchFood(repository),
            trackFood = TrackFood(repository)
        )
    }
}
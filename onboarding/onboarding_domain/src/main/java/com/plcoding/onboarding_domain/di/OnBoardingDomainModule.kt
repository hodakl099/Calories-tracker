package com.plcoding.onboarding_domain.di

import com.plcoding.onboarding_domain.use_case.ValidateNutrient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Singleton


@Module
@InstallIn(ViewModelComponent::class)
object OnBoardingDomainModule {

    @Provides
    @ViewModelScoped
    fun validateNutrientUseCase() : ValidateNutrient {
        return ValidateNutrient()
    }
}
package com.plcoding.tracker_domain.use_case

import com.google.common.truth.Truth.assertThat
import com.plcoding.core.domain.model.ActivityLevel
import com.plcoding.core.domain.model.Gender
import com.plcoding.core.domain.model.GoalType
import com.plcoding.core.domain.model.UserInfo
import com.plcoding.core.domain.preferences.Preferences
import com.plcoding.tracker_domain.model.MealType
import com.plcoding.tracker_domain.model.TrackedFood
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test
import java.time.LocalDate
import kotlin.random.Random

class CalculatedMealNutrientsTest {

    private lateinit var calculateMealNutrients: CalculateMealNutrients

    @Before
    fun setUp () {
        val preferences = mockk<Preferences>(relaxed = true)

        every { preferences.loadUserInfo()  } returns UserInfo(
            gender = Gender.Male,
            age = 20,
            weight = 180f,
            height = 180,
            activityLevel = ActivityLevel.Medium,
            goalType= GoalType.LoseWeight,
            carbRatio = 0.4f,
            proteinRatio = 0.3f,
            fatRatio = 0.2f
        )
        calculateMealNutrients= CalculateMealNutrients(preferences)
    }

    @Test
    fun `calories for breakfast properly calculated`() {
        val trackedFood = (1..30).map {
            TrackedFood(
                name = "name",
                carbs = Random.nextInt(100),
                protein = Random.nextInt(100),
                fat = Random.nextInt(100),
                imageUrl = null,
                mealType = MealType.BreakFast,
                amount = 100,
                date = LocalDate.now(),
                calories = Random.nextInt(2000)
            )
        }
        val result = calculateMealNutrients(trackedFood)

        val breakFastCalories = result.mealNutrients.values
            .filter {
            it.mealType is MealType.BreakFast
        }
            .sumOf { it.carbs }
        val expectedValues = trackedFood
            .filter {
                it.mealType is MealType.BreakFast
            }
            .sumOf {
            it.carbs
        }

        assertThat(breakFastCalories).isEqualTo(expectedValues)
    }
}
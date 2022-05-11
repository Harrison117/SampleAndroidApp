package com.example.myapplication.ui.score

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.myapplication.R
import com.example.myapplication.ui.main.MainActivity
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class ScoreActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(ScoreActivity::class.java)

    // TODO do sample UI test
    @Test
    fun incrementScore() {
        onView(
            withId(R.id.teamAScore))
            .check(matches(isDisplayed()))
        onView(
            withId(R.id.freeThrowAButton))
            .perform(click())
    }
}
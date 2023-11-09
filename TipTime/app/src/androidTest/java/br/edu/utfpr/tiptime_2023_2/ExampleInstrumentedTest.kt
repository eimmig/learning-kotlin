package br.edu.utfpr.tiptime_2023_2

import android.app.Activity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule()
    val activity = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun useAppContext() {
       onView(ViewMatchers.withId(R.id.cost_of_service))
           .perform(typeText("50.0"))
           .perform(closeSoftKeyboard())

       onView(ViewMatchers.withId(R.id.calculate_button))
           .perform(click())

       onView(ViewMatchers.withId(R.id.tipResult))
           .check(ViewAssertions.matches(ViewMatchers.withText(CoreMatchers.containsString("10.00"))))
    }
}
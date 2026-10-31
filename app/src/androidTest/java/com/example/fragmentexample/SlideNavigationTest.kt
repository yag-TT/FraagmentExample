package com.example.fragmentexample

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SlideNavigationTest {
    @Test
    fun openScrollRestoreAndReturn() {
        ActivityScenario.launch(MainActivity::class.java).use { scenario ->
            onView(withId(R.id.open_slide_page_button)).perform(click())
            onView(withId(R.id.slide_card_list)).check(matches(isDisplayed()))
                .perform(swipeUp())
            var position = 0
            scenario.onActivity { activity ->
                val list = activity.findViewById<RecyclerView>(R.id.slide_card_list)
                position = (list.layoutManager as LinearLayoutManager)
                    .findFirstVisibleItemPosition()
                assertTrue("Swipe should advance to the next card", position > 0)
            }
            scenario.recreate()
            onView(withId(R.id.slide_card_list)).check(matches(isDisplayed()))
            scenario.onActivity { activity ->
                val list = activity.findViewById<RecyclerView>(R.id.slide_card_list)
                assertTrue("Card position should survive recreation",
                    (list.layoutManager as LinearLayoutManager)
                        .findFirstVisibleItemPosition() == position)
            }
            onView(withId(R.id.close_slide_page_button)).perform(click())
            onView(withId(R.id.open_slide_page_button)).check(matches(isDisplayed()))
                .perform(click())
            onView(withId(R.id.slide_card_list)).check(matches(isDisplayed()))
            pressBack()
            onView(withId(R.id.open_slide_page_button)).check(matches(isDisplayed()))
        }
    }
}

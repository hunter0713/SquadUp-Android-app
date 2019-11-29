package com.example.squadup


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class StartGameActivity {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun startGameActivity() {
        val appCompatEditText = onView(
            allOf(
                withId(R.id.usernameLogin),

                isDisplayed()
            )
        )
        appCompatEditText.perform(replaceText("bwheat"), closeSoftKeyboard())

        val appCompatEditText2 = onView(
            allOf(
                withId(R.id.password),

                isDisplayed()
            )
        )
        appCompatEditText2.perform(replaceText("password"), closeSoftKeyboard())

        val appCompatButton = onView(
            allOf(
                withId(R.id.loginButton), withText("Login"),

                isDisplayed()
            )
        )
        appCompatButton.perform(click())

        val appCompatButton2 = onView(
            allOf(
                withId(R.id.profileStartGame), withText("Start Game"),

                isDisplayed()
            )
        )
        appCompatButton2.perform(click())

        val appCompatSpinner = onView(
            allOf(
                withId(R.id.userTeamSpinner),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        appCompatSpinner.perform(click())

        val appCompatSpinner2 = onView(
            allOf(
                withId(R.id.otherTeamSpinner),

                isDisplayed()
            )
        )
        appCompatSpinner2.perform(click())

        val spinner = onView(
            allOf(
                withId(R.id.userTeamSpinner),
                isDisplayed()
            )
        )
        spinner.check(matches(isDisplayed()))

        val spinner2 = onView(
            allOf(
                withId(R.id.otherTeamSpinner),

                isDisplayed()
            )
        )
        spinner2.check(matches(isDisplayed()))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}

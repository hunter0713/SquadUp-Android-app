package com.example.squadup


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
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
class TestSuite {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    fun login(){
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
        Thread.sleep(2000)
    }

    @Test
    fun mainActivityLoadsCorrectly() {
        val textView = onView(
            allOf(
                withId(R.id.loginTitle), withText("SquadUp"),
                isDisplayed()
            )
        )
        textView.check(matches(withText("SquadUp")))


    }

    @Test
    fun MainActivityLoadsCorrectly() {
        val button = onView(
            allOf(
                withId(R.id.loginButton),

                isDisplayed()
            )
        )
        button.check(matches(isDisplayed()))
    }

    @Test
    fun CorrectProfileAccessed() {
        login()
        val textView2 = onView(
            allOf(
                withId(R.id.profileTitle),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("Welcome, bwheat!")))
    }
    @Test
    fun CorrectNumberOfWins() {
        login()
        val textView3 = onView(
            allOf(
                withId(R.id.profileWins),

                isDisplayed()
            )
        )
        textView3.check(matches(isDisplayed()))
    }
    @Test
    fun CorrectNumberOfLosses() {
        login()
        val textView4 = onView(
            allOf(
                withId(R.id.profileLosses),

                isDisplayed()
            )
        )
        textView4.check(matches(isDisplayed()))
    }
    @Test
    fun JoinTeamLoadsCorrectly() {
        login()
        val appCompatButton2 = onView(
            allOf(
                withId(R.id.profileJoinTeamButton), withText("Join Team"),

                isDisplayed()
            )
        )
        appCompatButton2.perform(click())

        val button2 = onView(
            allOf(
                withId(R.id.joinTeamButton),

                isDisplayed()
            )
        )
        button2.check(matches(isDisplayed()))

        pressBack()
    }

    @Test
    fun startGameLoadsCorrectly() {
        login()
        val appCompatButton2 = onView(
            allOf(
                withId(R.id.profileStartGame), withText("Start Game"),

                isDisplayed()
            )
        )
        appCompatButton2.perform(click())
        Thread.sleep(2000)

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

    @Test
    fun WinlessProfileShows0Wins() {
        val appCompatEditText = onView(
            allOf(
                withId(R.id.usernameLogin),

                isDisplayed()
            )
        )
        appCompatEditText.perform(replaceText("testthis"), closeSoftKeyboard())

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
        Thread.sleep(2000)
        val textView3 = onView(
            allOf(
                withId(R.id.profileWins), withText("0"),

                isDisplayed()
            )
        )
        textView3.check(matches(isDisplayed()))
    }

    @Test
    fun LosslessProfileShows0Losses() {
        val appCompatEditText = onView(
            allOf(
                withId(R.id.usernameLogin),

                isDisplayed()
            )
        )
        appCompatEditText.perform(replaceText("testthis"), closeSoftKeyboard())

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
        Thread.sleep(2000)
        val textView3 = onView(
            allOf(
                withId(R.id.profileLosses), withText("0"),

                isDisplayed()
            )
        )
        textView3.check(matches(isDisplayed()))
    }

    @Test
    fun ErrorIfUSernameAlreadyExists() {
        val appCompatTextView = onView(
            allOf(
                withId(R.id.clickToRegister), withText("Don't Have an Account Yet? Register Here!"),
                isDisplayed()
            )
        )
        appCompatTextView.perform(click())

        val appCompatEditText = onView(
            allOf(
                withId(R.id.registerUsername),

                isDisplayed()
            )
        )
        appCompatEditText.perform(replaceText("bwheat"), closeSoftKeyboard())

        val appCompatEditText2 = onView(
            allOf(
                withId(R.id.registerPagePass1),

                isDisplayed()
            )
        )
        appCompatEditText2.perform(replaceText("test"), closeSoftKeyboard())

        val appCompatEditText3 = onView(
            allOf(
                withId(R.id.registerPagePass2),

                isDisplayed()
            )
        )
        appCompatEditText3.perform(replaceText("test"), closeSoftKeyboard())

        val appCompatButton = onView(
            allOf(
                withId(R.id.registerButton), withText("Register"),

                isDisplayed()
            )
        )
        appCompatButton.perform(click())

        val button = onView(
            allOf(
                withId(R.id.registerButton),

                isDisplayed()
            )
        )
        Thread.sleep(2000)
        button.check(matches(isDisplayed()))
    }

    @Test
    fun UnmatchingPasswordsReportsError() {
        val appCompatTextView = onView(
            allOf(
                withId(R.id.clickToRegister), withText("Don't Have an Account Yet? Register Here!"),
                isDisplayed()
            )
        )
        appCompatTextView.perform(click())

        val appCompatEditText = onView(
            allOf(
                withId(R.id.registerUsername),

                isDisplayed()
            )
        )
        appCompatEditText.perform(replaceText("testthis1"), closeSoftKeyboard())

        val appCompatEditText2 = onView(
            allOf(
                withId(R.id.registerPagePass1),

                isDisplayed()
            )
        )
        appCompatEditText2.perform(replaceText("test"), closeSoftKeyboard())

        val appCompatEditText3 = onView(
            allOf(
                withId(R.id.registerPagePass2),

                isDisplayed()
            )
        )
        appCompatEditText3.perform(replaceText("tests"), closeSoftKeyboard())

        val appCompatButton = onView(
            allOf(
                withId(R.id.registerButton), withText("Register"),

                isDisplayed()
            )
        )
        appCompatButton.perform(click())

        val button = onView(
            allOf(
                withId(R.id.registerButton),

                isDisplayed()
            )
        )
        Thread.sleep(2000)
        button.check(matches(isDisplayed()))
    }

    @Test
    fun EmptyUsernameFieldStopsLogin() {
        val appCompatEditText = onView(
            allOf(
                withId(R.id.usernameLogin),

                isDisplayed()
            )
        )
        appCompatEditText.perform(replaceText("testthis"), closeSoftKeyboard())
        val appCompatButton = onView(
            allOf(
                withId(R.id.loginButton), withText("Login"),

                isDisplayed()
            )
        )
        appCompatButton.perform(click())

        val textView = onView(
            allOf(
                withId(R.id.loginTitle), withText("SquadUp"),

                isDisplayed()
            )
        )
        textView.check(matches(withText("SquadUp")))
    }

    fun EmptyPasswordFieldStopsLogin() {
        val appCompatEditText = onView(
            allOf(
                withId(R.id.password),

                isDisplayed()
            )
        )
        appCompatEditText.perform(replaceText("testthis"), closeSoftKeyboard())
        val appCompatButton = onView(
            allOf(
                withId(R.id.loginButton), withText("Login"),

                isDisplayed()
            )
        )
        appCompatButton.perform(click())

        val textView = onView(
            allOf(
                withId(R.id.loginTitle), withText("SquadUp"),

                isDisplayed()
            )
        )
        textView.check(matches(withText("SquadUp")))
    }

    @Test
    fun CreateTeamLoadsCorrectly() {
        login()
        val appCompatButton2 = onView(
            allOf(
                withId(R.id.profileCreateTeam), withText("Create Team"),

                isDisplayed()
            )
        )
        appCompatButton2.perform(click())

        val textView = onView(
            allOf(
                withId(R.id.registerTeamTitle), withText("Create Team"),

                isDisplayed()
            )
        )
        textView.check(matches(withText("Create Team")))
    }

    @Test
    fun YourTeamsLoadsCorrectly() {
        login()
        val appCompatButton2 = onView(
            allOf(
                withId(R.id.goToTeamPage),

                isDisplayed()
            )
        )
        appCompatButton2.perform(click())
        Thread.sleep(2000)

        val spinner = onView(
            allOf(
                withId(R.id.userTeamPageTitle),
                isDisplayed()
            )
        )
        spinner.check(matches(isDisplayed()))

    }

}

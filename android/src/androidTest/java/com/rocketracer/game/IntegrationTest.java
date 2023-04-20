package com.rocketracer.game;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class IntegrationTest {

    @Rule
    public ActivityScenarioRule<AndroidLauncher> mActivityRule = new ActivityScenarioRule<>(AndroidLauncher.class);

    @Test
    public void integrationTest() {
        // TODO: Implement integration test.

        mActivityRule.getScenario().onActivity(activity -> {
            // Perform operations on the activity instance
        });
        // Perform some UI interactions using Espresso
        //onView(withId(R.id)).perform(click());

        // Verify UI changes
        //onView(withId(R.id.textView)).check(matches(withText("Expected text")));

        // Initialize Firebase Test Lab
        // ...

        // Perform Firebase-related tasks, such as reading and writing data
        // ...

        // Assert the expected results
        // ...
    }

    @Test
    public void checkUIElements() {
        onView(withText("Write your player-name")).check(matches(isDisplayed()));
        onView(withText("Go to main-page")).check(matches(isDisplayed()));
    }

    @Test
    public void inputPlayerNameAndNavigateToMainView() {
        String playerName = "TestPlayer";


        onView(withText("Go to main-page")).perform(click());

        // Add assertions or additional actions to verify if MainView is displayed
    }
}




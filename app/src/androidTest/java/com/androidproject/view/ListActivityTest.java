package com.androidproject.view;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.runner.AndroidJUnit4;


import com.androidproject.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import android.support.test.rule.ActivityTestRule;

@RunWith(AndroidJUnit4.class)
public class ListActivityTest {

    @Rule
    public ActivityTestRule<ListActivity> mActivityRule = new ActivityTestRule<>(
            ListActivity.class);
    @Test
    public void checkIfViewIsSet_shouldPass() {

        onView(ViewMatchers.withId(R.id.recyclerViewList))
                .perform(click())
                .check(matches(isDisplayed()));

        onView(withId(R.id.recyclerViewList))
                .perform(swipeUp());

        onView(withId(R.id.swipeRefreshLayout))
                .perform(swipeDown());
    }


}

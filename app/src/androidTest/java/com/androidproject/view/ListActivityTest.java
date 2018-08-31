package com.androidproject.view;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
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
import static junit.framework.TestCase.assertTrue;

import android.support.test.rule.ActivityTestRule;
import android.util.Log;

@RunWith(AndroidJUnit4.class)
public class ListActivityTest {

    private static final String NETWORK_ERROR_STRING_PASS = "Oops! It seems you are not connected with internet.";
    private static final String NETWORK_ERROR_STRING_FAIL = "It seems you are not connected with internet.";
    private static final String RETRY_MESSAGE = "Hit Retry or Swipe down to try again.";

    Context mContext;

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

    @Test
    public void checkNetworkErrorMsg_shouldPass() {

        assertTrue(InstrumentationRegistry.getTargetContext().getResources()
                .getString(R.string.no_internet_error).equals(NETWORK_ERROR_STRING_PASS));
    }

    @Test
    public void checkNetworkErrorMsg_shouldFail() {

        assertTrue(InstrumentationRegistry.getTargetContext().getResources()
                .getString(R.string.no_internet_error).equals(NETWORK_ERROR_STRING_FAIL));
    }

    @Test
    public void checkRetryMsg_shouldPass() {

        assertTrue(InstrumentationRegistry.getTargetContext().getResources()
                .getString(R.string.retry_msg).equals(RETRY_MESSAGE));
    }


}

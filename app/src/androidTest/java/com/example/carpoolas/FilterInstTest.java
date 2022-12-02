package com.example.carpoolas;

import static androidx.test.espresso.assertion.ViewAssertions.matches;

import android.os.SystemClock;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.carpoolas.controller.MainActivity;

import org.junit.Test;

public class FilterInstTest {
    @org.junit.Rule
    public ActivityScenarioRule<MainActivity> activityRule
            = new ActivityScenarioRule<>(MainActivity.class);

    /**
     * tests filter listing use case by first creating account then filtering Page of listings
     */
    @Test
    public void testCreateListing(){
        //create listings
        CreateListingInstTest createListingInstTest = new CreateListingInstTest();
        createListingInstTest.testCreateListing();

        //click filter listing option
        ViewInteraction filterListingButtonVI = Espresso.onView(ViewMatchers.withId(R.id.searchListingsButton));
        filterListingButtonVI.perform(ViewActions.click());

        //select passenger
        ViewInteraction passButtonVI = Espresso.onView(ViewMatchers.withId(R.id.passengerRadioButton));
        passButtonVI.perform(ViewActions.click());
        passButtonVI.check(matches((ViewMatchers.isChecked())));

        //seats
        ViewInteraction seatsTextVI = Espresso.onView(ViewMatchers.withId(R.id.enterSeats));
        seatsTextVI.perform(ViewActions.typeText("4"));

        Espresso.closeSoftKeyboard();

        //date
        ViewInteraction dateTextVI = Espresso.onView(ViewMatchers.withId(R.id.enterDate));
        dateTextVI.perform(ViewActions.typeText("11/13/2022"));

        Espresso.closeSoftKeyboard();

        //time
        ViewInteraction timeTextVI = Espresso.onView(ViewMatchers.withId(R.id.enterTime));
        timeTextVI.perform(ViewActions.typeText("10:30"));

        Espresso.closeSoftKeyboard();

        ViewInteraction startTextVI = Espresso.onView(ViewMatchers.withId(R.id.enterStartLocation));
        startTextVI.perform(ViewActions.typeText("124 Ray Ave, Pough, NY 12604"));

        Espresso.closeSoftKeyboard();

        ViewInteraction endTextVI = Espresso.onView(ViewMatchers.withId(R.id.enterEndLocation));
        endTextVI.perform(ViewActions.typeText("3 Ray Ave, Pough, NY 12604"));

        Espresso.closeSoftKeyboard();

        //filter listings
        ViewInteraction createButtonVI = Espresso.onView(ViewMatchers.withId(R.id.filterButton));
        createButtonVI.perform(ViewActions.click());

        //check screen
        SystemClock.sleep(3000);

    }
}

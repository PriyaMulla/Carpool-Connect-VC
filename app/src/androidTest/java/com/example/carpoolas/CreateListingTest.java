package com.example.carpoolas;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.carpoolas.controller.MainActivity;

import org.junit.Test;
public class CreateListingTest {
    @org.junit.Rule
    public ActivityScenarioRule<MainActivity> activityRule
            = new ActivityScenarioRule<>(MainActivity.class);

    /**
     * tests create listing use case by first creating account then listing
     */

    @Test
    public void testCreateListing(){
        //create account
        CreateAccountInstTest createAccountInstTest = new CreateAccountInstTest();
        createAccountInstTest.testCreateAccount();

        //click create listing option
        ViewInteraction createListingButtonVI = Espresso.onView(ViewMatchers.withId(R.id.createListingButton));
        createListingButtonVI.perform(ViewActions.click());

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

        //add listing
        ViewInteraction createButtonVI = Espresso.onView(ViewMatchers.withId(R.id.addButton));
        createButtonVI.perform(ViewActions.click());

    }
}

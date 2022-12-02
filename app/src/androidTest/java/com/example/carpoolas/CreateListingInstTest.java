package com.example.carpoolas;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

import static org.hamcrest.Matchers.allOf;

import android.os.SystemClock;
import android.widget.ArrayAdapter;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.carpoolas.controller.MainActivity;

import org.hamcrest.Matchers;
import org.junit.Test;
public class CreateListingInstTest {
    @org.junit.Rule
    public ActivityScenarioRule<MainActivity> activityRule
            = new ActivityScenarioRule<>(MainActivity.class);

    /**
     * tests create listing use case by first creating account then listing
     */

    @Test
    public void testCreateListing(){
        //create account
        LogInInstTest logInInstTest = new LogInInstTest();
        logInInstTest.testLogIn();

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

        //check that it added
        DataInteraction pageListing = Espresso.onData(Matchers.anything()).inAdapterView(ViewMatchers.withId(R.id.listview)).atPosition(0);
        pageListing.check(ViewAssertions.matches(ViewMatchers.withSubstring("Role: Passenger")));

        //add another listing
        //click create listing option
        createListingButtonVI.perform(ViewActions.click());

        //select driver
        ViewInteraction driveButtonVI = Espresso.onView(ViewMatchers.withId(R.id.driverRadioButton));
        driveButtonVI.perform(ViewActions.click());
        driveButtonVI.check(matches((ViewMatchers.isChecked())));

        //seats
        seatsTextVI.perform(ViewActions.typeText("2"));

        Espresso.closeSoftKeyboard();

        //date
        dateTextVI.perform(ViewActions.typeText("12/15/2022"));

        Espresso.closeSoftKeyboard();

        //time
        timeTextVI.perform(ViewActions.typeText("9:30"));

        Espresso.closeSoftKeyboard();

        startTextVI.perform(ViewActions.typeText("13 Hi St, Waun, CO 53598"));

        Espresso.closeSoftKeyboard();

        endTextVI.perform(ViewActions.typeText("15 Hello Ave, Drop, CO 53597"));

        Espresso.closeSoftKeyboard();

        //add listing
        createButtonVI.perform(ViewActions.click());

        //check that it added
        pageListing = Espresso.onData(Matchers.anything()).inAdapterView(ViewMatchers.withId(R.id.listview)).atPosition(1);
        pageListing.check(ViewAssertions.matches(ViewMatchers.withSubstring("Role: Driver")));

        //check screen
        SystemClock.sleep(3000);

    }
}

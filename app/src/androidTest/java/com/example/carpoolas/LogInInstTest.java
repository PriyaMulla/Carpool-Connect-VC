package com.example.carpoolas;
import android.os.SystemClock;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.carpoolas.controller.MainActivity;

import org.junit.Test;

public class LogInInstTest {
    @org.junit.Rule
    public ActivityScenarioRule<MainActivity> activityRule
            = new ActivityScenarioRule<>(MainActivity.class);

    /**
     * tests use case of create account
     */
    @Test
    public void testLogIn(){
        //input username
        ViewInteraction usernameVI = Espresso.onView(ViewMatchers.withId(R.id.usernameText));
        usernameVI.perform(ViewActions.typeText("priyankamu"));

        Espresso.closeSoftKeyboard();

        //input password
        ViewInteraction passwordVI = Espresso.onView(ViewMatchers.withId(R.id.passwordText));
        passwordVI.perform(ViewActions.typeText("helloHi!"));

        Espresso.closeSoftKeyboard();

        //click login
        ViewInteraction createButtonVI = Espresso.onView(ViewMatchers.withId(R.id.logInButton));
        createButtonVI.perform(ViewActions.click());

        //check screen
        SystemClock.sleep(3000);

    }

}

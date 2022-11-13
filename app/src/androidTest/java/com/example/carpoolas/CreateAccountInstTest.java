package com.example.carpoolas;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.carpoolas.controller.MainActivity;

import org.junit.Test;

public class CreateAccountInstTest {
    @org.junit.Rule
    public ActivityScenarioRule<MainActivity> activityRule
            = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testCreateAccount(){


        ViewInteraction nameTextVI = Espresso.onView(ViewMatchers.withId(R.id.enterName));
        nameTextVI.perform(ViewActions.typeText("Priya Muldowney"));

        Espresso.closeSoftKeyboard();

        ViewInteraction emailTextVI = Espresso.onView(ViewMatchers.withId(R.id.enterEmailAddress));
        emailTextVI.perform(ViewActions.typeText("p@vassar.edu"));

        Espresso.closeSoftKeyboard();

        ViewInteraction usernameTextVI = Espresso.onView(ViewMatchers.withId(R.id.enterUsername));
        usernameTextVI.perform(ViewActions.typeText("priyankamu"));

        Espresso.closeSoftKeyboard();

        ViewInteraction passwordTextVI = Espresso.onView(ViewMatchers.withId(R.id.enterPassword));
        passwordTextVI.perform(ViewActions.typeText("hello!Hi"));

        Espresso.closeSoftKeyboard();

        ViewInteraction createButtonVI = Espresso.onView(ViewMatchers.withId(R.id.createButton));
        createButtonVI.perform(ViewActions.click());



    }


}

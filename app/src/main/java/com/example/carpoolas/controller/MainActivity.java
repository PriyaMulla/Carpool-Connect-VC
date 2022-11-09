package com.example.carpoolas.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.carpoolas.model.Account;
import com.example.carpoolas.model.Listing;
import com.example.carpoolas.model.PageOfListings;
import com.example.carpoolas.view.CreateAccountFragment;
import com.example.carpoolas.view.CreateListingFragment;
import com.example.carpoolas.view.ICreateListingView;
import com.example.carpoolas.view.IMainView;
import com.example.carpoolas.view.MainView;
import com.example.carpoolas.view.ICreateAccountView;

import java.util.Date;

public class MainActivity extends AppCompatActivity implements ICreateAccountView.Listener, ICreateListingView.Listener {

    //Listing curLst = new Listing(Date created, String role, Date dateTime, String start, String end,  int seats, int listingID);
    //Account acc = new Account(username, password, name, email);

    CollOfAccts accounts = new CollAccts();
    IMainView mainView;

    /**
     * Called whenever the activity is (re)created.
     * @param savedInstanceState saved data from prior instantiation (ignore for now)
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        this.mainView = new MainView(this); // create the main screen view

        //to begin with, make the screen display the create account fragment
        CreateAccountFragment createAccountFragment = new CreateAccountFragment(this);
        this.mainView.displayFragment(createAccountFragment, true, "create account");

        CreateListingFragment createListingFragment = new CreateListingFragment(this);
        this.mainView.displayFragment(createListingFragment, true, "create listing");

        setContentView(this.mainView.getRootView()); //display fragment
    }

    //here put validations/snackbar?

    public static boolean isValidName (String input){
        char ch;
        for (int i = 0; i < input.length(); i++){
            ch = input.charAt(i);
            if (Character.isWhitespace(ch) && i > 0 && i < input.length()){
                return true;
            }
        }
        return false;
    }

    public static boolean isValidUsername (String input){
        return input.length() > 5;
    }

    public static boolean isValidPassword (String input){

    }

    /**
     * React to the user's intention of adding a new item onto the collection of accounts.
     * @param name name of user
     * @param username username of user
     * @param password password of user
     * @param email email of user
     * @param view the view where the event originated
     */
    @Override //addAccount be on collection of Accounts
    public void onCreateAccount(@NonNull String username, String password, String name, String email, @NonNull ICreateAccountView view) {
    this.accounts.addAccount(username,password,name,email);
    }

    @Override
    public void onCreateListing(@NonNull Date created, String role, Date dateTime, String start, String end, int seats, int listingID, @NonNull ICreateListingView view){

    }
}
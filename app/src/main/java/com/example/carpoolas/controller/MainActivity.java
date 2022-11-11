package com.example.carpoolas.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.carpoolas.model.Account;
import com.example.carpoolas.model.CollectionOfAccounts;
import com.example.carpoolas.model.Listing;
import com.example.carpoolas.model.PageOfListings;
import com.example.carpoolas.view.CreateAccountFragment;
import com.example.carpoolas.view.CreateListingFragment;
import com.example.carpoolas.view.ICreateListingView;
import com.example.carpoolas.view.IFilterView;
import com.example.carpoolas.view.IMainView;
import com.example.carpoolas.view.MainView;
import com.example.carpoolas.view.ICreateAccountView;
import com.google.android.material.snackbar.Snackbar;

import java.util.Date;

public class MainActivity extends AppCompatActivity implements ICreateAccountView.Listener, ICreateListingView.Listener, IFilterView.Listener {

    //Listing curLst = new Listing(Date created, String role, Date dateTime, String start, String end,  int seats, int listingID);
    //Account acc = new Account(username, password, name, email);

    CollectionOfAccounts accounts = new CollectionOfAccounts();
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

        setContentView(this.mainView.getRootView()); //display fragment
    }

    //Validations

    public static boolean isValidName(String input){
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
    char ch;
    boolean capitalFlag = false;
    boolean specialFlag = false;
    if (!(input.length() > 5)){
    return false;
    }
    for (int i = 0; i < input.length(); i++){
        ch = input.charAt(i);
        if (Character.isUpperCase(ch)){
            capitalFlag = true;
        } else if (!Character.isLetter(ch) && !Character.isWhitespace(ch)){
            specialFlag = true;
        }
        if (capitalFlag && specialFlag){
            return true;
        }
    }
    return false;
    }

    public static boolean isValidEmail (String input){
        return ((input.contains("@vassar.edu")) && (input.length() > 11));
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

        //switch to welcome user fragment with buttons
    }

    @Override
    public void onCreateListing(@NonNull Date created, String role, Date dateTime, String start, String end, int seats, int listingID, @NonNull ICreateListingView view){

    }

    @Override
    public void onFilter(@NonNull IFilterView view) {

    }
}
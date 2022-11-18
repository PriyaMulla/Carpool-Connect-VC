package com.example.carpoolas.controller;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;

import com.example.carpoolas.model.CollectionOfAccounts;
import com.example.carpoolas.model.DateFilter;
import com.example.carpoolas.model.EndFilter;
import com.example.carpoolas.model.IFilter;
import com.example.carpoolas.model.Listing;
import com.example.carpoolas.model.PageOfListings;
import com.example.carpoolas.model.RoleFilter;
import com.example.carpoolas.model.StartFilter;
import com.example.carpoolas.view.CreateAccountFragment;
import com.example.carpoolas.view.CreateListingFragment;
import com.example.carpoolas.view.DashboardFragment;
import com.example.carpoolas.view.FilterFragment;
import com.example.carpoolas.view.ICreateListingView;
import com.example.carpoolas.view.IDashboardView;
import com.example.carpoolas.view.IFilterView;
import com.example.carpoolas.view.IMainView;
import com.example.carpoolas.view.MainView;
import com.example.carpoolas.view.ICreateAccountView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements ICreateAccountView.Listener, ICreateListingView.Listener, IFilterView.Listener, IDashboardView.Listener {

    CollectionOfAccounts accounts = new CollectionOfAccounts();
    static PageOfListings listings = new PageOfListings();
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

    public CreateListingFragment getListingFragListener(){
        CreateListingFragment createListingFragment = new CreateListingFragment(this);
        return createListingFragment;
    }
    public DashboardFragment getDashboardFragListener(){
        DashboardFragment dashboardFragment = new DashboardFragment(this);
        return dashboardFragment;
    }
    public FilterFragment getSearchFragListener(){
        FilterFragment filterFragment = new FilterFragment(this);
        return filterFragment;
    }


    public PageOfListings getListing() {
        return listings;
    }

    /**
     * React to the user's intention of adding a new account onto the collection of accounts.
     * @param name name of user
     * @param username username of user
     * @param password password of user
     * @param email email of user
     * @param view the view where the event originated
     */
    @Override //addAccount be on collection of Accounts
    public void onCreateAccount(@NonNull String username, String password, String name, String email, @NonNull ICreateAccountView view) {
        this.accounts.addAccount(username,password,name,email);
        this.mainView.displayFragment(new DashboardFragment(this),true,"dashboard");

        //switch to welcome user fragment with buttons
    }

    @Override
    public void onCreateListing(@NonNull Date created, String role, Date dateTime, String start, String end, int seats, @NonNull ICreateListingView view){
        this.listings.addListing(created, role, dateTime, start, end, seats);
        this.mainView.displayFragment(new DashboardFragment(this),true,"dashboard");

    }


    public PageOfListings getListings() {
        return listings;
    }

    @Override
    public void onFilter(@NonNull PageOfListings lst, Set<IFilter> filterSet, @NonNull IFilterView view) {
        //TODO:call generic filter method here, for every member of the set filter

        //display filtered listings
        this.mainView.displayFragment(new MainActivity().getDashboardFragListener(),true,"dashboard");
    }
}
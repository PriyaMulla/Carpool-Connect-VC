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
import com.example.carpoolas.model.PageOfListings;
import com.example.carpoolas.model.RoleFilter;
import com.example.carpoolas.model.StartFilter;
import com.example.carpoolas.view.CreateAccountFragment;
import com.example.carpoolas.view.CreateListingFragment;
import com.example.carpoolas.view.DashboardFragment;
import com.example.carpoolas.view.FilterFragment;
import com.example.carpoolas.view.ICreateListingView;
import com.example.carpoolas.view.IFilterView;
import com.example.carpoolas.view.IMainView;
import com.example.carpoolas.view.MainView;
import com.example.carpoolas.view.ICreateAccountView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements ICreateAccountView.Listener, ICreateListingView.Listener, IFilterView.Listener {

    CollectionOfAccounts accounts = new CollectionOfAccounts();
    PageOfListings listings = new PageOfListings();
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
    //Account

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

    //Listing
    public static boolean isValidDateTime(String dateTime){
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        try{
            formatter.parse(dateTime);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    public static boolean isValidStart(String start){
        return Pattern.compile("^(\\d{1,}) [a-zA-Z0-9\\s]+(\\,)? [a-zA-Z]+(\\,)? [A-Z]{2} [0-9]{5,6}$")
                .matcher(start)
                .find();
    }

    public static boolean isValidEnd(String end){
        return Pattern.compile("^(\\d{1,}) [a-zA-Z0-9\\s]+(\\,)? [a-zA-Z]+(\\,)? [A-Z]{2} [0-9]{5,6}$")
                .matcher(end)
                .find();
    }

    public static boolean isValidSeats(String seats){
        try{
            int numSeats = Integer.parseInt(seats.toString());
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    //filters
    //date
    public static PageOfListings filterDates(Date date, PageOfListings filteredPage){
        DateFilter dateFilter = new DateFilter();
        dateFilter.dDate = date;
        dateFilter.filterListings(filteredPage);
        return filteredPage = dateFilter.newPage;
    }

    //start location
    public static PageOfListings filterStart(String start, PageOfListings filteredPage){
        StartFilter startFilter = new StartFilter();
        startFilter.dStart = start;
        startFilter.filterListings(filteredPage);
        return filteredPage = startFilter.newPage;
    }

    //end location
    public static PageOfListings filterEnd(String end, PageOfListings filteredPage){
        EndFilter endFilter = new EndFilter();
        endFilter.dEnd = end;
        endFilter.filterListings(filteredPage);
        return filteredPage = endFilter.newPage;
    }

    //driver
    public static PageOfListings filterDriverRole(PageOfListings filteredPage){
        RoleFilter roleFilter = new RoleFilter();
        roleFilter.dRole = "Driver";
        roleFilter.filterListings(filteredPage);
        return filteredPage = roleFilter.newPage;
    }

    //passenger
    public static PageOfListings filterPassengerRole(PageOfListings filteredPage){
        RoleFilter roleFilter = new RoleFilter();
        roleFilter.dRole = "Passenger";
        roleFilter.filterListings(filteredPage);
        return filteredPage = roleFilter.newPage;
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
        //transition back to Mainview
        //FragmentManager fm = getSupportFragmentManager();
        //fm.popBackStack();
        //fm.executePendingTransactions();
        DashboardFragment dashboardFragment = new DashboardFragment();
        this.mainView.displayFragment(dashboardFragment,true,"dashboard");

        //switch to welcome user fragment with buttons
    }

    @Override
    public void onCreateListing(@NonNull Date created, String role, Date dateTime, String start, String end, int seats, @NonNull ICreateListingView view){
        this.listings.addListing(created, role, dateTime, start, end, seats);
        //transition back to mainview
        //FragmentManager fm = getSupportFragmentManager();
        //fm.popBackStack();
        //fm.executePendingTransactions();
        DashboardFragment dashboardFragment = new DashboardFragment();
        this.mainView.displayFragment(dashboardFragment,true,"dashboard");

    }

    public void goToCreateListing(){
        CreateListingFragment ListingFragment = new CreateListingFragment();
        this.mainView.displayFragment(ListingFragment, true, "create a listing");

    }
    public void goToSearchListing(){
        FilterFragment filterFragment = new FilterFragment();
        this.mainView.displayFragment(filterFragment, true, "create a listing");

    }

    public PageOfListings getListings() {
        return listings;
    }

    @Override
    public void onFilter(@NonNull PageOfListings lst, @NonNull IFilterView view) {
        //display filtered listings
    }
}
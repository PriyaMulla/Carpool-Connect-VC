package com.example.carpoolas.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.carpoolas.model.CollectionOfAccounts;
import com.example.carpoolas.model.CollectionOfListings;
import com.example.carpoolas.model.IFilter;
import com.example.carpoolas.model.Listing;
import com.example.carpoolas.view.CreateAccountFragment;
import com.example.carpoolas.view.CreateListingFragment;
import com.example.carpoolas.view.DashboardFragment;
import com.example.carpoolas.view.DetailedListingFragment;
import com.example.carpoolas.view.FilterFragment;
import com.example.carpoolas.view.ICreateListingView;
import com.example.carpoolas.view.IDashboardView;
import com.example.carpoolas.view.IFilterView;
import com.example.carpoolas.view.ILogInScreen;
import com.example.carpoolas.view.IMainView;
import com.example.carpoolas.view.LogInScreen;
import com.example.carpoolas.view.MainView;
import com.example.carpoolas.view.ICreateAccountView;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements ICreateAccountView.Listener, ICreateListingView.Listener, IFilterView.Listener, IDashboardView.Listener, ILogInScreen.Listener {

    public static final String IN_PROGRESS = "inProgress";
    public static final String IS_SHOWN = "isShown";
    CollectionOfAccounts accounts = new CollectionOfAccounts();
    CollectionOfListings listings = new CollectionOfListings();
    IMainView mainView;
    public static String curState = "";


    /**
     * Called whenever the activity is (re)created.
     * @param savedInstanceState saved data from prior instantiation (ignore for now)
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getSupportFragmentManager().setFragmentFactory(new CarpoolASFragFactory(this));
        super.onCreate(savedInstanceState);


        this.mainView = new MainView(this); // create the main screen view

        if (savedInstanceState == null){
            //to begin with, make the screen display the create account fragment
            LogInScreen logInScreen = new LogInScreen(this);
            curState = "logIn";
            this.mainView.displayFragment(logInScreen, true, "login screen");
        }

        setContentView(this.mainView.getRootView()); //display fragment
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(IS_SHOWN, curState);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //keep track of states
        //Fragment currentFragment = getCurrentFragment();
        //show the controls
        Fragment curFrag = MainActivity.this.mainView.getCurFragment();
        if (curFrag instanceof ILogInScreen) mainView.hideControls();
        if (curFrag instanceof IDashboardView) mainView.showControls();
        if (curFrag instanceof ICreateListingView) mainView.hideControls();
        if (curFrag instanceof ICreateAccountView) {
            LogInScreen logInScreen = new LogInScreen(this);
            this.mainView.displayFragment(logInScreen, true, "'login screen");
            mainView.hideControls();
        }

    }
        //areControlsShown(curState);

        //mainView.showControls();

    public void areControlsShown(String curState){
//        switch (curState){
//            //case "dashboard":
//            case "dashboard":
//                mainView.showControls();
//                break;
//            default:
//                mainView.hideControls();
//                break;
//        }
        mainView.showControls();
    }
    //first put into bundle and save state string
    //then inspect string
    ///if its necessary then show controls
    //else keep controls hidden
    public CollectionOfListings getListing() {
        return listings;
    }

    DashboardFragment dashboardFragment = new DashboardFragment(this);

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
        curState = "dashboard";
        this.mainView.displayFragment(dashboardFragment,true,"dashboard");

    }

    @Override
    public void onCreateListing(@NonNull Date created, String role, Date dateTime, String start, String end, int seats, @NonNull ICreateListingView view){
        this.listings.addListing(created, role, dateTime, start, end, seats);
        curState = "dashboard";
        this.mainView.displayFragment(dashboardFragment,true,"dashboard");

    }

    public CollectionOfListings getListings() {
        return listings;
    }

    @Override
    public void onFilter(@NonNull CollectionOfListings lst, Set<IFilter> filterSet, @NonNull IFilterView view) {
        //TODO:call generic filter method here, for every member of the set filter
        Iterator<IFilter> filterIterator = filterSet.iterator();
        while (filterIterator.hasNext()){
            IFilter filter = filterIterator.next();
            filter.filterListings(lst);
            }


        //display filtered listings
        this.mainView.displayFragment(dashboardFragment,true,"dashboard");
    }
    @Override
    public void goToCreateAccount(@NonNull ILogInScreen view) {
        curState = "logIn";
        this.mainView.displayFragment(new CreateAccountFragment(this),true,"create an account");
    }
    @Override
    public void goToDashboard(@NonNull ILogInScreen view) {
        curState = "logIn";
        this.mainView.displayFragment(dashboardFragment,true,"go to dashboard");
    }

    public void goToDetailedPost(@NonNull IDashboardView view, Listing curListing) {
        this.mainView.displayFragment(new DetailedListingFragment(curListing),true,"go to detailed");
    }
}
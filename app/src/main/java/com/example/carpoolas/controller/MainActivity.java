package com.example.carpoolas.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.carpoolas.R;
import com.example.carpoolas.model.Account;
import com.example.carpoolas.model.CollectionOfAccounts;
import com.example.carpoolas.model.CollectionOfListings;
import com.example.carpoolas.model.IFilter;
import com.example.carpoolas.model.Listing;
import com.example.carpoolas.persistance.FirestoreFacade;
import com.example.carpoolas.persistance.IPersistenceFacade;
import com.example.carpoolas.view.CreateAccountFragment;
import com.example.carpoolas.view.CreateListingFragment;
import com.example.carpoolas.view.DashboardFragment;
import com.example.carpoolas.view.DetailedListingFragment;
import com.example.carpoolas.view.FilterFragment;
import com.example.carpoolas.view.ICreateListingView;
import com.example.carpoolas.view.IDashboardView;
import com.example.carpoolas.view.IDetailedListingView;
import com.example.carpoolas.view.IFilterView;
import com.example.carpoolas.view.ILogInScreen;
import com.example.carpoolas.view.IMainView;
import com.example.carpoolas.view.LogInScreen;
import com.example.carpoolas.view.MainView;
import com.example.carpoolas.view.ICreateAccountView;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements ICreateAccountView.Listener, ICreateListingView.Listener, IFilterView.Listener, IDashboardView.Listener, ILogInScreen.Listener, IDetailedListingView.Listener {

    public static final String IS_SHOWN = "isShown";
    private static final String CUR_LISTING = "curListing";
    CollectionOfAccounts accounts = new CollectionOfAccounts();
    public static CollectionOfListings allListings = new CollectionOfListings();
    public static CollectionOfListings filteredListings = new CollectionOfListings();
    IMainView mainView;
    public static String curState = "";
    Account curAccount;
    public static Listing curListing; //listing currently working on
    IPersistenceFacade persistenceFacade = new FirestoreFacade();
    public static boolean createListUsed = true;



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
        }  else {
            curListing = (Listing) savedInstanceState.getSerializable(CUR_LISTING);
        }


        setContentView(this.mainView.getRootView()); //display fragment
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(IS_SHOWN, curState);
        outState.putSerializable(CUR_LISTING, curListing);
    }

    public CreateListingFragment getListingFragListener(){
        return new CreateListingFragment(this);
    }
    public DashboardFragment getDashboardFragListener(){
        return new DashboardFragment(this);
    }
    public FilterFragment getSearchFragListener(){
        return new FilterFragment(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Fragment curFrag = MainActivity.this.mainView.getCurFragment();
        if (curFrag instanceof ILogInScreen) {
            mainView.hideControls();
            new MainActivity();
        }
        if (curFrag instanceof IDashboardView) {
            mainView.showControls();

            ConstraintLayout layout = mainView.getRootView().findViewById(R.id.mainView);
            layout.setBackgroundColor(Color.parseColor("#FFFFFFFF"));

        }
        if (curFrag instanceof ICreateListingView) mainView.hideControls();
        if (curFrag instanceof IFilterView) mainView.hideControls();
        if (curFrag instanceof ICreateAccountView) {
            LogInScreen logInScreen = new LogInScreen(this);
            this.mainView.displayFragment(logInScreen, true, "'login screen");
            mainView.hideControls();
        }
        if (curFrag instanceof IDetailedListingView){

        }

    }


    public void areControlsShown(String curState){
        mainView.showControls();
    }
    public CollectionOfListings getListing() {
        return allListings;
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
        curAccount = new Account(username,password,name,email);
        this.accounts.addCreatedAccount(curAccount);
        curState = "dashboard";
        this.persistenceFacade.retrieveCollectionOfListings(new IPersistenceFacade.DataListener<CollectionOfListings>() {
            @Override
            public void onDataReceived(@NonNull CollectionOfListings listings) {
                MainActivity.allListings = listings;
                Fragment curFrag = MainActivity.this.mainView.getCurFragment();
                if (curFrag instanceof IDashboardView) // update ledger display if ledger fragment being displayed
                    ((IDashboardView)curFrag).updateDashboardDisplay(MainActivity.allListings);
                dashboardFragment.adapter.notifyDataSetChanged();
            }

            @Override
            public void onNoDataFound() {
            }
        });
        this.persistenceFacade.saveAccount(curAccount);
        //MainActivity.this.curAccount = curAccount;

        this.mainView.displayFragment(dashboardFragment,true,"dashboard");

    }

    @Override
    public void onCreateListing(@NonNull Date created, String role, Date dateTime, String start, String end, int seats, @NonNull ICreateListingView view){
        Listing listing = new Listing(created, role, dateTime, start, end, seats, curAccount);
        createListUsed = true;
        allListings.addCreatedListing(listing);

        this.persistenceFacade.saveListing(listing);
        curState = "dashboard";
        this.mainView.displayFragment(dashboardFragment,true,"dashboard");

    }

    public CollectionOfListings getListings() {
        return allListings;
    }

    @Override
    public void onFilter(@NonNull CollectionOfListings lst, Set<IFilter> filterSet, @NonNull IFilterView view) {
        //TODO:call generic filter method here, for every member of the set filter
        createListUsed = false;
        for (IFilter filter : filterSet) {
            filteredListings = filter.filterListings(lst);
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
    public void goToDashboard() {
        curState = "logIn";

        //load listings
        this.loadListings();

        this.mainView.displayFragment(dashboardFragment,true,"go to dashboard");

    }

    public void goToDetailedPost(@NonNull IDashboardView view) {
        DetailedListingFragment detailedListingFragment = new DetailedListingFragment(this);
        this.mainView.displayFragment(detailedListingFragment, true,"go to detailed");
    }



    public Account getCurAccount() {return curAccount;
    }

    @Override
    public void onSigninAttempt(String username, String password, ILogInScreen view) {

        persistenceFacade.retrieveAccount(username, new IPersistenceFacade.DataListener<Account>() {
            @Override
            public void onDataReceived(@NonNull Account account) {
                if (account.validatePassword(password)){
                    MainActivity.this.curAccount = account;
                    DashboardFragment dashboardFragment = new DashboardFragment(MainActivity.this);
                    MainActivity.this.mainView.displayFragment(dashboardFragment, true, "go to Dashboard");
                } else view.onInvalidCredentials();
            }
            @Override
            public void onNoDataFound() {
                view.onInvalidCredentials();
            }
        });
    }
    public void loadListings(){
        this.persistenceFacade.retrieveCollectionOfListings(new IPersistenceFacade.DataListener<CollectionOfListings>() {
            @Override
            public void onDataReceived(@NonNull CollectionOfListings listings) {
                MainActivity.allListings = listings;
                Fragment curFrag = MainActivity.this.mainView.getCurFragment();
                if (curFrag instanceof IDashboardView) // update ledger display if ledger fragment being displayed
                    ((IDashboardView)curFrag).updateDashboardDisplay(MainActivity.allListings);
                dashboardFragment.adapter.notifyDataSetChanged();
            }

            @Override
            public void onNoDataFound() {
            }
        });
    }
}
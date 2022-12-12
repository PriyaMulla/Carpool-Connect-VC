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
    public static CollectionOfListings allListings = new CollectionOfListings(); //all listings
    public static CollectionOfListings filteredListings = new CollectionOfListings(); //filtered listings
    IMainView mainView;
    public static String curState = "";
    Account curAccount;
    public static Listing curListing; //listing currently working on
    IPersistenceFacade persistenceFacade = new FirestoreFacade();
    public static boolean createListUsed = true;
    DashboardFragment dashboardFragment = new DashboardFragment(this);



    /**
     * Called whenever the activity is (re)created.
     * @param savedInstanceState saved data from prior instantiation
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getSupportFragmentManager().setFragmentFactory(new CarpoolASFragFactory(this));
        super.onCreate(savedInstanceState);

        this.mainView = new MainView(this); // create the main screen view
        setContentView(this.mainView.getRootView()); //display fragment

        if (savedInstanceState == null){
            //to begin with, make the screen display the create account fragment
            LogInScreen logInScreen = new LogInScreen(this);
            curState = "logIn";
            this.mainView.displayFragment(logInScreen, true, "login screen");
        }  else {
            curListing = (Listing) savedInstanceState.getSerializable(CUR_LISTING);
        }

    }

    /**
     * Overridden to save dynamic state before activity destruction.
     * @param outState the bundle to write state to.
     */
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(IS_SHOWN, curState);
        outState.putSerializable(CUR_LISTING, curListing);
    }

    /**
     * get methods
     * @return fragments
     */
    public CreateListingFragment getListingFragListener(){
        return new CreateListingFragment(this);
    }
    public DashboardFragment getDashboardFragListener(){
        return new DashboardFragment(this);
    }
    public FilterFragment getSearchFragListener(){
        return new FilterFragment(this);
    }
    public CollectionOfListings getListings() {
        return allListings;
    }


    /**
     * when back button is pressed, previous screen is shown
     */
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
        if (curFrag instanceof IDetailedListingView)mainView.hideControls();
    }

    /**
     * shows main buttons
     * @param curState current display
     */
    public void areControlsShown(String curState){
        mainView.showControls();
    }


    /* ICreateListingView.Listener interface implementation start */

    /**
     * React to user's intention of creating a new listing and adding to CollectionOfListings
     * @param created when listing created
     * @param role what the role of the user is
     * @param dateTime when user wants to go
     * @param start where user wants to start
     * @param end where user wants to go
     * @param seats how many seats user has or needs
     * @param view where event originated
     */
    @Override
    public void onCreateListing(@NonNull Date created, String role, Date dateTime, String start, String end, int seats, @NonNull ICreateListingView view){
        Listing listing = new Listing(created, role, dateTime, start, end, seats, curAccount);
        createListUsed = true;
        allListings.addCreatedListing(listing);

        //add to firestore
        this.persistenceFacade.saveListing(listing);

        curState = "dashboard";
        this.mainView.displayFragment(dashboardFragment,true,"dashboard");

    }
    /* ICreateListingView.Listener interface implementation end */


    /* IFilterView.Listener interface implementation start */

    /**
     * React to user's intention of filtering all listings
     * @param lst listings that are being filtered
     * @param filterSet what filters are being applied
     * @param view where event originated
     */
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
    /* IFilterView.Listener interface implementation end */


    /* ILogInScreen.Listener interface implementation start */

    /**
     * React to user's intention of wanting to create an account
     * @param view where event originated
     */
    @Override
    public void goToCreateAccount(@NonNull ILogInScreen view) {
        curState = "logIn";
        this.mainView.displayFragment(new CreateAccountFragment(this),true,"create an account");
    }

    /**
     * React to user's logging in and wanting to go to dashboard
     * @param view where event originated
     */
    @Override
    public void goToDashboard(@NonNull ILogInScreen view) {
        curState = "logIn";

        //load listings
        this.persistenceFacade.retrieveCollectionOfListings(new IPersistenceFacade.DataListener<CollectionOfListings>() {
            @Override
            public void onDataReceived(@NonNull CollectionOfListings listings) {
                //display all listings
                MainActivity.allListings = listings;
                Fragment curFrag = MainActivity.this.mainView.getCurFragment();
                if (curFrag instanceof IDashboardView) // update dash display if dash fragment being displayed
                    ((IDashboardView)curFrag).updateDashboardDisplay(MainActivity.allListings);
                dashboardFragment.adapter.notifyDataSetChanged();
            }

            @Override
            public void onNoDataFound() {
            }
        });

        this.mainView.displayFragment(dashboardFragment,true,"go to dashboard");
    }

    /**
     * React to user's attempt to sign in
     * @param username user's username
     * @param password user's password
     * @param view where event originated
     */
    @Override
    public void onSigninAttempt(String username, String password, ILogInScreen view) {

        //retrieve accounts
        persistenceFacade.retrieveAccount(username, new IPersistenceFacade.DataListener<Account>() {
            @Override
            public void onDataReceived(@NonNull Account account) {
                if (account.getPassword().equals(password)){
                    //MainActivity.this.curAccount = account;
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
    /* ILogInScreen.Listener interface implementation end */

    /* IDashboardView.Listener interface implementation start */

    /**
     * React to user's intention of seeing more info about a listing
     * @param view where event originated
     */
    public void goToDetailedPost(@NonNull IDashboardView view) {
        DetailedListingFragment detailedListingFragment = new DetailedListingFragment(this);
        this.mainView.displayFragment(detailedListingFragment, true,"go to detailed");
    }
    /* IDashboardView.Listener interface implementation end */


    /* ICreateAccountView.Listener interface implementation start */
    /**
     * React to the user's intention of adding a new account onto the collection of accounts.
     * @param name name of account
     * @param username username of account
     * @param password password of account
     * @param email email of account
     * @param view the view where the event originated
     */
    @Override //addAccount be on collection of Accounts
    public void onCreateAccount(@NonNull String username, String password, String name, String email, @NonNull ICreateAccountView view) {
        Account newAccount = new Account(username,password,name,email);
        this.persistenceFacade.createAccountIfNotExists(newAccount, new IPersistenceFacade.BinaryResultListener() {
            @Override
            public void onYesResult() {
                view.onCreateSuccess();
                //load listings
                MainActivity.this.persistenceFacade.retrieveCollectionOfListings(new IPersistenceFacade.DataListener<CollectionOfListings>() {
                    @Override
                    public void onDataReceived(@NonNull CollectionOfListings listings) {
                        //display all listings
                        MainActivity.allListings = listings;
                        Fragment curFrag = MainActivity.this.mainView.getCurFragment();
                        if (curFrag instanceof IDashboardView) // update dash display if dash fragment being displayed
                            ((IDashboardView)curFrag).updateDashboardDisplay(MainActivity.allListings);
                        dashboardFragment.adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onNoDataFound() {
                    }
                });
                MainActivity.this.mainView.displayFragment(dashboardFragment,true,"go to dashboard");
            }

            @Override
            public void onNoResult() {
                view.onAccountAlreadyExists();
            }
        });
        /* ICreateAccountView.Listener interface implementation end */
    }


}
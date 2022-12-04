package com.example.carpoolas.view;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import static com.example.carpoolas.controller.MainActivity.curState;

import android.view.View;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.carpoolas.R;
import com.example.carpoolas.controller.MainActivity;
import com.example.carpoolas.databinding.ActivityMainBinding;

/**
 * An implementation of the application's screen template.
 */
public class MainView implements IMainView{
    FragmentManager fmanager; // lets us perform fragment transactions
    ActivityMainBinding binding;     // gives us access to all the graphical components in main.xml
    boolean isShown = true;
//set visibility
    /**
     *
     * @param activity The android activity the screen is associated with.
     */
    public MainView(MainActivity activity){
        this.fmanager = activity.getSupportFragmentManager();
        this.binding = ActivityMainBinding.inflate(activity.getLayoutInflater());
        //set up onclick listener for buttons
        this.binding.searchListingsButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                LinearLayout layout = binding.mainLayout;
                layout.setVisibility(INVISIBLE);
                isShown = false;
                curState = "logIn";
                displayFragment(activity.getSearchFragListener(),true,"search listings");
            }
        });
        this.binding.createListingButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                LinearLayout layout = binding.mainLayout;
                layout.setVisibility(INVISIBLE);
                isShown = false;
                curState = "logIn";
                //CreateListingFragment createListingFragment =  new MainActivity().getAccountFragListener();
                displayFragment(activity.getListingFragListener(),true,"create a listing");
            }
        });
    }
//showControls method
    public void showControls(){
        LinearLayout layout = binding.mainLayout;
        layout.setVisibility(VISIBLE);
    }

    public void hideControls(){
        LinearLayout layout = binding.mainLayout;
        layout.setVisibility(INVISIBLE);
    }

    /**
     * Retrieve the graphical widget (android view) at the root of the screen hierarchy/
     * @return the screen's root android view (widget)
     */
    @Override
    public View getRootView() {
        return this.binding.getRoot();
    }

    /**
     * Replaces the contents of the screen's fragment container with the one passed in as an argument.
     *
     * @param fragment The fragment to be displayed.
     * @param allowBack true if it should be possible to go back to this particular fragment, flase otherwise.
     * @param name the name of the fragment to be displayed (can be used to backtrack to a particular fragment by name).
     */
    @Override
    public void displayFragment(Fragment fragment, boolean allowBack, String name) {

        FragmentTransaction ft = this.fmanager.beginTransaction();

        ft.replace(this.binding.fragmentContainerView.getId(), fragment);

        if (allowBack) ft.addToBackStack(name);

        ft.commit(); // executes the transaction
    }
    public Fragment getCurFragment(){
        return this.binding.fragmentContainerView.getFragment();
    }


}

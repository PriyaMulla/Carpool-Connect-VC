package com.example.carpoolas.view;

import static com.example.carpoolas.controller.MainActivity.IS_SHOWN;
import static com.example.carpoolas.controller.MainActivity.curState;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.example.carpoolas.R;
import com.example.carpoolas.controller.MainActivity;
import com.example.carpoolas.databinding.FragmentDashboardBinding;
import com.example.carpoolas.model.CollectionOfListings;
import com.example.carpoolas.model.Listing;
import com.google.android.material.snackbar.Snackbar;
import java.util.ArrayList;

/**
 * Implements IDashboardView interface using an Android fragment.
 */
public class DashboardFragment extends Fragment implements IDashboardView {

    FragmentDashboardBinding binding;
    Listener listener;
    static String curRole;
    static String curEnd;
    public static Listing curListing;
    String brief;
    ArrayList<Listing> listy;
    ArrayList<String> dash;
    public ArrayAdapter<String> adapter;


    /**
     * Constructor method.
     * @param listener observer to be notified of events of interest
     */
    public DashboardFragment(Listener listener) {
        this.listener = listener;
    }

    /**
     * OnCreateView() overrides method of the same name from superclass. It's purpose is to
     * inflate the xml layout associated with the fragment.
     * @param inflater object to use to inflate the xml layout (create actual graphical widgets out of the xml declarations)
     * @param container where the graphical widgets will be placed
     * @param savedInstanceState any saved state information to be restored (null if none exists)
     * @return the root of the layout that has just been inflated
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.binding = FragmentDashboardBinding.inflate(inflater);
        return this.binding.getRoot();

    }

    /**
     * OnViewCreated() overrides method of the same name from superclass. It is called by the
     * android platform after the layout has been inflated, and before the view transitions to the
     * created state.
     *
     * @param view the layout's root view
     * @param savedInstanceState any saved state information to be restored (null if none exists)
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dash = new ArrayList<>();
        listy = new ArrayList<>();

        //changes dashboard depending on action that just occurred
        if (MainActivity.filteredListings.isEmpty()) {
            this.updateDashboardDisplay(MainActivity.allListings);
        } else {
            this.updateDashboardDisplay(MainActivity.filteredListings);
        }


        //go into detailed frag
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, dash);

        ListView listView = binding.listview;
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        listView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedPost = (String) listView.getItemAtPosition(position);
                Snackbar.make(view, "You selected : " + selectedPost, Snackbar.LENGTH_SHORT).show();

                if (!(MainActivity.allListings.isEmpty())) {
                    curListing = listy.get(position++);
                    LinearLayout layout = (LinearLayout) view.getRootView().findViewById(R.id.mainLayout);
                    layout.setVisibility(View.INVISIBLE);
                    DashboardFragment.this.listener.goToDetailedPost(DashboardFragment.this);
                }
            }
        });
        this.binding.showAllTrips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).goToDashboard();
            }
        });
    }


    /**
     * Overridden to save dynamic state before fragment destruction.
     * @param outState the bundle to write state to.
     */
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(IS_SHOWN, curState);
    }

    /**
     * Overridden to load dynamic state upon fragment recreation.
     * @param savedInstanceState the bundle to read state from.
     */
    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        ((MainActivity)getActivity()).areControlsShown(curState);
    }

    /**
     * Method to be called whenever the dash display needs to be updated.
     * @param listings the dash to be displayed
     */
    @Override
    public void updateDashboardDisplay(CollectionOfListings listings) {

        //Import listings into an arrayList
        for (Listing listing : listings.listings) {
            listy.add(listing);
            curRole = listing.getRole();
            curEnd = listing.getEndLocation();
            if (curRole.equals("Driver")) {
                brief = "Listing: \nA " + curRole + " is going to " + curEnd + "";
            }
            if (curRole.equals("Passenger")) {
                brief = "Listing: \nA " + curRole + " wants to go to " + curEnd + "";
            }
            dash.add(brief);
        }

    }
}
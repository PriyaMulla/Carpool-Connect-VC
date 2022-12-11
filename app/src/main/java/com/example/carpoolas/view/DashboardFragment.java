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
import java.util.Iterator;


public class DashboardFragment extends Fragment implements IDashboardView {

    FragmentDashboardBinding binding;
    Listener listener;
    static String curRole;
    static String curEnd;
    public static Listing curListing;
    String brief;
    ArrayList<Listing> listy = new ArrayList<>();
    ArrayList<String> dash = new ArrayList<>();
    public ArrayAdapter<String> adapter;


    public DashboardFragment(Listener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.binding = FragmentDashboardBinding.inflate(inflater);
        return this.binding.getRoot();

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        this.updateDashboardDisplay(MainActivity.listings);

        /*if(this.listener.getListings().isEmpty()){
            dash.add("No listings available :'(");
        }*/

        //go into detailed frag
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,dash);

        ListView listView = binding.listview;
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                String selectedPost = (String) listView.getItemAtPosition(position);
                Snackbar.make(view,"You selected : " + selectedPost,Snackbar.LENGTH_SHORT).show();

                if (!(((MainActivity)getActivity()).getListings().isEmpty())) {
                    curListing = listy.get(position++);
                    LinearLayout layout = (LinearLayout) view.getRootView().findViewById(R.id.mainLayout);
                    layout.setVisibility(View.INVISIBLE);
                    DashboardFragment.this.listener.goToDetailedPost(DashboardFragment.this);
                }
            }
        });

    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(IS_SHOWN, curState);
    }

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
        dash = new ArrayList<>();

        //Import listings into an arrayList
        for (Listing listing : MainActivity.listings.listings) {
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
package com.example.carpoolas.view;

import static com.example.carpoolas.view.DashboardFragment.curListing;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.carpoolas.R;
import com.example.carpoolas.controller.MainActivity;
import com.example.carpoolas.databinding.ActivityMainBinding;
import com.example.carpoolas.databinding.FragmentDetailedListingBinding;
import com.example.carpoolas.model.Listing;
import com.google.android.material.snackbar.Snackbar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class DetailedListingFragment extends Fragment implements IDetailedListingView {

    public static FragmentDetailedListingBinding binding;
    ActivityMainBinding mainBinding;
    Listener listener;
    Listing currListing;
    String curRRole;
    String endLocation;
    String strDateTimeCreated;
    String strDateTime;
    String startLoc;
    String seatNum;
//TODO Create a method in MainActivity that gets the current post for here
    public DetailedListingFragment(Listener listener) {
        this.listener = listener;
        currListing = curListing;
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        strDateTimeCreated = formatter.format(currListing.getDateCreated());
        strDateTime = formatter.format(currListing.getDateTimeOfTrip());
        //this.currListing = currListing;
        curRRole = currListing.getRole();
        endLocation = currListing.getEndLocation();
        startLoc = currListing.getStartLocation();
        seatNum = String.valueOf(currListing.getSeats());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.binding = FragmentDetailedListingBinding.inflate(inflater);
        return this.binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        strDateTimeCreated = formatter.format(currListing.getDateCreated());
        strDateTime = formatter.format(currListing.getDateTimeOfTrip());
        //TODO Onclick close -goes back?
        //TODO Onclick either accept or message
        TextView roleText = binding.rolePlaceholder;
        TextView dateTimeCreated = binding.dateTimePlaceholder;
        TextView destination = binding.destPlaceholder;
        TextView startLocation = binding.startPlaceholder;
        TextView dateTime4Trip = binding.dateTime4Trip;
        TextView seatPhrase = binding.seatPhrase;
        TextView seats = binding.seatsPlaceholder;
        TextView acc = binding.postedBy;
        //TODO fix this implementation
        if (currListing.getCurAccount() != null) acc.setText(currListing.getCurAccount().getName());
        dateTimeCreated.setText(strDateTimeCreated);
        destination.setText(endLocation);
        roleText.setText(curRRole + " is offering");
        startLocation.setText(startLoc);
        dateTime4Trip.setText(strDateTime);
        if (curRRole.equals("Driver")) {
            seatPhrase.setText("Seats Available:");
        } else{
            seatPhrase.setText("Seats needed");
        }
        seats.setText(seatNum);

        this.binding.contact.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Nice try",Snackbar.LENGTH_SHORT).show();
                ConstraintLayout contactInfo = binding.contactInfo;
                contactInfo.setVisibility(View.VISIBLE);
                ConstraintLayout layout = view.getRootView().findViewById(R.id.mainView);
                layout.setBackgroundColor(Color.parseColor("#FF9E9E9E"));
                Button contact = binding.contact;
                contact.setEnabled(false);
                //((MainActivity)getActivity()).changeColor();
            }
        });
        this.binding.closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConstraintLayout contactInfo = binding.contactInfo;
                contactInfo.setVisibility(View.INVISIBLE);
                ConstraintLayout layout = view.getRootView().findViewById(R.id.mainView);
                layout.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                Button contact = binding.contact;
                contact.setEnabled(true);
            }
        });
    }

//    @Override
//    public void onSaveInstanceState(@NonNull Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putString("Role", curRRole);
//        outState.putString("Start Location", startLoc);
//        outState.putString("End Location", endLocation);
//        outState.putString("Seats", seatNum);
//        outState.putString("Time Created", strDateTimeCreated);
//        outState.putString("Date Time", strDateTime);
//    }
//
//    @Override
//    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
//        super.onViewStateRestored(savedInstanceState);
//    }
}
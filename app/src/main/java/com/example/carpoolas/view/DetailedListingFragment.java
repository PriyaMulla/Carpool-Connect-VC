package com.example.carpoolas.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.carpoolas.databinding.FragmentDetailedListingBinding;
import com.example.carpoolas.model.Listing;
import com.example.carpoolas.view.IDetailedListingFragment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class DetailedListingFragment extends Fragment implements IDetailedListingFragment {

    FragmentDetailedListingBinding binding;
    Listener listener;
    FragmentManager fmanager;
    String curRRole = "";
    Listing currListing;

    public DetailedListingFragment(Listing currListing) {this.currListing = currListing;

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
        String strDateTimeCreated = formatter.format(currListing.getDateCreated());
        String strDateTime = formatter.format(currListing.getDateTimeOfTrip());
        //TODO Onclick close -goes back?
        //TODO Onclick either accept or message
        TextView roleText = binding.rolePlaceholder;
        TextView dateTimeCreated = binding.dateTimePlaceholder;
        TextView destination = binding.destPlaceholder;
        TextView startLocation = binding.startPlaceholder;
        TextView dateTime4Trip = binding.dateTime4Trip;
        TextView seatPhrase = binding.seatPhrase;
        TextView seats = binding.seatsPlaceholder;

        dateTimeCreated.setText(strDateTimeCreated);
        destination.setText(currListing.getEndLocation());
        roleText.setText(currListing.getRole() + " is offering");
        startLocation.setText(currListing.getStartLocation());
        dateTime4Trip.setText(strDateTime);
        if (currListing.getRole().equals("Driver")) {
            seatPhrase.setText("Seats Available:");
        } else{
            seatPhrase.setText("Seats needed");
        }
        seats.setText(String.valueOf(currListing.getSeats()));


    }
}
package com.example.carpoolas.view;

import static com.example.carpoolas.model.Listing.isValidEnd;
import static com.example.carpoolas.model.Listing.isValidStart;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.example.carpoolas.R;
import com.example.carpoolas.controller.MainActivity;
import com.example.carpoolas.databinding.FragmentFilterBinding;
import com.example.carpoolas.model.CollectionOfListings;
import com.example.carpoolas.model.DateFilter;
import com.example.carpoolas.model.EndFilter;
import com.example.carpoolas.model.IFilter;
import com.example.carpoolas.model.RoleFilter;
import com.example.carpoolas.model.StartFilter;
import com.google.android.material.snackbar.Snackbar;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class FilterFragment extends Fragment implements IFilterView{

    private FragmentFilterBinding binding;
    private Listener listener;


    public FilterFragment(Listener listener) {
        this.listener = listener;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentFilterBinding.inflate(inflater);
        return this.binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        @SuppressLint("SimpleDateFormat") DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        Set<IFilter> filterSet = new HashSet<>();

        //listener for filter button clicks
        this.binding.filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CollectionOfListings filteredPage = ((MainActivity)getActivity()).getListings(); //all listings at first
                //if no listings
                if (filteredPage.isEmpty()){
                    Snackbar.make(view, "No listings to filter!", Snackbar.LENGTH_SHORT).show();
                }

                boolean isValid = true;
                //extract trip date and time
                Editable enterDate = FilterFragment.this.binding.enterDate.getText();
                Editable enterTime = FilterFragment.this.binding.enterTime.getText();
                String dateString = enterDate.toString();
                String timeString = enterTime.toString();
                String dateTimeString = dateString + " " + timeString;
                Date date = null;

                if (dateString.isEmpty() && timeString.isEmpty());

                else {
                    try {
                        date = formatter.parse(dateTimeString);
                    } catch (ParseException e) {
                        Snackbar.make(view, "Please enter Date and Time!", Snackbar.LENGTH_SHORT).show();
                        isValid = false;
                    }
                    DateFilter dateFilter = new DateFilter();
                    dateFilter.dDate = date;
                    filterSet.add(dateFilter);
                }

                //extract start location
                Editable enterStart = FilterFragment.this.binding.enterStartLocation.getText();
                String start = enterStart.toString();
                if(start.isEmpty());

                else if (!isValidStart(start)) {
                    Snackbar.make(view, "Please enter Start Location!", Snackbar.LENGTH_SHORT).show();
                    isValid = false;
                }
                else{
                    StartFilter startFilter = new StartFilter();
                    startFilter.dStart = start;
                    filterSet.add(startFilter);
                }

                //extract end location
                Editable enterEnd = FilterFragment.this.binding.enterEndLocation.getText();
                String end = enterEnd.toString();
                if(end.isEmpty());
                else if (!isValidEnd(end)) {
                    Snackbar.make(view, "Please enter End Location!", Snackbar.LENGTH_SHORT).show();
                    isValid = false;
                }
                else{
                    EndFilter endFilter = new EndFilter();
                    endFilter.dEnd = end;
                    filterSet.add(endFilter);
                }

                //extract seats
                Editable enterSeats = FilterFragment.this.binding.enterSeats.getText();
                String stringSeats = enterSeats.toString();
                int seats = 0;
                if(stringSeats.isEmpty());

                else {
                    try{
                        seats = Integer.parseInt(enterSeats.toString());
                    } catch (NumberFormatException e) {
                        Snackbar.make(view, "Please enter number of seats!", Snackbar.LENGTH_SHORT).show();
                        isValid = false;
                    }
                    seats = Integer.parseInt(enterSeats.toString());
                }

                //TODO:record filters, create filter, set of them, send to main activity
                 if (isValid) {
                     Snackbar.make(view, "Filtered Listings!", Snackbar.LENGTH_SHORT).show();
                     LinearLayout layout = (LinearLayout) view.getRootView().findViewById(R.id.mainLayout);
                     layout.setVisibility(View.VISIBLE);
                     //TODO: dateCreated = formatter.format(dateCreated);
                     RadioButton driverButton = binding.driverRadioButton;
                     RadioButton PassengerButton = binding.passengerRadioButton;

                     if (driverButton.isChecked()) {
                         RoleFilter roleFilter = new RoleFilter();
                         roleFilter.dRole = "Driver";
                         filterSet.add(roleFilter);
                     }
                     if (PassengerButton.isChecked()) {
                         RoleFilter roleFilter = new RoleFilter();
                         roleFilter.dRole = "Passenger";
                         filterSet.add(roleFilter);
                     }

                     FilterFragment.this.listener.onFilter(filteredPage, filterSet, FilterFragment.this);

                     enterDate.clear();
                     enterSeats.clear();
                     enterStart.clear();
                     enterEnd.clear();
                     enterTime.clear();

                 }

                }

            }
        );

        }


    }

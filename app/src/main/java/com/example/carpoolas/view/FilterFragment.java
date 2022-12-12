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
import com.example.carpoolas.model.SeatFilter;
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
    public static boolean isEmpty = true;


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
        @SuppressLint("SimpleDateFormat") DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Set<IFilter> filterSet = new HashSet<>();

        //listener for filter button clicks
        this.binding.filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.allListings.isEmpty()){
                    Snackbar.make(view, "No listings to filter!", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                CollectionOfListings filteredPage = ((MainActivity)getActivity()).getListings(); //all listings at first
                boolean isValid = true;

                //role
                RadioButton driverButton = binding.driverRadioButton;
                RadioButton PassengerButton = binding.passengerRadioButton;

                if (driverButton.isChecked()) {
                    RoleFilter roleFilter = new RoleFilter();
                    roleFilter.dRole = "Driver";
                    filterSet.add(roleFilter);
                    isEmpty = false;
                }
                if (PassengerButton.isChecked()) {
                    RoleFilter roleFilter = new RoleFilter();
                    roleFilter.dRole = "Passenger";
                    filterSet.add(roleFilter);
                    isEmpty = false;
                }

                //extract seats
                Editable enterSeats = FilterFragment.this.binding.enterSeats.getText();
                String stringSeats = enterSeats.toString();
                int seats = 0;
                if(!(stringSeats.isEmpty()))

                 {
                     isEmpty = false;
                    try{
                        seats = Integer.parseInt(enterSeats.toString());
                    } catch (NumberFormatException e) {
                        Snackbar.make(view, "Please enter number of seats!", Snackbar.LENGTH_SHORT).show();
                        isValid = false;
                    }
                    SeatFilter seatFilter = new SeatFilter();
                    seatFilter.dSeats = seats;
                    filterSet.add(seatFilter);
                }


                //extract trip date
                Editable enterDate = FilterFragment.this.binding.enterDate.getText();
                String dateString = enterDate.toString();
                Date date = null;

                if (!(dateString.isEmpty()))

                 {
                     isEmpty = false;
                    try {
                        date = formatter.parse(dateString);
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
                if(!(start.isEmpty())) {
                    isEmpty = false;
                    if (!isValidStart(start)) {
                        Snackbar.make(view, "Please enter Start Location!", Snackbar.LENGTH_SHORT).show();
                        isValid = false;
                    } else {
                        StartFilter startFilter = new StartFilter();
                        startFilter.dStart = start;
                        filterSet.add(startFilter);
                    }
                }

                //extract end location
                Editable enterEnd = FilterFragment.this.binding.enterEndLocation.getText();
                String end = enterEnd.toString();
                if(!(end.isEmpty())) {
                    isEmpty = false;
                    if (!isValidEnd(end)) {
                        Snackbar.make(view, "Please enter End Location!", Snackbar.LENGTH_SHORT).show();
                        isValid = false;
                    } else {
                        EndFilter endFilter = new EndFilter();
                        endFilter.dEnd = end;
                        filterSet.add(endFilter);
                    }
                }


                 if (isValid) {
                     Snackbar.make(view, "Filtered Listings!", Snackbar.LENGTH_SHORT).show();
                     LinearLayout layout = (LinearLayout) view.getRootView().findViewById(R.id.mainLayout);
                     layout.setVisibility(View.VISIBLE);

                     FilterFragment.this.listener.onFilter(filteredPage, filterSet, FilterFragment.this);
                 }
                }
            }
        );
        }
    }

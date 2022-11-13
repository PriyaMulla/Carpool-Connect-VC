package com.example.carpoolas.view;

import static com.example.carpoolas.controller.MainActivity.filterDates;
import static com.example.carpoolas.controller.MainActivity.filterDriverRole;
import static com.example.carpoolas.controller.MainActivity.filterEnd;
import static com.example.carpoolas.controller.MainActivity.filterPassengerRole;
import static com.example.carpoolas.controller.MainActivity.filterStart;
import static com.example.carpoolas.controller.MainActivity.isValidDateTime;
import static com.example.carpoolas.controller.MainActivity.isValidEnd;
import static com.example.carpoolas.controller.MainActivity.isValidSeats;
import static com.example.carpoolas.controller.MainActivity.isValidStart;

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
import com.example.carpoolas.model.DateFilter;
import com.example.carpoolas.model.EndFilter;
import com.example.carpoolas.model.PageOfListings;
import com.example.carpoolas.model.RoleFilter;
import com.example.carpoolas.model.StartFilter;
import com.google.android.material.snackbar.Snackbar;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class FilterFragment extends Fragment implements IFilterView{

    private FragmentFilterBinding binding;
    private Listener listener;
    PageOfListings filteredPage = FilterFragment.this.listener.getListings(); //all listings at first
    public FilterFragment( ) {
    }

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


        //listener for filter button clicks
        this.binding.filterButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SimpleDateFormat")
            @Override
            public void onClick(View view) {

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
                if (dateString.isEmpty() && timeString.isEmpty()){
                    isValid = true;
                }
                else if (!isValidDateTime(dateTimeString)) {
                    Snackbar.make(view, "Please enter Date and Time!", Snackbar.LENGTH_SHORT).show();
                    isValid = isValidDateTime(dateTimeString);

                }
                else {
                    try {
                        date = formatter.parse(dateTimeString);
                    } catch (ParseException e) {
                        Snackbar.make(view, "Please enter Date and Time!", Snackbar.LENGTH_SHORT).show();
                        isValid = isValidDateTime(dateTimeString);
                    }
                    filterDates(date, filteredPage);
                }
                //extract start location
                Editable enterStart = FilterFragment.this.binding.enterStartLocation.getText();
                String start = enterStart.toString();
                if(start.isEmpty()){
                    isValid = isValid;
                }
                else if (!isValidStart(start)) {
                    Snackbar.make(view, "Please enter Start Location!", Snackbar.LENGTH_SHORT).show();
                    isValid = isValid && isValidStart(start);
                }
                else{
                    filterStart(start, filteredPage);
                }

                //extract end location
                Editable enterEnd = FilterFragment.this.binding.enterEndLocation.getText();
                String end = enterEnd.toString();
                if(end.isEmpty()){
                    isValid = isValid;
                }
                else if (!isValidEnd(end)) {
                    Snackbar.make(view, "Please enter End Location!", Snackbar.LENGTH_SHORT).show();
                    isValid = isValid && isValidEnd(end);
                }
                else{
                    filterEnd(end, filteredPage);
                }

                //extract seats
                Editable enterSeats = FilterFragment.this.binding.enterSeats.getText();
                String stringSeats = enterSeats.toString();
                int seats = 0;
                if(stringSeats.isEmpty()){
                    isValid = isValid;
                }
                else if (!isValidSeats(stringSeats)) {
                    Snackbar.make(view, "Please enter number of seats!", Snackbar.LENGTH_SHORT).show();
                    isValid = isValid && isValidSeats(stringSeats);
                } else seats = Integer.parseInt(enterSeats.toString());



                 if (isValid) {
                     Snackbar.make(view, "Listing added!", Snackbar.LENGTH_SHORT).show();
                     LinearLayout layout = (LinearLayout) view.getRootView().findViewById(R.id.mainLayout);
                     layout.setVisibility(View.VISIBLE);
                     //TODO: dateCreated = formatter.format(dateCreated);
                     RadioButton driverButton = (RadioButton) view.getRootView().findViewById(R.id.driverRadioButton);
                     RadioButton PassengerButton = (RadioButton) view.getRootView().findViewById(R.id.passengerRadioButton);

                     if (driverButton.isChecked()) {

                         filterDriverRole(filteredPage);

                         FilterFragment.this.listener.onFilter(filteredPage, FilterFragment.this);
                     }
                     if (PassengerButton.isChecked()) {
                         filterPassengerRole(filteredPage);

                         FilterFragment.this.listener.onFilter(filteredPage, FilterFragment.this);
                     }
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

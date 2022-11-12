package com.example.carpoolas.view;

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
import com.example.carpoolas.databinding.FragmentFilterBinding;
import com.example.carpoolas.model.DateFilter;
import com.example.carpoolas.model.PageOfListings;
import com.google.android.material.snackbar.Snackbar;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class FilterFragment extends Fragment implements IFilterView{

    private FragmentFilterBinding binding;
    private Listener listener;

    public FilterFragment( ) {
    }

    public FilterFragment(Listener listener) {
        this.listener = listener;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentFilterBinding.inflate(inflater);
        return this.binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        PageOfListings filteredPage = new PageOfListings(); //starts out as all listings

        //listener for filter button clicks
        this.binding.filterButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SimpleDateFormat")
            @Override
            public void onClick(View view) {
                boolean isValid = true;
                //extract trip date and time
                Editable enterDate = FilterFragment.this.binding.enterDate.getText();
                Editable enterTime = FilterFragment.this.binding.enterTime.getText();
                String dateTimeString = enterDate.toString() + " " + enterTime.toString();
                Date date = null;
                if (!isValidDateTime(dateTimeString)) {
                    Snackbar.make(view, "Please enter Date and Time!", Snackbar.LENGTH_SHORT).show();
                    isValid = isValidDateTime(dateTimeString);

                } else {
                    try {
                        date = formatter.parse(dateTimeString);
                    } catch (ParseException e) {
                        Snackbar.make(view, "Please enter Date and Time!", Snackbar.LENGTH_SHORT).show();
                        isValid = isValidDateTime(dateTimeString);
                    }
                    DateFilter dateFilter = new DateFilter();
                    dateFilter.dDate = date;
                    dateFilter.newPage = filteredPage;
                    dateFilter.filterListings( ); //TODO: page of all listings??
                    filteredPage = dateFiler.newPage;
                }
                //extract start location
                Editable enterStart = FilterFragment.this.binding.enterStartLocation.getText();
                String start = enterStart.toString();
                if (!isValidStart(start)) {
                    Snackbar.make(view, "Please enter Start Location!", Snackbar.LENGTH_SHORT).show();
                    isValid = isValid && isValidStart(start);
                }

                //extract end location
                Editable enterEnd = FilterFragment.this.binding.enterEndLocation.getText();
                String end = enterEnd.toString();
                if (!isValidEnd(end)) {
                    Snackbar.make(view, "Please enter End Location!", Snackbar.LENGTH_SHORT).show();
                    isValid = isValid && isValidEnd(end);
                }

                //extract seats
                Editable enterSeats = FilterFragment.this.binding.enterSeats.getText();
                String stringSeats = enterSeats.toString();
                int seats = 0;
                if (!isValidSeats(stringSeats)) {
                    Snackbar.make(view, "Please enter number of seats!", Snackbar.LENGTH_SHORT).show();
                    isValid = isValid && isValidSeats(stringSeats);
                } else seats = Integer.parseInt(enterSeats.toString());
                 if (isValid) {
                     Snackbar.make(view, "Listing added!", Snackbar.LENGTH_SHORT).show();
                     //boolean checked = ((RadioButton) view).isChecked();
                     Date dateCreated = new Date();
                     LinearLayout layout = (LinearLayout) view.getRootView().findViewById(R.id.mainLayout);
                     layout.setVisibility(View.VISIBLE);
                     //TODO: dateCreated = formatter.format(dateCreated);
                     RadioButton driverButton = (RadioButton) view.getRootView().findViewById(R.id.driverRadioButton);
                     RadioButton PassengerButton = (RadioButton) view.getRootView().findViewById(R.id.driverRadioButton);
                     //switch(view.getId()) {
                     //case R.id.driverRadioButton:
                     if (driverButton.isChecked()) {
                         FilterFragment.this.listener.onFilter(filteredPage, FilterFragment.this);
                     }
                     //    break;
                     //case R.id.passengerRadioButton:
                     if (PassengerButton.isChecked()) {
                         FilterFragment.this.listener.onFilter(filteredPage, FilterFragment.this);
                     }
                     //   break;
                     //}
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
    

    @Override
    public void updateDisplay(PageOfListings newPage) {
        this.binding.filterLabel.setText(newPage.toString());
    }
}
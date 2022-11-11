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
import android.widget.RadioButton;

import com.example.carpoolas.R;
import com.example.carpoolas.databinding.FragmentCreateListingBinding;
import com.example.carpoolas.model.DateFilter;
import com.example.carpoolas.model.Listing;
import com.google.android.material.snackbar.Snackbar;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDate;

public class CreateListingFragment extends Fragment implements ICreateListingView {

    FragmentCreateListingBinding binding;
    Listener listener;

    public CreateListingFragment(Listener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.binding = FragmentCreateListingBinding.inflate(inflater);
        return this.binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm");

        //listener for create button clicks
        this.binding.addButton.setOnClickListener(new View.OnClickListener(){
            @SuppressLint("SimpleDateFormat")
            @Override
            public void onClick(View view){
                boolean isValid = true;
                //extract trip date and time
                Editable enterDate = CreateListingFragment.this.binding.enterDate.getText();
                Editable enterTime = CreateListingFragment.this.binding.enterTime.getText();
                String dateTimeString = enterDate.toString() + " " + enterTime.toString();
                Date date = null;

                if (!isValidDateTime(dateTimeString)){
                    Snackbar.make(view, "Please enter Date and Time!", Snackbar.LENGTH_INDEFINITE).show();
                    isValid = isValidDateTime(dateTimeString);

                }
                else {
                    try {
                        date = formatter.parse(dateTimeString);
                    } catch (ParseException e) {
                        Snackbar.make(view, "Please enter Date and Time!", Snackbar.LENGTH_INDEFINITE).show();
                        isValid = isValidDateTime(dateTimeString);
                    }
                }

                //extract start location
                Editable enterStart = CreateListingFragment.this.binding.enterStartLocation.getText();
                String start = enterStart.toString();
                if (!isValidStart(start)){
                    Snackbar.make(view, "Please enter Start Location!", Snackbar.LENGTH_INDEFINITE).show();
                    isValid = isValid && isValidStart(start);
                }

                //extract end location
                Editable enterEnd = CreateListingFragment.this.binding.enterEndLocation.getText();
                String end = enterEnd.toString();
                if (!isValidEnd(end)){
                    Snackbar.make(view, "Please enter End Location!", Snackbar.LENGTH_INDEFINITE).show();
                    isValid = isValid && isValidEnd(end);
                }

                //extract seats
                Editable enterSeats = CreateListingFragment.this.binding.enterSeats.getText();
                String stringSeats = null;
                int seats = 0;
                if (!isValidSeats(stringSeats)){
                    Snackbar.make(view, "Please enter number of seats!", Snackbar.LENGTH_INDEFINITE).show();
                    isValid = isValid && isValidSeats(stringSeats);
                }
                else seats = Integer.parseInt(enterSeats.toString());



                if(isValid){
                    Snackbar.make(view, "Listing added!", Snackbar.LENGTH_INDEFINITE).show();
                    boolean checked = ((RadioButton) view).isChecked();
                    Date dateCreated = new Date();
                    //TODO: dateCreated = formatter.format(dateCreated);
                    switch(view.getId()) {
                        case R.id.driverRadioButton:
                            if (checked)
                                CreateListingFragment.this.listener.onCreateListing(dateCreated, "Driver", date, start, end, seats, CreateListingFragment.this);
                            break;
                        case R.id.passengerRadioButton:
                            if (checked)
                                CreateListingFragment.this.listener.onCreateListing(dateCreated, "Passenger", date, start, end, seats, CreateListingFragment.this);
                            break;
                    }

                }

            }

        }
        );

    }


}
package com.example.carpoolas.view;

import static com.example.carpoolas.view.DashboardFragment.curListing;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.carpoolas.R;
import com.example.carpoolas.databinding.FragmentDetailedListingBinding;
import com.example.carpoolas.model.Listing;
import com.google.android.material.snackbar.Snackbar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Implements IDetailedListingView interface using an Android fragment.
 */
public class DetailedListingFragment extends Fragment implements IDetailedListingView {

    @SuppressLint("StaticFieldLeak")
    public static FragmentDetailedListingBinding binding;
    Listener listener;
    Listing currListing;
    String curRRole;
    String endLocation;
    String strDateTimeCreated;
    String strDateTime;
    String startLoc;
    String seatNum;

    /**
     * Constructor method.
     * @param listener observer to be notified of events of interest
     */
    public DetailedListingFragment(Listener listener) {
        this.listener = listener;
        currListing = curListing;
        @SuppressLint("SimpleDateFormat") DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        strDateTimeCreated = formatter.format(currListing.getDateCreated());
        strDateTime = formatter.format(currListing.getDateTimeOfTrip());
        curRRole = currListing.getRole();
        endLocation = currListing.getEndLocation();
        startLoc = currListing.getStartLocation();
        seatNum = String.valueOf(currListing.getSeats());
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
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDetailedListingBinding.inflate(inflater);
        return binding.getRoot();
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

        @SuppressLint("SimpleDateFormat") DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        strDateTimeCreated = formatter.format(currListing.getDateCreated());
        strDateTime = formatter.format(currListing.getDateTimeOfTrip());
        TextView roleText = binding.rolePlaceholder;
        TextView dateTimeCreated = binding.dateTimePlaceholder;
        TextView destination = binding.destPlaceholder;
        TextView startLocation = binding.startPlaceholder;
        TextView dateTime4Trip = binding.dateTime4Trip;
        TextView seatPhrase = binding.seatPhrase;
        TextView seats = binding.seatsPlaceholder;
        TextView acc = binding.postedBy;
        acc.setText(currListing.getCurAccount().getName());
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

        binding.contact.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Nice try",Snackbar.LENGTH_SHORT).show();
                ConstraintLayout contactInfo = binding.contactInfo;
                contactInfo.setVisibility(View.VISIBLE);
                ConstraintLayout layout = view.getRootView().findViewById(R.id.mainView);
                layout.setBackgroundColor(Color.parseColor("#FF9E9E9E"));
                Button contact = binding.contact;
                contact.setEnabled(false);
                TextView email = binding.emailHolder;
                email.setText(currListing.getCurAccount().getEmail());
            }
        });
        binding.closeButton.setOnClickListener(new View.OnClickListener() {
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
}
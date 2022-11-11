package com.example.carpoolas.view;

import android.view.View;

import androidx.annotation.NonNull;

import java.util.Date;
import com.example.carpoolas.model.Listing;

public interface ICreateListingView {
    /**
     * Interface that classes interested in being notified of events happening
     * to the view should implement.
     */
    interface Listener{
        /**
         * called when an account is created
         */
        void onCreateListing(@NonNull Date created, String role, Date dateTime, String start, String end, int seats, @NonNull ICreateListingView view);
    }


    /**
     * tells view to update display and show account created
     */
    void updatePageOfListings(Listing lst);

}

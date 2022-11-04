package com.example.carpoolas.view;

import android.view.View;

import androidx.annotation.NonNull;

import com.example.carpoolas.model.Account;
import com.example.carpoolas.model.PassengerListing;

public interface ICreatePassengerLView {
    /**
     * Interface that classes interested in being notified of events happening
     * to the view should implement.
     */
    interface Listener{
        /**
         * called when an account is created
         */
        void onCreatePassengerL(@NonNull int date, int time, String start, String end, int listingID, int seats, @NonNull ICreatePassengerLView view);
    }

    /**
     * Retrieve the graphical widget (android view) at the root of the screen hierarchy/
     * @return the screen's root android view (widget)
     */
    View getRootView();

    /**
     * tells view to update display and show account created
     */
    void updatePageOfListing(PassengerListing lst);

}


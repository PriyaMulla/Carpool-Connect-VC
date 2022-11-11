package com.example.carpoolas.view;

import androidx.annotation.NonNull;

import com.example.carpoolas.model.Account;
import com.example.carpoolas.model.PageOfListings;

public interface IFilterView {

    /**
     * Interface that classes interested in being notified of events happening
     * to the view should implement.
     */
    interface Listener{
        /**
         * called when an account is created
         */
        void onFilter(@NonNull PageOfListings lst, @NonNull IFilterView view);
    }


    /**
     * tells view to update display and show filtered page
     */
    void updateDisplay(PageOfListings newPage);

}

package com.example.carpoolas.view;

import com.example.carpoolas.model.CollectionOfListings;
import com.example.carpoolas.model.Listing;

public interface IDashboardView {


    void updateDashboardDisplay(CollectionOfListings dash);

    /**
     * Interface that classes interested in being notified of events happening
     * to the view should implement.
     */
    interface Listener{
        void goToDetailedPost(IDashboardView view);

        CollectionOfListings getListings();
        /**
         * called when an account is created
         */
        //void onCreateDashboard(@NonNull String username, String password, String name, String email, @NonNull ICreateAccountView view);


}
}

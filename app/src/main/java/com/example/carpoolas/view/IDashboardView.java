package com.example.carpoolas.view;

import com.example.carpoolas.model.CollectionOfListings;

public interface IDashboardView {


    void updateDashboardDisplay(CollectionOfListings dash);

    /**
     * Interface that classes interested in being notified of events happening
     * to the view should implement.
     */
    interface Listener{
        void goToDetailedPost(IDashboardView view);

        CollectionOfListings getListings();


}
}

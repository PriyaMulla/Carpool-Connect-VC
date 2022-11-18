package com.example.carpoolas.view;

import androidx.annotation.NonNull;

import com.example.carpoolas.model.Account;
import com.example.carpoolas.model.IFilter;
import com.example.carpoolas.model.PageOfListings;

import java.util.Set;

public interface IFilterView {

    /**
     * Interface that classes interested in being notified of events happening
     * to the view should implement.
     */
    interface Listener{
        /**
         * called when an account is created
         */
        void onFilter(@NonNull PageOfListings lst, Set<IFilter> filterSet, @NonNull IFilterView view);



    }


}

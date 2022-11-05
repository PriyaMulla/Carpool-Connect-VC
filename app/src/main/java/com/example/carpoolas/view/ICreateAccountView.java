package com.example.carpoolas.view;

import android.view.View;

import androidx.annotation.NonNull;

import com.example.carpoolas.model.Account;

public interface ICreateAccountView {

    /**
     * Interface that classes interested in being notified of events happening
     * to the view should implement.
     */
    interface Listener{
        /**
         * called when an account is created
         */
        void onCreateAccount(@NonNull String username, String password, String name, String email, @NonNull ICreateAccountView view);
    }


    /**
     * tells view to update display and show account created
     */
    void updateDisplay(Account acc);
}

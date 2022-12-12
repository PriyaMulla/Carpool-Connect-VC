package com.example.carpoolas.view;

import androidx.annotation.NonNull;

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
    void onCreateSuccess();
    void onAccountAlreadyExists();



}

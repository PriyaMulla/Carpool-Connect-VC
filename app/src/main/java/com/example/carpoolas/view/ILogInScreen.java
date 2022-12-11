package com.example.carpoolas.view;

import androidx.annotation.NonNull;

public interface ILogInScreen {
    void onInvalidCredentials();

    /**
     * Interface that classes interested in being notified of events happening
     * to the view should implement.
     */
    interface Listener{
        /**
         * called when an account needs to be created
         */
        void goToCreateAccount(@NonNull ILogInScreen view);
        void goToDashboard(@NonNull ILogInScreen view);

        void onSigninAttempt(String username, String password, ILogInScreen view);
        //void onCreateAccount(@NonNull String username, String password, String name, String email, @NonNull ICreateAccountView view);
    }
}

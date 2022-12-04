package com.example.carpoolas.view;

public interface IDashboardView {

    /**
     * Interface that classes interested in being notified of events happening
     * to the view should implement.
     */
    interface Listener{
        void goToDetailedPost(IDashboardView view, String s);
        /**
         * called when an account is created
         */
        //void onCreateDashboard(@NonNull String username, String password, String name, String email, @NonNull ICreateAccountView view);


}
}

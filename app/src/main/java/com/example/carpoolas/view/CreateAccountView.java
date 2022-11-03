package com.example.carpoolas.view;

import android.view.View;

public class CreateAccountView {
    /**
     * Interface that classes interested in being notified of events happening
     * to the view should implement.
     */
    interface Listener{
        void onCreateAccount();
    }

    /**
     * Retrieve the graphical widget (android view) at the root of the screen hierarchy/
     * @return the screen's root android view (widget)
     */
    View getRootView();

    //What should it update?
    //change this
    void updateSmtnDisplay();
}

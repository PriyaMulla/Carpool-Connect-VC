package com.example.carpoolas.view;

import android.view.View;

import androidx.fragment.app.Fragment;

/**
 * An interface for the application screen template.
 */
public interface IMainView {

    /**
     * Retrieve the graphical widget (android view) at the root of the screen hierarchy/
     * @return the screen's root android view (widget)
     */
    View getRootView();

    /**
     * Replaces the contents of the screen's fragment container with the one passed in as an argument.
     *
     * @param fragment The fragment to be displayed.
     * @param allowBack true if it should be possible to go back to this particular fragment, false otherwise.
     * @param name the name of the fragment to be displayed (can be used to backtrack to a particular fragment by name).
     */
    void displayFragment(Fragment fragment, boolean allowBack, String name);

    void showControls();

    void hideControls();

    Fragment getCurFragment();
}


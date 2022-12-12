package com.example.carpoolas.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.carpoolas.R;
import com.example.carpoolas.databinding.FragmentLogInScreenBinding;
import com.google.android.material.snackbar.Snackbar;

/**
 * Implements ILoginScreen interface using an Android fragment.
 */
public class LogInScreen extends Fragment implements ILogInScreen{
    private static final String IS_REGISTERED = "isRegistered";
    FragmentLogInScreenBinding binding;
    private boolean isRegistered = false;
    Listener listener;

    /**
     * Constructor method.
     * @param listener observer to be notified of events of interest
     */
    public LogInScreen(ILogInScreen.Listener listener) {
        this.listener = listener;
    }


    /**
     * OnCreateView() overrides method of the same name from superclass. It's purpose is to
     * inflate the xml layout associated with the fragment.
     * @param inflater object to use to inflate the xml layout (create actual graphical widgets out of the xml declarations)
     * @param container where the graphical widgets will be placed
     * @param savedInstanceState any saved state information to be restored (null if none exists)
     * @return the root of the layout that has just been inflated
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.binding = FragmentLogInScreenBinding.inflate(inflater);
        return this.binding.getRoot();
    }

    /**
     * OnViewCreated() overrides method of the same name from superclass. It is called by the
     * android platform after the layout has been inflated, and before the view transitions to the
     * created state.
     *
     * @param view the layout's root view
     * @param savedInstanceState any saved state information to be restored (null if none exists)
     */
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (savedInstanceState != null && savedInstanceState.getBoolean(IS_REGISTERED))
            activateRegisteredConfig();

        this.binding.logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Editable usernameText = LogInScreen.this.binding.usernameText.getText();
                String username = usernameText.toString();

                Editable passwordTeext = LogInScreen.this.binding.passwordText.getText();
                String password = passwordTeext.toString();
                LogInScreen.this.listener.onSigninAttempt(username, password, LogInScreen.this);

            }
        });

        this.binding.signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogInScreen.this.listener.goToCreateAccount(LogInScreen.this);
            }
        });
    }

    /**
     * Overridden to save dynamic state before fragment destruction.
     * @param outState the bundle to write state to.
     */
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(IS_REGISTERED, this.isRegistered);
    }

    /**
     * notifies invalid credentials
     */
    @Override
    public void onInvalidCredentials() {
        displayMessage(R.string.invalid_credentials_msg);
    }
    private void displayMessage(int msgRid){
        Snackbar.make(this.binding.getRoot(),
                        msgRid,
                        Snackbar.LENGTH_LONG)
                .show();
    }

    /**
     * prevent multiple reg attempts
     */
    private void activateRegisteredConfig(){
        this.isRegistered = true;
        this.binding.signUpButton.setEnabled(false);
    }



}
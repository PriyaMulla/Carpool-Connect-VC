package com.example.carpoolas.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.carpoolas.R;
import com.example.carpoolas.databinding.FragmentLogInScreenBinding;
import com.google.android.material.snackbar.Snackbar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LogInScreen# newInstance} factory method to
 * create an instance of this fragment.
 */
public class LogInScreen extends Fragment implements ILogInScreen{
    private static final String IS_REGISTERED = "isRegistered";
    FragmentLogInScreenBinding binding;
    private boolean isRegistered = false;
    Listener listener;

    public LogInScreen(ILogInScreen.Listener listener) {
        this.listener = listener;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.binding = FragmentLogInScreenBinding.inflate(inflater);
        return this.binding.getRoot();
    }

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

                /////////////////
                /*if(username.equals("admin") &&
                        password.equals("admin")) {
                    Snackbar.make(view,
                            "Redirecting...",Snackbar.LENGTH_SHORT).show();
                    LinearLayout layout = (LinearLayout) view.getRootView().findViewById(R.id.mainLayout);
                    layout.setVisibility(View.VISIBLE);
                    LogInScreen.this.listener.goToDashboard();
                }else{
                    Snackbar.make(view, "Wrong Credentials",Snackbar.LENGTH_SHORT).show();
                usernameText.clear();
                passwordTeext.clear();
                }*/
                ///////////////////
            }
        });

        this.binding.signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogInScreen.this.listener.goToCreateAccount(LogInScreen.this);
            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(IS_REGISTERED, this.isRegistered);
    }

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
    // prevent multiple registration attempts
    private void activateRegisteredConfig(){
        this.isRegistered = true;
        this.binding.signUpButton.setEnabled(false);
    }



}
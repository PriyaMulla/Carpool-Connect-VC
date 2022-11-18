package com.example.carpoolas.view;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.carpoolas.R;
import com.example.carpoolas.databinding.FragmentCreateAccountBinding;
import com.example.carpoolas.databinding.FragmentLogInScreenBinding;
import com.google.android.material.snackbar.Snackbar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LogInScreen# newInstance} factory method to
 * create an instance of this fragment.
 */
public class LogInScreen extends Fragment implements ILogInScreen{
    FragmentLogInScreenBinding binding;
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


        this.binding.logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Editable usernameText = LogInScreen.this.binding.usernameText.getText();
                String name = usernameText.toString();

                Editable passwordTeext = LogInScreen.this.binding.passwordText.getText();
                String password = passwordTeext.toString();
                if(name.equals("admin") &&
                        password.equals("admin")) {
                    Snackbar.make(view,
                            "Redirecting...",Snackbar.LENGTH_SHORT).show();
                    LinearLayout layout = (LinearLayout) view.getRootView().findViewById(R.id.mainLayout);
                    layout.setVisibility(View.VISIBLE);
                    LogInScreen.this.listener.goToDashboard(LogInScreen.this);
                }else{
                    Snackbar.make(view, "Wrong Credentials",Snackbar.LENGTH_SHORT).show();

                }
            }
        });

        this.binding.signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogInScreen.this.listener.goToCreateAccount(LogInScreen.this);
            }
        });
    }
}
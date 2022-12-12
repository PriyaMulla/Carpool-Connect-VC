package com.example.carpoolas.view;


import static com.example.carpoolas.model.Account.isValidEmail;
import static com.example.carpoolas.model.Account.isValidName;
import static com.example.carpoolas.model.Account.isValidPassword;
import static com.example.carpoolas.model.Account.isValidUsername;

import android.graphics.Color;
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
import com.example.carpoolas.databinding.FragmentCreateAccountBinding;
import com.google.android.material.snackbar.Snackbar;

//implements ICreateView interface using Android Frag
public class CreateAccountFragment extends Fragment implements ICreateAccountView {

    private static final String IS_CREATED = "isCreated";
    FragmentCreateAccountBinding binding;
    Listener listener;
    private boolean isCreated = false;

    public CreateAccountFragment(Listener listener) {
        this.listener = listener;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                            @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.binding = FragmentCreateAccountBinding.inflate(inflater);
        return this.binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        //set up a listener for "create" button clicks
        this.binding.createButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Editable enterName;
                Editable enterUsername;
                Editable enterPassword;
                Editable enterEmail;
                String name;
                String password;
                String username;
                String email;
                boolean isValid = true;

                    //extract user's name
                    enterName = CreateAccountFragment.this.binding.enterName.getText();
                    name = enterName.toString();
                    if (!isValidName(name)){
                        Snackbar.make(view, "Please provide your first and last name!",Snackbar.LENGTH_SHORT).show();
                        binding.enterName.setHintTextColor(Color.RED);
                        binding.enterName.setTextColor(Color.RED);
                        isValid = isValidName(name);
                    }
                binding.enterName.setTextColor(Color.BLACK);

                //extract user's username
                enterUsername = CreateAccountFragment.this.binding.enterUsername.getText();
                username = enterUsername.toString();
                if (!isValidUsername(username)){
                    Snackbar.make(view, "Please provide a valid username (longer than 5)!", Snackbar.LENGTH_SHORT).show();
                    binding.enterUsername.setHintTextColor(Color.RED);
                    binding.enterUsername.setTextColor(Color.RED);
                    isValid = isValid && isValidUsername(name);
                }

                binding.enterUsername.setTextColor(Color.BLACK);
                //extract user's password
                enterPassword = CreateAccountFragment.this.binding.enterPassword.getText();
                password = enterPassword.toString();
                if (!isValidPassword(password)){
                    Snackbar.make(view, "Please provide your password (One capital, one special, longer than 5)!",Snackbar.LENGTH_SHORT).show();
                    binding.enterPassword.setHintTextColor(Color.RED);
                    binding.enterPassword.setTextColor(Color.RED);
                    isValid = isValid && isValidPassword(password);
                }

                binding.enterPassword.setTextColor(Color.BLACK);
                //extract user's email
                enterEmail = CreateAccountFragment.this.binding.enterEmailAddress.getText();
                email = enterEmail.toString();
                if (!isValidEmail(email)){
                    Snackbar.make(view, "Please provide your vassar email!",Snackbar.LENGTH_SHORT).show();
                    binding.enterEmailAddress.setHintTextColor(Color.RED);
                    binding.enterEmailAddress.setTextColor(Color.RED);
                    isValid = isValid && isValidEmail(name);
                }
                binding.enterEmailAddress.setTextColor(Color.BLACK);

                if(isValid) {
                    CreateAccountFragment.this.listener.onCreateAccount(username, password, name, email, CreateAccountFragment.this);
                    LinearLayout layout = (LinearLayout) view.getRootView().findViewById(R.id.mainLayout);
                    layout.setVisibility(View.VISIBLE);
                }
            }
            }
        );

        }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(IS_CREATED, this.isCreated);
    }

    @Override
    public void onCreateSuccess() {
        this.isCreated = true;
        Snackbar.make(this.binding.getRoot(), "Account created!",Snackbar.LENGTH_SHORT).show();


    }


    @Override
    public void onAccountAlreadyExists() {
        Snackbar.make(this.binding.getRoot(), "Username taken",Snackbar.LENGTH_SHORT).show();
    }


}
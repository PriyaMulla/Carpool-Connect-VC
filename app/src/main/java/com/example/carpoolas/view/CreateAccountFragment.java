package com.example.carpoolas.view;

import static com.example.carpoolas.controller.MainActivity.isValidEmail;
import static com.example.carpoolas.controller.MainActivity.isValidName;
import static com.example.carpoolas.controller.MainActivity.isValidPassword;
import static com.example.carpoolas.controller.MainActivity.isValidUsername;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.carpoolas.R;
import com.example.carpoolas.controller.MainActivity;
import com.example.carpoolas.databinding.FragmentCreateAccountBinding;
import com.example.carpoolas.model.Account;
import com.example.carpoolas.model.CollectionOfAccounts;
import com.google.android.material.snackbar.Snackbar;

//implements ICreateView interface using Android Frag
public class CreateAccountFragment extends Fragment implements ICreateAccountView {

    FragmentCreateAccountBinding binding;
    Listener listener;

    public CreateAccountFragment(Listener listener) {
        // Required empty public constructor
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
                        Snackbar.make(view, "Please provide your name!",
                                Snackbar.LENGTH_INDEFINITE).show();
                        isValid = isValid && isValidName(name);
                    }

                    //extract user's username
                    enterUsername = CreateAccountFragment.this.binding.enterUsername.getText();
                    username = enterUsername.toString();
                    if (!isValidUsername(username)){
                        Snackbar.make(view, "Please provide a valid username!", Snackbar.LENGTH_INDEFINITE).show();
                        isValid = isValid && isValidUsername(name);
                    }

                    //extract user's password
                    enterPassword = CreateAccountFragment.this.binding.enterPassword.getText();
                    password = enterPassword.toString();
                    if (!isValidPassword(password)){
                        Snackbar.make(view, "Please provide your password!",
                                Snackbar.LENGTH_INDEFINITE).show();
                        isValid = isValid && isValidPassword(password);
                    }

                    //extract user's email
                    enterEmail = CreateAccountFragment.this.binding.enterEmailAddress.getText();
                    email = enterEmail.toString();
                    if (!isValidEmail(email)){
                        Snackbar.make(view, "Please provide your email!",
                                Snackbar.LENGTH_INDEFINITE).show();
                        isValid = isValid && isValidEmail(name);
                    }
                if(isValid) {
                        Snackbar.make((View) view, "Account created!",
                                Snackbar.LENGTH_INDEFINITE).show();
                    CreateAccountFragment.this.listener.onCreateAccount(name, password, username, email, CreateAccountFragment.this);
                }
                enterEmail.clear();
                enterUsername.clear();
                enterPassword.clear();
                enterName.clear();
            }

                //delegate the creation to the account to controller aka main activity

                //CreateAccountFragment.this.listener.onCreateAccount(username,password,name,email,CreateAccountFragment.this);

                //empty out the fields in preparation for next account

                //TODO: restrictions
            }


        );
    }


    @Override
    public void updateDisplay(Account acc) {
        this.binding.accountLabel.setText(acc.toString());
    }
}
package com.example.carpoolas.view;

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
import com.example.carpoolas.databinding.FragmentCreateAccountBinding;
import com.example.carpoolas.model.Account;

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
                //extract user's name
                Editable enterName = CreateAccountFragment.this.binding.enterName.getText();
                String name = enterName.toString();

                //extract user's username
                Editable enterUsername = CreateAccountFragment.this.binding.enterUsername.getText();
                String username = enterUsername.toString();

                //extract user's password
                Editable enterPassword = CreateAccountFragment.this.binding.enterPassword.getText();
                String password = enterPassword.toString();

                //extract user's email
                Editable enterEmail = CreateAccountFragment.this.binding.enterEmailAddress.getText();
                String email = enterEmail.toString();

                //send to controller to validate
                //delegate the creation to the account to controller
                //controller handles exceptions? Make controller in charge of making snackbar?

                //empty out the fields in preparation for next account
                enterEmail.clear();
                enterUsername.clear();
                enterPassword.clear();
                enterName.clear();
                //TODO: restrictions
            }
        }



        );
    }


    @Override
    public void updateDisplay(Account acc) {
        this.binding.accountLabel.setText(acc.toString());
    }
}
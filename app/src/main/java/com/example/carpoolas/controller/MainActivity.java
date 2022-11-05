package com.example.carpoolas.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.carpoolas.view.CreateAccountFragment;
import com.example.carpoolas.view.ICreateListingView;
import com.example.carpoolas.view.IMainView;
import com.example.carpoolas.view.MainView;
import com.example.carpoolas.view.ICreateAccountView;

import java.util.Date;

public class MainActivity extends AppCompatActivity implements ICreateAccountView.Listener {

    Account acc = new Account(username, password, name, email);
    IMainView mainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        this.mainView = new MainView(this);
        CreateAccountFragment createAccountFragment = new CreateAccountFragment(this);
        this.mainView.displayFragment(createAccountFragment, true, "createAccount");
        setContentView(this.mainView.getRootView());
    }

    @Override
    public void onCreateListing(@NonNull Date created, String role, Date dateTime, String start, String end, int seats, int listingID, @NonNull ICreateListingView view){

    }

    @Override
    public void onCreateAccount(@NonNull String username, String password, String name, String email, @NonNull ICreateAccountView view) {

    }
}
package com.example.carpoolas.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.carpoolas.R;
import com.example.carpoolas.view.CreateAccountFragment;
import com.example.carpoolas.view.IMainView;
import com.example.carpoolas.view.MainView;

public class MainActivity extends AppCompatActivity {

    private IMainView mainView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        this.mainView = new MainView(this);
        setContentView(this.mainView.getRootView());
        this.mainView.displayFragment(new CreateAccountFragment(), true, "createAccount");
        //setContentView(R.layout.activity_main);
    }
}
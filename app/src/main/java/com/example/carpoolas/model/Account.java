package com.example.carpoolas.model;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;

//represents user in application
public class Account implements Serializable {

    private String username; // the user's unique name

    //fields
    private String name = "";
    private String email = "";
    private String password = "";

    public Account(){}


    public Account(String username, String password, String name, String email){
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    //validations
    public static boolean isValidName(String input){
        char ch;
        for (int i = 0; i < input.length(); i++){
            ch = input.charAt(i);
            if (Character.isWhitespace(ch) && i > 0){
                return true;
            }
        }
        return false;
    }

    public static boolean isValidUsername (String input){
        return input.length() > 5;
    }

    public static boolean isValidPassword (String input){
        char ch;
        boolean capitalFlag = false;
        boolean specialFlag = false;
        if (!(input.length() > 5)){
            return false;
        }
        for (int i = 0; i < input.length(); i++){
            ch = input.charAt(i);
            if (Character.isUpperCase(ch)){
                capitalFlag = true;
            } else if (!Character.isLetter(ch) && !Character.isWhitespace(ch)){
                specialFlag = true;
            }
            if (capitalFlag && specialFlag){
                return true;
            }
        }
        return false;
    }

    public static boolean isValidEmail (String input){
        return ((input.contains("@vassar.edu")) && (input.length() > 11));
    }



    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }


    @NonNull
    @Override
    public String toString() {
        return name + "'s Account " +
                "\n Username: " + username +
                "\n Email: " + email +
                "\n Password: " + password;
    }
}

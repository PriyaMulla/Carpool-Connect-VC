package com.example.carpoolas.model;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Account implements Serializable {
    //TODO: account's listings

    private String username; // the user's unique name
    private AuthKey authKey; // the authentication key associated with the user

    //fields
    private String name = "";
    private String email = "";
    private String password = "";

    public Account(){}

    //constructor
    public Account(String username, String password, String name, String email){
        this.username = username;
        this.authKey = new AuthKey(password);
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

    public AuthKey getAuthKey() {return this.authKey;}

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    /**
     * Tests whether ths provided password matches the one provided when the user
     * was created.
     * @param password the plaintext password to test
     * @return true if the password matches, false otherwise.
     */
    public boolean validatePassword(String password){
        return this.authKey.validatePassword(password);
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

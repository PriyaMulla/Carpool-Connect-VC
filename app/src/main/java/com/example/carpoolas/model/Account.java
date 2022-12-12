package com.example.carpoolas.model;

import androidx.annotation.NonNull;
import java.io.Serializable;

//represents user in application
public class Account implements Serializable {



    //fields
    private String name = "";
    private String email = "";
    private String password = "";
    private String username; // the user's unique name

    public Account(){}


    /**
     * Creates an account with info
     * @param username accounts username
     * @param password accounts password
     * @param name accounts name
     * @param email accounts email
     */
    public Account(String username, String password, String name, String email){
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    /**
     * ensures validations for fields
     * @param input fields
     * @return true if valid
     */
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
    //end validations


    /**
     * retrieves accounts fields
     * @return field
     */
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


    /**
     * textual representation of user
     * @return text of user
     */
    @NonNull
    @Override
    public String toString() {
        return name + "'s Account " +
                "\n Username: " + username +
                "\n Email: " + email +
                "\n Password: " + password;
    }
}

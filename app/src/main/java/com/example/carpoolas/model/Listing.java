package com.example.carpoolas.model;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

public class Listing {
    //fields
    Date dateCreated;
    String role;
    Date dateTimeOfTrip;
    String startLocation;
    String endLocation;
    int seats;




    public Listing(Date created, String role, Date dateTime, String start, String end,  int seats){
        this.dateCreated = created;
        this.role = role;
        this.dateTimeOfTrip = dateTime;
        this.startLocation = start;
        this.endLocation = end;
        this.seats = seats;

    }


    public static boolean isValidStart(String start){
        return Pattern.compile("^(\\d{1,}) [a-zA-Z0-9\\s]+(\\,)? [a-zA-Z]+(\\,)? [A-Z]{2} [0-9]{5,6}$")
                .matcher(start)
                .find();
    }

    public static boolean isValidEnd(String end){
        return Pattern.compile("^(\\d{1,}) [a-zA-Z0-9\\s]+(\\,)? [a-zA-Z]+(\\,)? [A-Z]{2} [0-9]{5,6}$")
                .matcher(end)
                .find();
    }


    //object for date formatting
    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm");

    @NonNull
    @Override
    public String toString() {
        return "Listing: " +
                "\n Created: " + formatter.format(dateCreated) +
                "\n Role: " + role +
                "\n Date and Time: " + formatter.format(dateTimeOfTrip) +
                "\n Start: " + startLocation +
                "\n End: " + endLocation +
                "\n Seats: " + seats;
    }
}

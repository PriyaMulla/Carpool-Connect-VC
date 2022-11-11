package com.example.carpoolas.model;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Listing {
    //fields
    Date dateCreated;
    String role;
    Date dateTimeOfTrip;
    String startLocation;
    String endLocation;
    int seats;
    //int listingID;




    Listing(Date created, String role, Date dateTime, String start, String end,  int seats){
        this.dateCreated = created;
        this.role = role;
        this.dateTimeOfTrip = dateTime;
        this.startLocation = start;
        this.endLocation = end;
        this.seats = seats;
        //this.listingID = listingID;

    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public String getRole() {
        return role;
    }

    public Date getDateTimeOfTrip() {
        return dateTimeOfTrip;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public String getEndLocation() {
        return endLocation;
    }

    public int getSeats() {
        return seats;
    }

    //public int getListingID() {
    //    return listingID;
   // }

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

package com.example.carpoolas.model;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

public class Listing implements Serializable {
    //fields
    Date dateCreated;
    String role;
    Date dateTimeOfTrip;
    String startLocation;
    String endLocation;
    int seats;

    public Date getDateCreated() {
        return dateCreated;
    }

    public String getRole() {
        return role;
    }
    public String getEndLocation() {return endLocation;}

    public Date getDateTimeOfTrip() {
        return dateTimeOfTrip;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public int getSeats() {
        return seats;
    }

    public Listing() throws ParseException {};

    public Listing(Date created, String role, Date dateTime, String start, String end,  int seats) throws ParseException {
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
    static SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm");

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

    //convert Date to string
    String strDateCreated = formatter.format(dateCreated);
    String strDateTrip = formatter.format(dateTimeOfTrip);

    //constants for (de) serialization
    private static final String DATE_CREATED = "dateCreated";
    private static final String ROLE = "role";
    private static final String DATE_TIME = "dateTimeOfTrip";
    private static final String START_LOCATION = "startLocation";
    private static final String END_LOCATION = "endLocation";
    private static final String SEATS = "seats";

    /**
     * Converts the Listing the method is called on into a Bundle.
     *
     * @return A Bundle with the listing's contents
     */
    Bundle b = new Bundle();
    @NonNull
    public Bundle toBundle(){
        b.putString(DATE_CREATED, this.strDateCreated);
        b.putString(ROLE, this.role);
        b.putString(DATE_TIME, this.strDateTrip);
        b.putString(START_LOCATION, this.startLocation);
        b.putString(END_LOCATION, this.endLocation);
        b.putInt(SEATS, this.seats);
        return b;
    }



    /**
     * Creates and returns a Listing from a previously created Bundle.
     *
     * @param b the Bundle to convert to a Listing
     * @return the Listing representation of the input Bundle
     */
    @NonNull
    public static Listing fromBundle(@NonNull Bundle b) throws ParseException {
        //switch dates back to date type
        Date bDateCreated = formatter.parse(b.getString(DATE_CREATED));
        Date bDateTrip = formatter.parse(b.getString(DATE_TIME));
        return new Listing(bDateCreated, b.getString(ROLE), bDateTrip, b.getString(START_LOCATION), b.getString(END_LOCATION), b.getInt(SEATS));
    }

}

package com.example.carpoolas.model;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * represents a listing that is part of collection of listings
 */
public class Listing implements Serializable {
    //fields
    Date dateCreated;
    String role;
    Date dateTimeOfTrip;
    String startLocation;
    String endLocation;
    int seats;

    //attach account to listing
    public Account getCurAccount() {
        return curAccount;
    }

    Account curAccount;



    public Listing(){}

    /**
     * creates a listing
     * @param created date create
     * @param role what role user is
     * @param dateTime date of trip
     * @param start start location
     * @param end end location
     * @param seats amount of seat
     * @param curAccount account that created the listing
     */
    public Listing(Date created, String role, Date dateTime, String start, String end, int seats, Account curAccount){
        this.dateCreated = created;
        this.role = role;
        this.dateTimeOfTrip = dateTime;
        this.startLocation = start;
        this.endLocation = end;
        this.seats = seats;
        this.curAccount = curAccount;
    }

    //getters
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

    /**
     * ensures locations are in correct format
     * @param start location
     * @return true if in correct format
     */
    public static boolean isValidStart(String start){
        return Pattern.compile("^(\\d{1,}) [a-zA-Z0-9\\s]+(\\,)? [a-zA-Z]+(\\,)? [A-Z]{2} [0-9]{5,6}$")
                .matcher(start)
                .find();
    }

    /**
     * ensures locations are in correct format
     * @param end location
     * @return true if in correct format
     */
    public static boolean isValidEnd(String end){
        return Pattern.compile("^(\\d{1,}) [a-zA-Z0-9\\s]+(\\,)? [a-zA-Z]+(\\,)? [A-Z]{2} [0-9]{5,6}$")
                .matcher(end)
                .find();
    }


    //object for date formatting
    @SuppressLint("SimpleDateFormat")
    static SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm");

    /**
     * textual representation of listing
     * @return text of listing
     */
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



    //constants for (de) serialization
    private static final String DATE_CREATED = "dateCreated";
    private static final String ROLE = "role";
    private static final String DATE_TIME = "dateTimeOfTrip";
    private static final String START_LOCATION = "startLocation";
    private static final String END_LOCATION = "endLocation";
    private static final String SEATS = "seats";
    private static final String CURRENT_ACCOUNT = "curAccount";


    /**
     * Converts the Listing the method is called on into a String to Object Map.
     *
     * @return A map with the listing's contents
     */
    @NonNull
    public Map<String,Object> toMap() {
        Map<String,Object> map = new HashMap<>();
        map.put(DATE_CREATED, dateCreated);
        map.put(ROLE, role);
        map.put(DATE_TIME, dateTimeOfTrip);
        map.put(START_LOCATION, startLocation);
        map.put(END_LOCATION, endLocation);
        map.put(SEATS, seats);
        map.put(CURRENT_ACCOUNT, curAccount);

        return map;
    }

    /**
     * Creates and returns a Listing from a previously created String to Object Map.
     *
     * @param map the Map to convert to a listing
     * @return the listing representation of the input map
     */
    @NonNull
    public static Listing fromMap(@NonNull Map<String, Object> map){
        Date dateCreated = (Date) map.get(DATE_CREATED);
        String role = (String) map.get(ROLE);
        Date dateTimeOfTrip = (Date) map.get(DATE_TIME);
        String startLocation = (String) map.get(START_LOCATION);
        String endLocation = (String) map.get(END_LOCATION);
        Account curAccount = (Account) map.get(CURRENT_ACCOUNT);
        int seats = (int)(long) map.get(SEATS); // Firestore saves integers as longs
        return new Listing(dateCreated, role, dateTimeOfTrip, startLocation, endLocation, seats, curAccount);
    }


}

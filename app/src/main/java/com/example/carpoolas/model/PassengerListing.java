package com.example.carpoolas.model;

public class PassengerListing extends AListing {


    //constructor
    protected PassengerListing(int date, int time, String start, String end,int listingID,int seats){
        this.dateOfTrip = date;
        this.timeOfTrip = time;
        this.startLocation = start;
        this.endLocation = end;
        this.listingID = listingID;
        this.seats = seats;
    }
    public int getListingID() {
        return listingID;
    }

    @Override
    public String toString() {
        return "[Passenger] Listing: " +
               // "\n Created: " + dateCreated +
                "\n Date: " + dateOfTrip +
                "\n Time: " + timeOfTrip +
                "\n Start: " + startLocation +
                "\n End: " + endLocation +
                "\n Seats needed: " + seats;
    }
}


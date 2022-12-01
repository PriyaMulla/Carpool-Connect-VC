package com.example.carpoolas.model;

import androidx.annotation.NonNull;

import java.util.Date;
import java.util.LinkedList;
        import java.util.List;

/**
 * Represents a Page, which is composed of listings.
 */
public class CollectionOfListings {

    public List<Listing> listings;

    public CollectionOfListings() {
        this.listings = new LinkedList<Listing>();
    }



    /**
     * Creates listing and associates it with the Page of Listings.
     */
    public void addListing(Date created, String role, Date dateTime, String start, String end, int seats) {
        Listing lst = new Listing(created, role, dateTime, start, end, seats);
        this.listings.add(lst);
    }

    /**
     * associates listing with the Page of Listings.
     */
    public void addCreatedListing(Listing lst) {
        this.listings.add(lst);
    }


    @NonNull
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(); // using a string builder is more ef

        for (Listing lst : this.listings) { // java.util.list supports for-each loop
            sb.append(lst.toString());
            sb.append("\n");
        }

        return sb.toString();
    }

    public boolean isEmpty() {
        if (this.listings.isEmpty()){
            return true;
        }
        return false;
    }
}

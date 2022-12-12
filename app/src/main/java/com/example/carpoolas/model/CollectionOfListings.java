package com.example.carpoolas.model;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

/**
 * Represents a Page, which is composed of listings.
 */
public class CollectionOfListings implements Serializable {

    public final Collection<Listing> listings;

    public CollectionOfListings() {
        this.listings = new LinkedList<Listing>();
    }



    /**
     * Creates listing and associates it with the Page of Listings.
     */
    public void addListing(Date created, String role, Date dateTime, String start, String end, int seats, Account curAccount) {
        Listing lst = new Listing(created, role, dateTime, start, end, seats, curAccount);
        this.listings.add(lst);
    }

    /**
     * adds a new listing to collection of Listings
     * @param lst listing to be added
     */
    public void addCreatedListing(Listing lst) {
        this.listings.add(lst);
    }


    /**
     * textual rep of collection of listings
     * @return text rep
     */
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

    /**
     * checks if collection of listings is empty
     * @return true if empty
     */
    public boolean isEmpty() {
        if (this.listings.isEmpty()){
            return true;
        }
        return false;
    }
}

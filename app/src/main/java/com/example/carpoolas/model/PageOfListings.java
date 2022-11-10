package com.example.carpoolas.model;

import androidx.annotation.NonNull;

import java.util.Date;
import java.util.LinkedList;
        import java.util.List;

/**
 * Represents a Page, which is composed of listings.
 */
public class PageOfListings {

    public List<Listing> listings;

    public PageOfListings() {
        this.listings = new LinkedList<Listing>();
    }

    public List<Listing> getListing() {
        return listings;
    }

    /**
     * Creates listing and associates it with the Page of Listings.
     */
    public void addListing(Date created, String role, Date dateTime, String start, String end, int seats, int listingID) {
        Listing lst = new Listing(created, role, dateTime, start, end, seats, listingID);
        this.listings.add(lst);
    }

    /**
     * TODO: any method we might need, ie SEARCH!!
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

}

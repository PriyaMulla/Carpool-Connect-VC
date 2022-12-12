package com.example.carpoolas.model;

import java.util.Iterator;

public class SeatFilter implements IFilter{
    public int dSeats;

    @Override
    public CollectionOfListings filterListings(CollectionOfListings lst) {
        CollectionOfListings newPage = new CollectionOfListings();

        for (Listing listing : lst.listings) {
            if (this.dSeats == listing.seats) {
                newPage.addCreatedListing(listing);
            }
        }

        return newPage;
    }
}

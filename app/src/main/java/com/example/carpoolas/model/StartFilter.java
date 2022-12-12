package com.example.carpoolas.model;

import java.util.Iterator;

public class StartFilter implements IFilter{
    public String dStart;

    @Override
    public CollectionOfListings filterListings(CollectionOfListings lst) {
        CollectionOfListings newPage = new CollectionOfListings();

        for (Listing listing : lst.listings) {
            if (this.dStart.equals(listing.startLocation)) {
                newPage.addCreatedListing(listing);
            }
        }

        return newPage;
    }
}

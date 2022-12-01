package com.example.carpoolas.model;

import java.util.Iterator;

public class EndFilter implements IFilter{
    public String dEnd;

    @Override
    public CollectionOfListings filterListings(CollectionOfListings lst) {
        CollectionOfListings newPage = new CollectionOfListings();

        Iterator<Listing> listingsIterator = lst.listings.iterator();
        while (listingsIterator.hasNext()) {
            Listing listing = listingsIterator.next();
            if (this.dEnd.equals(listing.endLocation)) {
                newPage.addCreatedListing(listing);
            }
        }

        return newPage;
    }
}

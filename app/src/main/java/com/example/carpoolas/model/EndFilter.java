package com.example.carpoolas.model;

import java.util.Iterator;

public class EndFilter implements IFilter{
    String dEnd;

    @Override
    public PageOfListings filterListings(Object lst) {
        PageOfListings pageOfListings = (PageOfListings) lst;
        PageOfListings newPage = new PageOfListings();

        Iterator<Listing> listingsIterator = pageOfListings.listings.iterator();
        while (listingsIterator.hasNext()) {
            Listing listing = listingsIterator.next();
            if (this.dEnd.equals(listing.endLocation)) {
                newPage.listings.add(listing);
            }
        }

        return newPage;
    }
}

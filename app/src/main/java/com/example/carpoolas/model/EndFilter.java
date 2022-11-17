package com.example.carpoolas.model;

import java.util.Iterator;

public class EndFilter implements IFilter{
    public String dEnd;
    public PageOfListings newPage = new PageOfListings();

    @Override
    public PageOfListings filterListings(PageOfListings lst) {

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

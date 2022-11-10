package com.example.carpoolas.model;

import java.util.Iterator;

public class RoleFilter implements IFilter{
    String dRole;

    @Override
    public PageOfListings filterListings(Object lst) {
        PageOfListings pageOfListings = (PageOfListings) lst;
        PageOfListings newPage = new PageOfListings();

        Iterator<Listing> listingsIterator = pageOfListings.listings.iterator();
        while (listingsIterator.hasNext()) {
            Listing listing = listingsIterator.next();
            if (this.dRole.equals(listing.role)) {
                newPage.listings.add(listing);
            }
        }

        return newPage;
    }
}

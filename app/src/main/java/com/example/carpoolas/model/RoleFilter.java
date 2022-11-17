package com.example.carpoolas.model;

import java.util.Iterator;

public class RoleFilter implements IFilter{
    public String dRole;
    public PageOfListings newPage = new PageOfListings();

    @Override
    public PageOfListings filterListings(PageOfListings lst) {

        Iterator<Listing> listingsIterator = lst.listings.iterator();
        while (listingsIterator.hasNext()) {
            Listing listing = listingsIterator.next();
            if (this.dRole.equals(listing.role)) {
                newPage.addCreatedListing(listing);
            }
        }

        return newPage;
    }
}

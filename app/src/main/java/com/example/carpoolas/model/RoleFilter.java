package com.example.carpoolas.model;

import java.util.Iterator;

public class RoleFilter implements IFilter{
    public String dRole;

    @Override
    public CollectionOfListings filterListings(CollectionOfListings lst) {
        CollectionOfListings newPage = new CollectionOfListings();

        for (Listing listing : lst.listings) {
            if (this.dRole.equals(listing.role)) {
                newPage.addCreatedListing(listing);
            }
        }

        return newPage;
    }
}

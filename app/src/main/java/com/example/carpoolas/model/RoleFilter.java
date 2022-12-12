package com.example.carpoolas.model;

public class RoleFilter implements IFilter{
    public String dRole;

    /**
     * filter listings depending on role
     * @param lst listings to be filtered
     * @return newPage of listings
     */
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

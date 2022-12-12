package com.example.carpoolas.model;

public class EndFilter implements IFilter{
    public String dEnd;

    /**
     * filter listings depending on end location
     * @param lst listings to be filtered
     * @return newPage of listings
     */
    @Override
    public CollectionOfListings filterListings(CollectionOfListings lst) {
        CollectionOfListings newPage = new CollectionOfListings();

        for (Listing listing : lst.listings) {
            if (this.dEnd.equals(listing.endLocation)) {
                newPage.addCreatedListing(listing);
            }
        }

        return newPage;
    }
}

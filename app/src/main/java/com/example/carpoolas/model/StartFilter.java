package com.example.carpoolas.model;

public class StartFilter implements IFilter{
    public String dStart;

    /**
     * filter listings depending on start location
     * @param lst listings to be filtered
     * @return newPage of listings
     */
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

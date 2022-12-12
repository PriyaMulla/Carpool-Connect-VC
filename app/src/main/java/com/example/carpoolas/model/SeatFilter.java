package com.example.carpoolas.model;

public class SeatFilter implements IFilter{
    public int dSeats;

    /**
     * filter listings depending on amount of seats
     * @param lst listings to be filtered
     * @return newPage of listings
     */
    @Override
    public CollectionOfListings filterListings(CollectionOfListings lst) {
        CollectionOfListings newPage = new CollectionOfListings();

        for (Listing listing : lst.listings) {
            if (this.dSeats == listing.seats) {
                newPage.addCreatedListing(listing);
            }
        }

        return newPage;
    }
}

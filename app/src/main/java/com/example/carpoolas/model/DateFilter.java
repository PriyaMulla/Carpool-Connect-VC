package com.example.carpoolas.model;

import java.util.Date;
import java.util.Iterator;

public class DateFilter implements IFilter{
    Date dDate;

    @Override
    public PageOfListings filterListings(PageOfListings lst) {
        PageOfListings newPage = new PageOfListings();

        Iterator<Listing> listingsIterator = lst.listings.iterator();
        while (listingsIterator.hasNext()){
            Listing listing = listingsIterator.next();
            if (this.dDate == listing.dateCreated){
                newPage.listings.add(listing);
            }
        }

        return newPage;
    }

}

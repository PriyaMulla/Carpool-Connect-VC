package com.example.carpoolas.model;

import java.util.Date;
import java.util.Iterator;

public class DateFilter implements IFilter{
    public Date dDate;
    public PageOfListings newPage;

    @Override
    public PageOfListings filterListings(PageOfListings lst) {


        Iterator<Listing> listingsIterator = lst.listings.iterator();
        while (listingsIterator.hasNext()){
            Listing listing = listingsIterator.next();
            if (this.dDate == listing.dateCreated){
                this.newPage.listings.add(listing);
            }
        }

        return newPage;
    }

}

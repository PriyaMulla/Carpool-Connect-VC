package com.example.carpoolas.model;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

public class DateFilter implements IFilter{
    public Date dDate;

    //object for date formatting
    @SuppressLint("SimpleDateFormat")
    static SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm");

    @Override
    public CollectionOfListings filterListings(CollectionOfListings lst) {
        CollectionOfListings newPage = new CollectionOfListings();


        for (Listing listing : lst.listings) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(listing.dateTimeOfTrip);
            int day = cal.get(Calendar.DATE);
            int month = cal.get(Calendar.MONTH);
            int year = cal.get(Calendar.YEAR);
            cal.set(year, month, day, 0, 0, 0);
            Date cDate = (cal).getTime();
            if (this.dDate.equals(cDate)) {
                newPage.addCreatedListing(listing);
            }
        }

        return newPage;
    }

}

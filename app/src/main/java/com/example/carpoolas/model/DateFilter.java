package com.example.carpoolas.model;
import java.util.Calendar;
import java.util.Date;

public class DateFilter implements IFilter{
    public Date dDate;


    /**
     * filters listings based on date
     * @param lst listings to be filtered
     * @return newPage of listings
     */
    @Override
    public CollectionOfListings filterListings(CollectionOfListings lst) {
        CollectionOfListings newPage = new CollectionOfListings();


        for (Listing listing : lst.listings) {
            //extract just the date
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

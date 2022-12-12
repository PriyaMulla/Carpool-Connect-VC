package com.example.carpoolas.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.Date;

/**
 * tests filterListings method applied to all types by creating a page of listings
 * with 2 listings and having a given type match 1 of the listings
 */



class FilterTest {
    //create account
    Account priya = new Account();
    Account chris = new Account();

    //create listings
    Date cDate = new Date(122, 9, 7, 0, 0);
    Date gDate = new Date(122, 10, 16, 0, 0);
    Listing listing1 = new Listing(cDate, "Passenger", gDate, "123 Ray Ave, Pough, NY 12604", "3 Mond St, Keepsie, KY 13094", 2, priya);
    Listing listing2 = new Listing(cDate, "Driver", cDate, "23 College Ave, Arling, CO 53597", "62 Grad Ave, Ton, NJ 23154", 4, chris);
    Listing listing3 = new Listing(cDate, "Driver", gDate, "123 Ray Ave, Pough, NY 12604", "62 Grad Ave, Ton, NJ 23154", 4, priya);
    CollectionOfListings lst = new CollectionOfListings();

    @Test
    //date filtering
    void testDateFilterListings() {
        lst.addCreatedListing(listing1);
        lst.addCreatedListing(listing2);
        lst.addCreatedListing(listing3);

        DateFilter dateFilter = new DateFilter();
        dateFilter.dDate = gDate;
        CollectionOfListings nLst = new CollectionOfListings();
        nLst.addCreatedListing(listing1);
        nLst.addCreatedListing(listing3);
        assertEquals(nLst.listings, dateFilter.filterListings(lst).listings);
    }
    //start location filtering
    @Test
    void testStartFilterListings() {
        lst.addCreatedListing(listing1);
        lst.addCreatedListing(listing2);
        lst.addCreatedListing(listing3);

        StartFilter startFilter = new StartFilter();
        startFilter.dStart = "123 Ray Ave, Pough, NY 12604";
        CollectionOfListings nLst = new CollectionOfListings();
        nLst.addCreatedListing(listing1);
        nLst.addCreatedListing(listing3);
        assertEquals(nLst.listings, startFilter.filterListings(lst).listings);
    }

    //end location filtering
    @Test
    void testEndFilterListings() {
        lst.addCreatedListing(listing1);
        lst.addCreatedListing(listing2);
        lst.addCreatedListing(listing3);

        EndFilter endFilter = new EndFilter();
        endFilter.dEnd = "62 Grad Ave, Ton, NJ 23154";
        CollectionOfListings nLst = new CollectionOfListings();
        nLst.addCreatedListing(listing2);
        nLst.addCreatedListing(listing3);
        assertEquals(nLst.listings, endFilter.filterListings(lst).listings);
    }

    //end location filtering
    @Test
    void testRoleFilterListings() {
        lst.addCreatedListing(listing1);
        lst.addCreatedListing(listing2);
        lst.addCreatedListing(listing3);

        RoleFilter roleFilter = new RoleFilter();
        roleFilter.dRole = "Driver";
        CollectionOfListings nLst = new CollectionOfListings();
        nLst.addCreatedListing(listing2);
        nLst.addCreatedListing(listing3);
        assertEquals(nLst.listings, roleFilter.filterListings(lst).listings);
    }
}
package com.example.carpoolas.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.Date;

class FilterTest {

    @Test
    void testDateFilterListings() {
        Date cDate = new Date(122, 10, 13, 2, 52);
        Date dDate = new Date(122, 10, 16, 2, 30);
        PageOfListings lst = new PageOfListings();
        lst.addListing(cDate, "Passenger", dDate, "123 Ray Ave, Pough, NY 12605", "3 Ray Ave, Pough, NY 12604", 2);
        lst.addListing(cDate, "Driver", cDate, "135 Ray Ave, Pough, CO 12605", "3 Ray Ave, Pough, NY 12409", 4);

        DateFilter dateFilter = new DateFilter();
        dateFilter.dDate = dDate;
        dateFilter.filterListings(lst);
        PageOfListings nLst = new PageOfListings();
        nLst.addListing(cDate, "Passenger", dDate, "123 Ray Ave, Pough, NY 12605", "3 Ray Ave, Pough, NY 12604", 2);
        assertEquals(nLst, dateFilter.newPage);
    }
    @Test
    void testStartFilterListings() {
        Date cDate = new Date(122, 10, 13, 2, 52);
        Date dDate = new Date(122, 10, 16, 2, 30);
        PageOfListings lst = new PageOfListings();
        lst.addListing(cDate, "Passenger", dDate, "123 Ray Ave, Pough, NY 12605", "3 Ray Ave, Pough, NY 12604", 2);
        lst.addListing(cDate, "Driver", cDate, "135 Ray Ave, Pough, CO 12605", "3 Ray Ave, Pough, NY 12409", 4);

        StartFilter startFilter = new StartFilter();
        startFilter.dStart = "123 Ray Ave, Pough, NY 12605";
        startFilter.filterListings(lst);
        PageOfListings nLst = new PageOfListings();
        nLst.addListing(cDate, "Passenger", dDate, "123 Ray Ave, Pough, NY 12605", "3 Ray Ave, Pough, NY 12604", 2);
        assertEquals(nLst, startFilter.newPage);
    }
}
package com.example.carpoolas.model;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import java.util.Date;

class PageOfListingsTest {
    /**
     * Tests PageOfListings toString  and addListing method by adding listings to PageOL and checking result
     */

    @Test
    void testToString() {
        PageOfListings lst = new PageOfListings();
        assertEquals("", lst.toString(), "non-empty string for empty page");

        //dates to check against
        Date cDate = new Date(122, 10, 13, 02, 52);
        Date dDate = new Date(122, 10, 16, 02, 30);
        lst.addListing(cDate, "Passenger", dDate, "123 Ray Ave, Pough, NY 12605", "3 Ray Ave, Pough, NY 12604", 2);
        assertEquals("Listing: \n Created: 11/13/2022 02:52\n Role: Passenger\n Date and Time: 11/16/2022 02:30\n Start: 123 Ray Ave, Pough, NY 12605\n End: 3 Ray Ave, Pough, NY 12604\n Seats: 2\n", lst.toString());

        lst.addListing(cDate, "Driver", dDate, "135 Ray Ave, Pough, CO 12605", "3 Ray Ave, Pough, NY 12409", 4);
        assertEquals("Listing: \n Created: 11/13/2022 02:52\n Role: Passenger\n Date and Time: 11/16/2022 02:30\n Start: 123 Ray Ave, Pough, NY 12605\n End: 3 Ray Ave, Pough, NY 12604\n Seats: 2\nListing: \n Created: 11/13/2022 02:52\n Role: Driver\n Date and Time: 11/16/2022 02:30\n Start: 135 Ray Ave, Pough, CO 12605\n End: 3 Ray Ave, Pough, NY 12409\n Seats: 4\n", lst.toString());

    }
}
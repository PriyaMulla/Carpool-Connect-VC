package com.example.carpoolas.model;
import org.junit.jupiter.api.Test; // for the @Test annotation
import static org.junit.jupiter.api.Assertions.*;

import com.example.carpoolas.model.CollectionOfAccounts;

public class CollectionOfAccountsTest {
    /**
     * Tests CollectionOfAccount's toString and addAccount method by adding multiple accounts to CollectionOA and checking result
     */
    @Test
    void testToString(){

        CollectionOfAccounts coll = new CollectionOfAccounts();
        assertEquals("", coll.toString(), "non-empty string for empty collection");


        coll.addAccount("priyankamu", "Hello!hi", "Priya M", "p@vassar.edu");
        assertEquals("Priya M's Account \n Username: priyankamu\n Email: p@vassar.edu\n Password: Hello!hi\n", coll.toString());

        //second acc
        coll.addAccount("chrisc", "Heyo!hi", "Chris C", "c@vassar.edu");
        assertEquals("Priya M's Account \n Username: priyankamu\n Email: p@vassar.edu\n Password: Hello!hi\nChris C's Account \n Username: chrisc\n Email: c@vassar.edu\n Password: Heyo!hi\n", coll.toString());
    }
}
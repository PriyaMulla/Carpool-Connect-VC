package com.example.carpoolas.persistance;

import androidx.annotation.NonNull;

import com.example.carpoolas.model.Account;
import com.example.carpoolas.model.CollectionOfListings;
import com.example.carpoolas.model.Listing;

public interface IPersistenceFacade {
    /**
     * Interface that classes interested in being notified of data-generating events
     * from the persistence layer should implement.
     */
    interface DataListener<T> {

        /**
         * Called when the requested data is successfully received.
         * @param data the data that was received from the persistence subsystem.
         */
        void onDataReceived(@NonNull T data);

        /**
         * Called when the requested data isn't found in the underlying persistence subsystem.
         */
        void onNoDataFound();

    }
    /**
     * Interface that classes interested in being notified of binary (i.e., true vs false) events
     * from the persistence layer should implement.
     */
    interface BinaryResultListener {

        /**
         * Called when the answer to the issued query is positive.
         */
        void onYesResult();
        /**
         * Called when the answer to the issued query is negative.
         */
        void onNoResult();
    }


    /* collectingOfListings -related methods start */
    /**
     * Saves the listing passed in as input to the underlying persistence solution.
     * @param listing the listing to be saved
     */
    void saveListing(Listing listing);

    /**
     * Saves the account passed in as input to the underlying persistence solution.
     * @param account the account to be saved
     */
    void saveAccount(Account account);

    /**
     * Issues a COL retrieval operation.
     * @param listener the listener to be notified when the ledger becomes available.
     */
    void retrieveCollectionOfListings(DataListener<CollectionOfListings> listener);

    /* collectingOfListings -related methods end */

    //TODO:

    /* authentication-related methods start */

    /**
     *  Creates an entry for the specified User in the underlying persistence subsystem.
     *
     * @param acc the user to be created
     * @param listener the observer to be notified of the query result. OnYesResult() is called if
     *                 a new user was created. Conversely, OnNoResult() is called if a user with
     *                 the specified username already existed.
     */
    void createAccountIfNotExists(@NonNull Account acc, @NonNull BinaryResultListener listener);

    /**
     * Retrieves the User with the specified username from the underlying persistence subsystem.
     *
     * @param username the username of the user to be retrieved.
     * @param listener observer to be notified of query result. onDataReceived() is called if a
     *                 user with the specified username was found. Otherwise, onNoDataFound() is
     *                 called.
     */
    void retrieveAccount(@NonNull String username, @NonNull DataListener<Account> listener);

    /* authentication-related methods end */

}

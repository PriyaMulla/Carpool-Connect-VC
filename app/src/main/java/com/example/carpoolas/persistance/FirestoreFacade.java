package com.example.carpoolas.persistance;
import androidx.annotation.NonNull;

import com.example.carpoolas.model.CollectionOfListings;
import com.example.carpoolas.model.Listing;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;


public class FirestoreFacade implements IPersistenceFacade{

    private final FirebaseFirestore db = FirebaseFirestore.getInstance(); // database connection

    private static final String LISTINGS_COLLECTION = "LISTINGS_COLLECTION"; // listings collection name
    //private static final String USERS_COLLECTION = "USERS_COLLECTION"; // users collection name


    /* ledger-related methods start */

    /**
     * Saves the sale passed in as input to the underlying persistence solution.
     * @param listing the listing to be saved
     */
    public void saveListing(Listing listing){
        this.db.collection(LISTINGS_COLLECTION).add(listing); // creates new document with pseudorandom id, uses firestore's built-in serialization
    }

    /**
     * Issues a CollectionOfListings retrieval operation.
     * @param listener the listener to be notified when the collectionOfListings becomes available.
     */
    public void retrieveCollectionOfListings(DataListener<CollectionOfListings> listener){
        this.db.collection(LISTINGS_COLLECTION)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() { // called when the data comes back from the database
                    @Override
                    public void onSuccess(QuerySnapshot qsnap) {
                        CollectionOfListings collectionOfListings = new CollectionOfListings();
                        for (DocumentSnapshot dsnap : qsnap){
                            Listing listing = dsnap.toObject(Listing.class);
                            collectionOfListings.addCreatedListing(listing);
                        }
                        listener.onDataReceived(collectionOfListings); // let the listener know we have a COL now
                    }
                });
    }

    /* ledger-related methods end */

   /* *//* authentication-related methods start *//*

    *//**
     *  Creates an entry for the specified User in the underlying persistence subsystem.
     *
     * @param user the user to be created
     * @param listener the observer to be notified of the query result. OnYesResult() is called if
     *                 a new user was created. Conversely, OnNoResult() is called if a user with
     *                 the specified username already existed.
     *//*
    @Override
    public void createUserIfNotExists(@NonNull User user, @NonNull BinaryResultListener listener) {

        String username = user.getUsername();

        this.retrieveUser(username, new DataListener<User>() {
            @Override
            public void onDataReceived(@NonNull User data) {
                listener.onNoResult();
            }

            @Override
            public void onNoDataFound() {
                db.collection(USERS_COLLECTION).document(username).
                        set(user).
                        addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                listener.onYesResult();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                listener.onNoResult();
                            }
                        });

            }
        });
    }

    *//**
     * Retrieves the User with the specified username from the underlying persistence subsystem.
     *
     * @param username the username of the user to be retrieved.
     * @param listener observer to be notified of query result. onDataReceived() is called if a
     *                 user with the specified username was found. Otherwise, onNoDataFound() is
     *                 called.
     *//*
    @Override
    public void retrieveUser(@NonNull String username, @NonNull DataListener<User> listener) {

        db.collection(USERS_COLLECTION).document(username).get().
                addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                         @Override
                                         public void onSuccess(DocumentSnapshot dsnap) {
                                             if (dsnap.exists()){
                                                 User user = dsnap.toObject(User.class);
                                                 listener.onDataReceived(user);
                                             } else // no user found
                                                 listener.onNoDataFound();
                                         }
                                     }
                );

    }

    *//* authentication-related methods end */
}

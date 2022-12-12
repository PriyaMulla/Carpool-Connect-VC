package com.example.carpoolas.model;


public interface IFilter {
    //interface for all filters

    /**
     * filter listings
     * @param lst listings to be filtered
     */
    CollectionOfListings filterListings(CollectionOfListings lst);


}


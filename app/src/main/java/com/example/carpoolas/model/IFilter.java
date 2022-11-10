package com.example.carpoolas.model;


public interface IFilter<PageOfListings> {
    public PageOfListings filterListings(PageOfListings lst);

}


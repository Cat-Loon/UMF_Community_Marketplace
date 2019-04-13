package com.umfmarketplace.hits;

import com.google.firebase.database.IgnoreExtraProperties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.umfmarketplace.CreateListingDB;
import com.umfmarketplace.Listing;

@IgnoreExtraProperties
public class PostSource {

    @SerializedName("_source")
    @Expose
    private Listing listing;

    public Listing getListing() {
        return listing;
    }

    public void setListing(Listing listing) {
        this.listing = listing;
    }
}

/* Authored by: Katelynn Slater
University of Michigan-Flint
Winter 2019 Capstone Project

Special thanks to Mitch Tabian for his documented work on ElasticSearch and querying with Retrofit
    and to the Firebase Google team for their support articles for Firebase Authentication
 */

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

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

import java.util.List;

@IgnoreExtraProperties
public class HitsList {

    @SerializedName("hits")
    @Expose
    private List<PostSource> postIndex;

    public List<PostSource> getPostIndex() {
        return postIndex;
    }

    public void setPostIndex(List<PostSource> postIndex) {
        this.postIndex = postIndex;
    }
}

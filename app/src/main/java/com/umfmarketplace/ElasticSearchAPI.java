package com.umfmarketplace;

import com.umfmarketplace.hits.HitsObject;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Query;

public interface ElasticSearchAPI {

    @GET("_search/")
    Call<HitsObject> search(
            @HeaderMap Map<String, String> headers,
            @Query("default_operator") String operator, //1st query - prepends question mark to URL
            @Query("q") String query //2nd query - prepends an & symbol
    );

}

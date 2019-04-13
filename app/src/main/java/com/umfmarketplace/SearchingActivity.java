package com.umfmarketplace;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.umfmarketplace.hits.HitsList;
import com.umfmarketplace.hits.HitsObject;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Credentials;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchingActivity extends CreateListingDB implements View.OnClickListener{

    private static final String TAG = "SearchingActivity";
    private static final String BASE_URL = "http://35.193.38.183//elasticsearch/book_listings/listing";
    private static final int NUM_GRID_COLUMNS = 3;
    private static final int GRID_ITEM_MARGIN = 5;

    private EditText mSearchField;
    private String mElasticPassword;
    private String mPrefAuthor;
    private String mPrefBook;
    private String mPrefClass;
    private ArrayList<Listing> mLists;

    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = database.getReference();
    DatabaseReference reference = databaseReference.getRef().child("Book_Listings");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searching);
        //Views
        mRecyclerView = findViewById(R.id.recyclerView);

        //Text Field
        mSearchField = findViewById(R.id.search_field);

        //Button
        findViewById(R.id.searchButton).setOnClickListener(this);

        getElasticPassword();
        init();

    }

    private void setUpListingsList(){
        RecyclerViewMargin itemDecorator = new RecyclerViewMargin(GRID_ITEM_MARGIN, NUM_GRID_COLUMNS);
        mRecyclerView.addItemDecoration(itemDecorator);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(SearchingActivity.this, NUM_GRID_COLUMNS);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mAdapter = new MyAdapter(mLists);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void init(){
        /*mFilters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startScan = new Intent(SearchingActivity.this, FilterActivity.class);
                startActivity(startScan);
            }
        });*/

        /*mSearchField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH
                    ||actionId == EditorInfo.IME_ACTION_DONE
                    || event.getAction() == KeyEvent.ACTION_DOWN
                    || event.getKeyCode() == KeyEvent.KEYCODE_ENTER){

                    mPosts = new ArrayList<BookListing>();

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    ElasticSearchAPI searchAPI = retrofit.create(ElasticSearchAPI.class);

                    HashMap<String, String> headerMap = new HashMap<String, String>();
                    headerMap.put("Authorization", Credentials.basic("user", mElasticPassword));

                    String searchString = "";

                    if(!mSearchField.equals("")){
                        searchString = searchString + mSearchField.getText().toString() + "*";
                    }
                    if(!mPrefAuthor.equals("")){
                        searchString = searchString + " Author:" + mPrefAuthor;
                    }
                    if(!mPrefBook.equals("")){
                        searchString = searchString + " Book:" + mPrefBook;
                    }
                    if(!mPrefClass.equals("")){
                        searchString = searchString + " Class:" + mPrefClass;
                    }

                    Call<HitsObject> call = searchAPI.search(headerMap, "AND", searchString);

                    call.enqueue(new Callback<HitsObject>() {
                        @Override
                        public void onResponse(Call<HitsObject> call, Response<HitsObject> response) {
                            HitsList hitsList = new HitsList();
                            String jsonResponse = "";
                            try{
                                Log.d(TAG, "onReponse - Server Reponse: " + response.toString());

                                if(response.isSuccessful()){
                                    hitsList = response.body().getHits();
                                } else {
                                    jsonResponse = response.errorBody().string();
                                }

                                Log.d(TAG, "onResponse - hits: " + hitsList);

                                for(int i = 0; i < hitsList.getPostIndex().size(); i++){
                                    Log.d(TAG, "onResponse - data: " + hitsList.getPostIndex().get(i).getListing().toString());

                                    mPosts.add(hitsList.getPostIndex().get(i).getListing());
                                }
                                Log.d(TAG, "onResponse - size: " + mPosts.size());

                                //Set up list of Book Listings
                            }
                            catch(NullPointerException e){
                                Log.e(TAG, "onResponse - NullPointerException: " + e.getMessage());
                            }
                            catch(IndexOutOfBoundsException e){
                                Log.e(TAG, "onResponse - IndexOutOfBoundsException: " + e.getMessage());
                            }
                            catch(IOException e){
                                Log.e(TAG, "onResponse - IOException: " + e.getMessage());
                            }

                        }

                        @Override
                        public void onFailure(Call<HitsObject> call, Throwable t) {
                            Log.e(TAG, "onFailure: " + t.getMessage());
                            Toast.makeText(SearchingActivity.this,"Firebase DB Search Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                return false;
            }
        });*/


    }

    @Override
    protected void onResume() {
        super.onResume();
        getFilters();
    }

    private void getElasticPassword(){
        Log.d(TAG,"Retrieving ElasticSearch password from Firebase");

        Query query = FirebaseDatabase.getInstance().getReference()
                .child(getString(R.string.node_elastic))
                .orderByValue();

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                DataSnapshot singleSnapshot = dataSnapshot.getChildren().iterator().next();
                mElasticPassword = singleSnapshot.getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void getFilters(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        //mPrefAuthor = preferences.getString(getString(R.id.preferences_author),"");
        //mPrefBook = preferences.getString(getString(R.id.preferences_book),"");
        //mPrefClass = preferences.getString(getString(R.id.preferences_class),"");

        Log.d(TAG, "Getting Filters: \nAuthor: " + mPrefAuthor + "\nBook: " + mPrefBook +
                "\nClass: " + mPrefClass);
    }


    @Override
    public void onClick(View v) {

        int i = v.getId();

        if(i == R.id.searchButton){

            mLists = new ArrayList<Listing>();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ElasticSearchAPI searchAPI = retrofit.create(ElasticSearchAPI.class);

            HashMap<String, String> headerMap = new HashMap<String, String>();
            headerMap.put("Authorization", Credentials.basic("user", mElasticPassword));

            String searchString = "";

            if(!mSearchField.equals("")){
                searchString = searchString + mSearchField.getText().toString() + "*";
            }
            if(!mPrefAuthor.equals("")){
                searchString = searchString + " Author:" + mPrefAuthor;
            }
            if(!mPrefBook.equals("")){
                searchString = searchString + " Book:" + mPrefBook;
            }
            if(!mPrefClass.equals("")){
                searchString = searchString + " Class:" + mPrefClass;
            }

            Call<HitsObject> call = searchAPI.search(headerMap, "AND", searchString);

            call.enqueue(new Callback<HitsObject>() {
                @Override
                public void onResponse(Call<HitsObject> call, Response<HitsObject> response) {
                    HitsList hitsList = new HitsList();
                    String jsonResponse = "";
                    try{
                        Log.d(TAG, "onReponse - Server Reponse: " + response.toString());

                        if(response.isSuccessful()){
                            hitsList = response.body().getHits();
                        } else {
                            jsonResponse = response.errorBody().string();
                        }

                        Log.d(TAG, "onResponse - hits: " + hitsList);

                        for(int i = 0; i < hitsList.getPostIndex().size(); i++){
                            Log.d(TAG, "onResponse - data: " + hitsList.getPostIndex().get(i).getListing().toString());

                            mLists.add(hitsList.getPostIndex().get(i).getListing());
                        }
                        Log.d(TAG, "onResponse - size: " + mLists.size());

                        //Set up list of Book Listings
                        setUpListingsList();
                    }
                    catch(NullPointerException e){
                        Log.e(TAG, "onResponse - NullPointerException: " + e.getMessage());
                    }
                    catch(IndexOutOfBoundsException e){
                        Log.e(TAG, "onResponse - IndexOutOfBoundsException: " + e.getMessage());
                    }
                    catch(IOException e){
                        Log.e(TAG, "onResponse - IOException: " + e.getMessage());
                    }

                }

                @Override
                public void onFailure(Call<HitsObject> call, Throwable t) {
                    Log.e(TAG, "onFailure: " + t.getMessage());
                    Toast.makeText(SearchingActivity.this,"Firebase DB Search Failed", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}


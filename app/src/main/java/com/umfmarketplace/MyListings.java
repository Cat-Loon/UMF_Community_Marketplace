/* Authored by: Katelynn Slater & Jared Suave
University of Michigan-Flint
Winter 2019 Capstone Project

Special thanks to Mitch Tabian for his documented work on ElasticSearch and querying with Retrofit
    and to the Firebase Google team for their support articles for Firebase Authentication
 */

package com.umfmarketplace;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MyListings extends AppCompatActivity implements
        View.OnClickListener  {

    //MyListings Activity for pulling all DB entries created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_listings);

        //Buttons
        findViewById(R.id.createNewListingButton).setOnClickListener(this);
        findViewById(R.id.editListingButton).setOnClickListener(this);
        findViewById(R.id.deleteListingButton).setOnClickListener(this);
        findViewById(R.id.myListingsBackButton).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();

        if (i == R.id.createNewListingButton) {
            Intent createListing = new Intent(MyListings.this, CreateListingDB.class);
            startActivity(createListing);
        } else if (i == R.id.editListingButton) {
            //did not finish this section of the project
        } else if (i == R.id.deleteListingButton){
            //did not finish this section of the project
        } else if (i == R.id.myListingsBackButton){
            Intent backToMain = new Intent(MyListings.this, MainActivity.class);
            startActivity(backToMain);
        }
    }


}

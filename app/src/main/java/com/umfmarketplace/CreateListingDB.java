package com.umfmarketplace;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateListingDB extends AppCompatActivity {
    public static class BookListing{
        public String TheBook;
        public String TheAuthor;
        public String ISBNasString;
        public String PriceasString;
        public String Condition;
        public String ClassUsed;

        public BookListing(String BookNameIn, String AuthorNameIn, String ISBNIn, String PriceIn, String ConditionIn, String ClassUsedIn){
            TheBook = BookNameIn;
            TheAuthor = AuthorNameIn;
            ISBNasString = ISBNIn;
            PriceasString = PriceIn;
            Condition = ConditionIn;
            ClassUsed = ClassUsedIn;
        }
    }

    private Button CreateListing;
    private Button scanTextbook;
    private Button backToMyListings;
    private EditText BookName, AuthorName, BookISBN, BookPrice, BookCondition, BookClassUsed;
    private  TextView PrintString;


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = database.getReference();
    DatabaseReference reference = databaseReference.getRef().child("book_listings");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_listing_db);

        CreateListing = findViewById(R.id.CreateListing);
        scanTextbook = findViewById(R.id.scanTextbook);
        backToMyListings = findViewById(R.id.backToMyListings);
        BookName = findViewById(R.id.BookName);
        AuthorName = findViewById(R.id.AuthorName);
        BookISBN = findViewById(R.id.ISBN);
        BookPrice = findViewById(R.id.Price);
        BookCondition = findViewById(R.id.BookCondition);
        BookClassUsed = findViewById(R.id.BookClassUsed);

        PrintString = findViewById(R.id.PrintString);

        CreateListing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Book = BookName.getText().toString().trim();
                String Author = AuthorName.getText().toString().trim();
                String ISBN = BookISBN.getText().toString();
                String Price = BookPrice.getText().toString();
                String Condition = BookCondition.getText().toString().trim();
                String Class = BookClassUsed.getText().toString().trim();
                String Result;

                if (TextUtils.isEmpty(Book)){
                    BookName.setError("Required.");
                }
                if (TextUtils.isEmpty(Author)){
                    AuthorName.setError("Required");
                }
                if (TextUtils.isEmpty(ISBN)){
                    BookISBN.setError("Required");
                }
                if (TextUtils.isEmpty(Price)){
                    BookPrice.setError("Required");
                }
                if (TextUtils.isEmpty(Condition)){
                    BookCondition.setError("Required");
                }

                BookListing TheListing = new BookListing(Book, Author, ISBN, Price, Condition, Class);
                reference.child(Author).setValue(TheListing);

                Result = "Textbook: " + TheListing.TheBook + " by " + TheListing.TheAuthor;

                Toast.makeText(CreateListingDB.this, "New Listing created successfully.",
                        Toast.LENGTH_SHORT).show();

                PrintString.setText(Result);
            }
        });

        scanTextbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startScan = new Intent(CreateListingDB.this, ListingActivity.class);
                startActivity(startScan);
            }
        });

        backToMyListings.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
            Intent backToListings = new Intent(CreateListingDB.this, MyListings.class);
            startActivity(backToListings);

            }
        });

    }
}

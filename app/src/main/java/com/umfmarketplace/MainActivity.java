package com.umfmarketplace;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends BaseActivity implements
        View.OnClickListener {
    private FirebaseAuth mAuth;


   /* mAuth mAuthListener = new FirebaseAuth.AuthStateListener(){
        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth){
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if(user != null){

            }
        }
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Buttons
        findViewById(R.id.MyListingsButton).setOnClickListener(this);
        findViewById(R.id.SearchTextbooksButton).setOnClickListener(this);
        findViewById(R.id.ViewTextbooksButton).setOnClickListener(this);
        findViewById(R.id.PreviousSalesButton).setOnClickListener(this);
        //findViewById(R.id.MessagesButton).setOnClickListener(this);
        findViewById(R.id.SignOutMainButton).setOnClickListener(this);

        // [START initialize_auth]
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]

    }

    // [START on_start_check_user]
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }
    // [END on_start_check_user]

    private void signOut() {
        mAuth.signOut();
    }

/*    private void updateUI(FirebaseUser user) {
        hideProgressDialog();
        if (user != null) {

        } else {

        }
    }*/

    @Override
    public void onClick(View v) {
        int i = v.getId();

        if (i == R.id.MyListingsButton) {
            Intent goToListings = new Intent(MainActivity.this, MyListings.class);
            startActivity(goToListings);
        } else if (i == R.id.SearchTextbooksButton) {
            Intent startSearch = new Intent(MainActivity.this, SearchingActivity.class);
            startActivity(startSearch);
        } else if (i == R.id.ViewTextbooksButton) {
            Intent ViewListings = new Intent(MainActivity.this, ViewListings.class);
            startActivity(ViewListings);
        } else if (i == R.id.PreviousSalesButton) {

        } else if (i == R.id.SignOutMainButton){
            signOut();
        }
    }
    /*var user = FirebaseUser.get

    user.updateProfile({
        displayName: "Jane Q. User",
                photoURL: "https://example.com/jane-q-user/profile.jpg"
    }).then(function() {
        // Update successful.
    }).catch(function(error) {
        // An error happened.
    });*/



}
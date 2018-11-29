package com.example.n01202172.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BatteryActivity extends AppCompatActivity {
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private FirebaseAuth mAuth;
    Database mData;

    private TextView voltage;
    private TextView charger;

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batteryactivity);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_keyboard_arrow_left_black_24dp);
        actionBar.setDisplayHomeAsUpEnabled(true);

        getDatabase();
        findAllViews();
        showInfo();

    }
    private void findAllViews() {

        voltage = findViewById(R.id.textView4);
        charger = findViewById(R.id.textView7);
    }

    private void getDatabase(){
        // TODO: Find the refernce form the database.
        myRef = FirebaseDatabase.getInstance().getReference("data");
        //myRef = FirebaseDatabase.getInstance().getReference("Voltage");

        //  myRef = database.getReference();
    }

    private void showInfo() {
        //mAuth = FirebaseAuth.getInstance();
        //myRef = FirebaseDatabase.getInstance().getReference("");
        //myRef = database.getReference();



        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //String realtemp = dataSnapshot.child("temperature").getValue(String.class);
                Database ds = (Database) dataSnapshot.getValue(Database.class);

                voltage.setText("Volt, " + ds.getVolt());
                charger.setText("Charging, " + ds.getCharge());


            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(BatteryActivity.this, "Data unavailable, please try again later", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

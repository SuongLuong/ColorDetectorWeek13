package com.example.n01202172.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyBattery extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private FirebaseAuth mAuth;
    Database mData;

    private TextView battery;


    //private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //mTextMessage.setText(R.string.title_home);
                    finish();
                    return true;
                case R.id.navigation:
                    //mTextMessage.setText(R.string.title_dashboard);
                    Intent intent = new Intent(MyBattery.this,BatteryActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.bardata:
                    Intent intent1 = new Intent(MyBattery.this,Statistics.class);
                    startActivity(intent1);
                    return true;

            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mybattery);

        getDatabase();
        findAllViews();
        showInfo();


       // mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void findAllViews() {

        battery = findViewById(R.id.textView2);
    }

    private void getDatabase(){
        // TODO: Find the refernce form the database.
        myRef = FirebaseDatabase.getInstance().getReference("Battery");
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

                battery.setText("Battery "+ ds.getBat());


            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MyBattery.this, "Data unavailable, please try again later", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

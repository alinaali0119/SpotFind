package com.example.spotfind;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import java.util.Arrays;
import java.util.List;

public class HomePageActivity extends AppCompatActivity {

    ImageButton btnProfile, btnOrders, btnSupport, btnAbout;
    Button btnFilter, btnPark;
    EditText searchBar;

    private static final int AUTOCOMPLETE_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        searchBar = findViewById(R.id.search_bar);
        btnProfile= findViewById(R.id.btnProfile);
        btnOrders= findViewById(R.id.btnOrders);
        btnSupport= findViewById(R.id.btnSupport);
        btnAbout= findViewById(R.id.btnAbout);
        btnFilter= findViewById(R.id.btnFilter);
        btnPark= findViewById(R.id.btnPark);


        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(HomePageActivity.this, ProfileActivity.class);
                startActivity(i);
                finish();
            }
        });

        btnPark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(HomePageActivity.this, RentParkingActivity.class);
                startActivity(i);
                finish();
            }
        });

    }


}
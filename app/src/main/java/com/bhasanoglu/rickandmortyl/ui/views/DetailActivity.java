package com.bhasanoglu.rickandmortyl.ui.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bhasanoglu.rickandmortyl.R;
import com.squareup.picasso.Picasso;

import static com.bhasanoglu.rickandmortyl.ui.views.MainActivity.EXTRA_GENDER;
import static com.bhasanoglu.rickandmortyl.ui.views.MainActivity.EXTRA_LOCATION;
import static com.bhasanoglu.rickandmortyl.ui.views.MainActivity.EXTRA_NAME;
import static com.bhasanoglu.rickandmortyl.ui.views.MainActivity.EXTRA_SPECIES;
import static com.bhasanoglu.rickandmortyl.ui.views.MainActivity.EXTRA_STATUS;
import static com.bhasanoglu.rickandmortyl.ui.views.MainActivity.EXTRA_URL;


public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);                               //ContentView set

        Intent intent = getIntent();    //Intent get
        String imageUrl = intent.getStringExtra(EXTRA_URL);                     //Mainden paylaştığımız EXTRA_(DATA) verilerini çekme
        String name = intent.getStringExtra(EXTRA_NAME);
        String status = intent.getStringExtra(EXTRA_STATUS);
        String location = intent.getStringExtra(EXTRA_LOCATION);
        String species = intent.getStringExtra(EXTRA_SPECIES);
        String gender = intent.getStringExtra(EXTRA_GENDER);

        ImageView imageView = findViewById(R.id.image_view_detail);              // .XMLde gerekli yerlerin id üzerinden bulunup atanması
        TextView textViewName = findViewById(R.id.text_view_name_detail);
        TextView textViewStatus = findViewById(R.id.text_view_status_detail);
        TextView textViewLastKnown = findViewById(R.id.text_view_LastKnown_detail);
        TextView textViewSpecies = findViewById(R.id.text_view_Species_detail);
        TextView textViewGender = findViewById(R.id.text_view_Gender_detail);

        Picasso.with(this).load(imageUrl).fit().centerInside().into(imageView); //Picasso kütüphanesi
        textViewName.setText(name);                                             //Çekilen verilerin .XMLde gerekli yerlere bağlanması
        textViewStatus.setText("Status: " + status);
        textViewLastKnown.setText("Last Known Location: " + location);
        textViewSpecies.setText("Species: " + species);
        textViewGender.setText("Gender: " + gender);

        if(status.equals("Dead")){                                              //Status Durumuna göre renk ayarlama
            textViewStatus.setTextColor(Color.parseColor("#BB2817"));
        }
        else if(status.equals("Alive")){
            textViewStatus.setTextColor(Color.GREEN);
        }
        else{
            textViewStatus.setTextColor(Color.LTGRAY);
        }


    }
}
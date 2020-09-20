package com.bhasanoglu.rickandmortyl.ui.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bhasanoglu.rickandmortyl.R;
import com.bhasanoglu.rickandmortyl.data.ExampleItem;
import com.bhasanoglu.rickandmortyl.ui.adapters.ExampleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ExampleAdapter.OnItemClickListener {
    public static final String EXTRA_URL ="imageurl";
    public static final String EXTRA_NAME ="name";
    public static final String EXTRA_STATUS ="status";
    public static final String EXTRA_LOCATION = "location";
    public static final String EXTRA_GENDER = "gender";
    public static final String EXTRA_SPECIES = "species";


    private RecyclerView mRecyclerView;
    private ExampleAdapter mExampleAdapter;
    private ArrayList<ExampleItem> mExampleList;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view);                                    //recycler viewe ulaşım
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mExampleList = new ArrayList<>();                                                    //Datamız için arraylist yaratma

        mRequestQueue = Volley.newRequestQueue(this);                              //volley kütüphanesi aracılığı ile Request queue
        parseJSON();

    }
    private void parseJSON(){                                                             //Jsondan istenilen parçaları ayırabilmek ve Json arrayı içinden obje çıkartmak için parse metodu
        String url ="https://rickandmortyapi.com/api/character";                         // API Link
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {   //JsonObjectRequest -> json objesine ulaşım
                try {
                    JSONArray jsonArray = response.getJSONArray("results");        // Json objesi içindeki "results" isimli Json arrayine ulaşım

                    for(int i= 0; i < jsonArray.length();i++){                         // responsedan alınan array elemanlarının hepsine ulaşım
                        JSONObject result = jsonArray.getJSONObject(i);               // response boyutu kadar elemena ulaşıp her biri resulta konulacak

                        String name = result.getString("name");                //istenilen verileri "name" tagları aracalığı ile seçip çekme
                        String imageUrl = result.getString("image");
                        String status = result.getString("status");
                        String species = result.getString("species");
                        String gender = result.getString("gender");

                        JSONObject location = result.getJSONObject("location");   //jsonArray elemanları içinden jsonObject çekme
                        String locName = location.getString("name");        //JsonArrayinden çekilen JsonObjesinin içinden eleman çekme

                        mExampleList.add(new ExampleItem(imageUrl,name,status,locName,species,gender)); // Çektiğim verileri parametre olarak girdi alan mExampleList elemanı oluşturuyor Responsedan gelen tüm elemanlar için

                    }

                    mExampleAdapter = new ExampleAdapter(MainActivity.this, mExampleList);      //mExampleAdapter(adaptör olarak oluşturduğum eleman)'a mExampleListimizi gönderiyor -> main activity
                    mRecyclerView.setAdapter(mExampleAdapter);                //recyclerviewe set ediyor adaptoru
                    mExampleAdapter.setOnItemClickListener(MainActivity.this);   //ClickListener set

                } catch (JSONException e) {                                  //error handle
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {  //error handle
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);                                          //queuemuza requesti ekleme(.add metodu)
    }

    @Override
    public void onItemClick(int position) {   //tıklandıgında
        Intent detailIntent = new Intent(this, DetailActivity.class);
        ExampleItem clickedItem = mExampleList.get(position);                //pozisyon girdisi get edilir

        detailIntent.putExtra(EXTRA_URL, clickedItem.getmImageUrl());        //intent aracalığı ile haberleşme sağlanır EXTRA_(DATA) ve getm(Data) verileri DetailActivity sayfasına ulaştırılır.
        detailIntent.putExtra(EXTRA_NAME, clickedItem.getmName());
        detailIntent.putExtra(EXTRA_STATUS,clickedItem.getmStatus());
        detailIntent.putExtra(EXTRA_LOCATION,clickedItem.getmLocName());
        detailIntent.putExtra(EXTRA_SPECIES,clickedItem.getmSpecies());
        detailIntent.putExtra(EXTRA_GENDER,clickedItem.getmGender());


        startActivity(detailIntent);                                         // Activity başlatma
    }
}
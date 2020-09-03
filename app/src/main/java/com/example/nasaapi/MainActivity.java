package com.example.nasaapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    Button apod, search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apod = (Button) findViewById(R.id.apod);

        search = (Button) findViewById(R.id.search);

        apod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apodApi();
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchApi();
            }
        });


    }

    private void apodApi(){
        Intent intent = new Intent(this, apod.class);
        startActivity(intent);
    }

    private void searchApi(){
        Intent intent = new Intent(this, search.class);
        startActivity(intent);
    }
}

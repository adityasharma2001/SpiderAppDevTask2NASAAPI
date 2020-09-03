package com.example.nasaapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.URL;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class apod extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    ImageView image;

    TextView text;
    Button start, datePicker;
    String date;
    private NasaApi nasaApi;
    String img_url;

    String mtype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apod);
        image = (ImageView) findViewById(R.id.image);

        text = (TextView) findViewById(R.id.text_date);
        start = (Button) findViewById(R.id.start);
        datePicker = (Button) findViewById(R.id.datePicker);



        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");

            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.nasa.gov/planetary/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        nasaApi = retrofit.create(NasaApi.class);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retro(date);
            }
        });

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        if(c.after(Calendar.getInstance())){
            Toast.makeText(this,"Please Select A Day In Past", Toast.LENGTH_SHORT).show();
            return;
        }
        date = String.format("%d-%02d-%02d", year, month, dayOfMonth);
        text.setText(date);
    }

    public void retro(final String date){
        Call<Post> call = nasaApi.getPosts(date);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if(!response.isSuccessful()){
                    text.setText(date + " Code: " +response.code());
                    return;
                }
                Post post = response.body();
                img_url = post.getUrl();
                mtype = post.getMediaType();
                datePicker.setVisibility(View.INVISIBLE);
                start.setVisibility(View.INVISIBLE);
                text.setVisibility(View.INVISIBLE);

                Picasso.with(apod.this).load(img_url).fit().into(image, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {


                    }

                    @Override
                    public void onError() {

                        Toast.makeText(apod.this, "Unable to Load Check your Connection", Toast.LENGTH_SHORT).show();

                    }
                });


            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                text.setText(date + t.getMessage());
            }
        });
    }

}
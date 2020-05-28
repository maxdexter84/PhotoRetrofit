package com.maxdexter.newretrofit2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.maxdexter.newretrofit2.network.NetworkService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity{
    private ImageView mImageView;
    private Retrofit retrofit;
    private FlickrService service;
    int currentPage = 1;
    String term = "moscow";
    private boolean loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageView = findViewById(R.id.image);


        NetworkService.getInstance().loadMore(1,"moscow");
    }


}

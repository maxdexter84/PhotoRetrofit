package com.maxdexter.newretrofit2.network;

import android.util.Log;

import com.maxdexter.newretrofit2.FlickrService;
import com.maxdexter.newretrofit2.Photo;
import com.maxdexter.newretrofit2.Photos;
import com.maxdexter.newretrofit2.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService implements Callback<Result> {
    Retrofit retrofit;
    private FlickrService service;
    public static NetworkService instance;
    private int currentPage;
    private String term;
    private boolean loading;

    public static NetworkService getInstance() {
        if(instance == null){
            instance = new NetworkService();
        }
        return instance;
    }

    public NetworkService() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://www.flickr.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(FlickrService.class);

    }


    public void loadMore(int page, String search) {
        loading = true;
        Call<Result> call = service.search(
                "flickr.photos.search",
                "6c8409b814c27833947a1f3fb4172023",
                search,
                "json",
                1,
                page
        );
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<Result> call, Response<Result> response) {
        Result result = response.body();
        if(result.getStat().equals("ok")){
            Photos photos = result.getPhotos();
            for(Photo p : photos.getPhoto()){
                Log.d("happy",p.getTitle());
            }
        }

    }

    @Override
    public void onFailure(Call<Result> call, Throwable t) {

    }
}
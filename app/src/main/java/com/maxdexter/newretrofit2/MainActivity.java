package com.maxdexter.newretrofit2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity implements Callback<Result>{
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

        retrofit = new Retrofit.Builder()
                .baseUrl("https://www.flickr.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(FlickrService.class);
        
        startOver();

    }

    private void startOver() {
       currentPage = 1;
       
        loadMore(currentPage,term);
    }
    //https://www.flickr.com/services/rest/?
    // method=flickr.photos.search
    // &api_key=6c8409b814c27833947a1f3fb4172023
    // &text=moscow
    // &format=json
    // &nojsoncallback=1
    // &api_sig=b307c5559e87e67206668e8d0ff20c39

    private void loadMore(int page, String search) {
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
        Log.d("happy",t.getMessage());
    }
}

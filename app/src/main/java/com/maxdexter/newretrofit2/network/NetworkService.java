package com.maxdexter.newretrofit2.network;

import android.app.Application;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.maxdexter.newretrofit2.FlickrService;
import com.maxdexter.newretrofit2.database.DatabaseInstance;
import com.maxdexter.newretrofit2.pogo.Image;
import com.maxdexter.newretrofit2.pogo.ImageBox;
import com.maxdexter.newretrofit2.pogo.Photo;
import com.maxdexter.newretrofit2.pogo.Photos;
import com.maxdexter.newretrofit2.pogo.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService extends AppCompatActivity implements Callback<Result> {
    Retrofit retrofit;
    private FlickrService service;
    public static NetworkService instance;
    private int currentPage;
    private String term;
    boolean loading;
    MutableLiveData<Boolean> mLiveData = new MutableLiveData<>();

    public void setLiveData(MutableLiveData<Boolean> liveData) {
        mLiveData = liveData;
    }

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

    public MutableLiveData<Boolean> getLiveData() {
        return mLiveData;
    }

    @Override
    public void onResponse(Call<Result> call, Response<Result> response) {
        Result result = response.body();
        if(result.getStat().equals("ok")){
            Photos photos = result.getPhotos();
            for(Photo p : photos.getPhoto()){
                Log.d("happy",p.getTitle());
              String url = createUrl(p);
                Image image = new Image();
                image.setUrl(url);
                ImageBox.getImageBox().setImages(image);

            }
            loading = true;
            mLiveData.setValue(loading);

        }

    }

    @Override
    public void onFailure(Call<Result> call, Throwable t) {

    }
    private static String createUrl(Photo p) {
        // Сервисная функция для получения URL картинки по объекту
        // Подробности https://www.flickr.com/services/api/misc.urls.html
        //Log.d("happy", url);
        return String.format(
                "https://farm%s.staticflickr.com/%s/%s_%s_q.jpg",
                p.getFarm(),
                p.getServer(),
                p.getId(),
                p.getSecret()
        );
    }
}

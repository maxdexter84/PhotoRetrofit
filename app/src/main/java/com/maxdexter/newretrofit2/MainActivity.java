package com.maxdexter.newretrofit2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.maxdexter.newretrofit2.database.DatabaseInstance;
import com.maxdexter.newretrofit2.network.NetworkService;
import com.maxdexter.newretrofit2.pogo.Image;
import com.maxdexter.newretrofit2.pogo.ImageBox;


import java.util.List;

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

        DatabaseInstance.getDatabaseInstance(this);
        NetworkService.getInstance().loadMore(1,"moscow");
        LiveData<Boolean> getLive = NetworkService.getInstance().getLiveData();
        getLive.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                loading = aBoolean;
                if(loading){
                    setImageView();
                }
            }
        });

    }

    public void setImageView(){
       List<Image> urlList = ImageBox.getImageBox().getImages();

        //DatabaseInstance.getDatabaseInstance(this).getDatabase().mPhotoDAO().insert();

    }


}

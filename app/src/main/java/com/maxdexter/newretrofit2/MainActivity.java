package com.maxdexter.newretrofit2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;


import com.google.android.material.textfield.TextInputEditText;
import com.maxdexter.newretrofit2.adapter.ImageAdapter;
import com.maxdexter.newretrofit2.database.DatabaseInstance;
import com.maxdexter.newretrofit2.network.NetworkService;
import com.maxdexter.newretrofit2.pogo.Image;


import java.util.List;

import retrofit2.Retrofit;



public class MainActivity extends AppCompatActivity{
    private ImageView mImageView;
    private Retrofit retrofit;
    private FlickrService service;
    int currentPage = 1;
    String term = "moscow";
    private boolean loading;
    ImageAdapter mImageAdapter;
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageView = findViewById(R.id.image);
        TextInputEditText textInputEditText = findViewById(R.id.edit_text);
        initButton(textInputEditText);


        DatabaseInstance.getDatabaseInstance(this);
        loadPhoto(currentPage,term);

    }

    private void initButton(TextInputEditText textInputEditText) {
        Button button = findViewById(R.id.search_button);
        button.setOnClickListener(v -> {
            String query = textInputEditText.getText().toString();
            loadPhoto(currentPage,query);
        });
    }

    private void loadPhoto(int page,String search) {
        NetworkService.getInstance().loadMore(page,search);
        LiveData<Boolean> getLive = NetworkService.getInstance().getLiveData();
        getLive.observe(this, aBoolean -> {
            loading = aBoolean;
            if(loading){
                setImageView();
                loading = false;
            }

        });
    }

    public void setImageView(){
       List<Image> list = DatabaseInstance.getDatabaseInstance(this).getDatabase().mPhotoDAO().getAll();
       mRecyclerView = findViewById(R.id.recycler);
        mImageAdapter = new ImageAdapter(list);
        mRecyclerView.setAdapter(mImageAdapter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),6));


    }


}

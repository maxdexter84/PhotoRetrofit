package com.maxdexter.newretrofit2;

import com.maxdexter.newretrofit2.pogo.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FlickrService {
    //https://www.flickr.com/services/rest/?
    // method=flickr.photos.search
    // &api_key=6c8409b814c27833947a1f3fb4172023
    // &text=moscow
    // &format=json
    // &nojsoncallback=1
    // &api_sig=b307c5559e87e67206668e8d0ff20c39
    @GET("/services/rest/")
    Call<Result> search(
            @Query("method")   String method,
            @Query("api_key")  String key,
            @Query("text")     String text,
            @Query("format")   String format,
            @Query("nojsoncallback") int nojsoncallback,
            @Query("page")      int page);
}

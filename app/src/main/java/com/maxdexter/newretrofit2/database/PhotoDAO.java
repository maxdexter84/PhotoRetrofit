package com.maxdexter.newretrofit2.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.maxdexter.newretrofit2.pogo.Image;


import java.util.List;

@Dao
public interface PhotoDAO {
    @Query("SELECT * FROM Image")
    List<Image> getAll();
    @Query("SELECT * FROM image WHERE url = :url")
    Image getByUrl(String url);

    @Query("SELECT url FROM Image")
    List<String> getAllUrl();

    @Insert
    void insert(Image photo);

    @Update
    void update(Image photo);

    @Delete
    void delete(Image photo);
}

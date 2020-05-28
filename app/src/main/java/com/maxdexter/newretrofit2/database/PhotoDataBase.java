package com.maxdexter.newretrofit2.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.maxdexter.newretrofit2.pogo.Image;

@Database(entities = {Image.class},version = 1,exportSchema = false)
public abstract class PhotoDataBase extends RoomDatabase {
    public abstract PhotoDAO mPhotoDAO();
}

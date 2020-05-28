package com.maxdexter.newretrofit2.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;

import com.maxdexter.newretrofit2.pogo.Image;

import java.util.ArrayList;
import java.util.List;

public class DatabaseInstance {
   private static DatabaseInstance mDatabaseInstance;
   private PhotoDataBase database;
    List<Image>allPhoto = new ArrayList<>();
    public static DatabaseInstance getDatabaseInstance(Context context) {
        if(mDatabaseInstance == null){
            mDatabaseInstance = new DatabaseInstance(context);
        }
        return mDatabaseInstance;
    }

    private DatabaseInstance(Context context) {
        database = Room.databaseBuilder(context, PhotoDataBase.class, "photoDB")
                .allowMainThreadQueries()
                .build();
    }

    public PhotoDataBase getDatabase() {
        return database;
    }
}

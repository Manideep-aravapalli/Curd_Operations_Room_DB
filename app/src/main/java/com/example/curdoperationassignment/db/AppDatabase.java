package com.example.curdoperationassignment.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.curdoperationassignment.db.entity.UserDetails;

@Database(entities = {UserDetails.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();

}

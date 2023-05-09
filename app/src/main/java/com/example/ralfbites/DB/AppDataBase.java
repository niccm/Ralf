package com.example.ralfbites.DB;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.ralfbites.Item;
import com.example.ralfbites.User;

@Database(entities = {User.class, Item.class},version = 2)
public abstract class AppDataBase extends RoomDatabase {
    public static final String DATABASE_NAME = "RalfBites.db";
    public static final String USER_TABLE = "user_table";

    public static final String ITEM_TABLE = "item_table";


    private static volatile AppDataBase instance;
    private static final Object LOCK = new Object();

    public static AppDataBase getInstance(Context context) {
        if(instance == null){
            synchronized (LOCK){
                instance = Room.databaseBuilder(context.getApplicationContext(),AppDataBase.class,DATABASE_NAME).build();
            }
        }
        return instance;
    }

    public abstract RalfBitesDAO RalfBitesDAO();
}

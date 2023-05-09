package com.example.ralfbites.DB;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.ralfbites.Item;
import com.example.ralfbites.User;

@Dao
public interface RalfBitesDAO {
    @Insert
    void insert(User... users);

    @Update
    void update(User... users);

    @Delete
    void delete(User... users);

    @Query("SELECT username FROM " +  AppDataBase.USER_TABLE + " WHERE username = :Username")
    String checkUsername(String Username);

    @Query("SELECT password FROM " +  AppDataBase.USER_TABLE + " WHERE password = :Password")
    String checkPassword(String Password);

    @Query("SELECT password FROM " +  AppDataBase.USER_TABLE + " WHERE username = :Username")
    String getPasswordFromUsername(String Username);

    @Query("SELECT isAdmin FROM " + AppDataBase.USER_TABLE +  " WHERE username = :Username")
    Boolean checkAdmin(String Username);

    @Query("SELECT UserID FROM " +  AppDataBase.USER_TABLE + " WHERE username = :Username")
    int getUserIDByUsername(String Username);

    @Query("SELECT * FROM " +  AppDataBase.USER_TABLE + " WHERE username = :Username")
    User getUserByUsername(String Username);



    @Insert
    void insert(Item... items);

    @Update
    void update(Item... items);

    @Delete
    void delete(Item... items);
}

package com.example.ralfbites;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.ralfbites.DB.AppDataBase;

@Entity(tableName = AppDataBase.ITEM_TABLE)
public class Item {

    @PrimaryKey(autoGenerate = true)
    private int ItemID;

    private String name;
    private String description;
    private double price;
    private int orders;



    public Item(String name, String description, double price, int orders) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.orders = orders;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getOrders() {
        return orders;
    }

    public void setOrders(int orders) {
        this.orders = orders;
    }

    public int getItemID() {
        return ItemID;
    }

    public void setItemID(int itemID) {
        ItemID = itemID;
    }
}

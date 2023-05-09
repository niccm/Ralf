package com.example.ralfbites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ralfbites.DB.AppDataBase;
import com.example.ralfbites.DB.RalfBitesDAO;
import com.example.ralfbites.databinding.ActivityAddItemBinding;
import com.example.ralfbites.databinding.ActivityLandingPageBinding;

public class AddItem extends AppCompatActivity {
    RalfBitesDAO ralfBitesDAO;
    ActivityAddItemBinding addItemBinding;

    Button addItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        addItemBinding = ActivityAddItemBinding.inflate(getLayoutInflater());
        View view = addItemBinding.getRoot();
        setContentView(view);

        addItem = addItemBinding.addMenuItemButtonSubmit;

        ralfBitesDAO = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.DATABASE_NAME)
                .allowMainThreadQueries()
                .build()
                .RalfBitesDAO();



        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }



























    public static Intent getIntent(Context context){
        Intent intent = new Intent(context, AddItem.class);
        return intent;
    }
}
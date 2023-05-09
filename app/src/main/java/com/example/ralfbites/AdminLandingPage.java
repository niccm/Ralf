package com.example.ralfbites;

import static com.example.ralfbites.LoginActivity.pass;
import static com.example.ralfbites.LoginActivity.prefs;
import static com.example.ralfbites.LoginActivity.user;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.ralfbites.DB.AppDataBase;
import com.example.ralfbites.DB.RalfBitesDAO;
import com.example.ralfbites.databinding.ActivityLandingPageBinding;

public class AdminLandingPage extends AppCompatActivity{

    Button totalOrders;
    Button changePrice;
    Button addItem;
    Button logoutButton;
    Button deleteButton;
    RalfBitesDAO ralfBitesDAO;

    ActivityLandingPageBinding landingPageBinding;

    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);



        landingPageBinding = ActivityLandingPageBinding.inflate(getLayoutInflater());
        View view = landingPageBinding.getRoot();
        setContentView(view);




        deleteButton = landingPageBinding.deleteUserButton;
        logoutButton = landingPageBinding.logoutButtonAdmin;
        addItem = landingPageBinding.addMenuItemButton;


        ralfBitesDAO = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.DATABASE_NAME)
                .allowMainThreadQueries()
                .build()
                .RalfBitesDAO();



        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = AddItem.getIntent(getApplicationContext());
                startActivity(intent);
            }
        });



        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = deleteUser.getIntent(getApplicationContext());
                startActivity(intent);
            }
        });


        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPref = getSharedPreferences(prefs, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                String nullUser = null;
                String nullPass = null;
                editor.putString(user, nullUser);
                editor.putString(pass, nullPass);
                editor.apply();


                Intent intent = MainActivity.getIntent(getApplicationContext());
                startActivity(intent);
            }
        });


    }












    public static Intent getIntent(Context context){
        Intent intent = new Intent(context, AdminLandingPage.class);
        return intent;
    }
}
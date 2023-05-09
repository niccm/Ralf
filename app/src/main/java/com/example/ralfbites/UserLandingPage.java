package com.example.ralfbites;

import static com.example.ralfbites.LoginActivity.pass;
import static com.example.ralfbites.LoginActivity.prefs;
import static com.example.ralfbites.LoginActivity.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ralfbites.DB.AppDataBase;
import com.example.ralfbites.DB.RalfBitesDAO;
import com.example.ralfbites.databinding.ActivityUserLandingPageBinding;

public class UserLandingPage extends AppCompatActivity {

    SharedPreferences sharedPref;
    Button logoutButton;
    Button cartButton;

    ActivityUserLandingPageBinding userLandingPageBinding;

    RalfBitesDAO ralfBitesDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_landing_page);


        userLandingPageBinding = ActivityUserLandingPageBinding.inflate(getLayoutInflater());
        View view = userLandingPageBinding.getRoot();

        setContentView(view);

        logoutButton = userLandingPageBinding.logoutButton;
        cartButton = userLandingPageBinding.cartButton;


        ralfBitesDAO = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.DATABASE_NAME)
                .allowMainThreadQueries()
                .build()
                .RalfBitesDAO();



        sharedPref = getSharedPreferences(prefs, Context.MODE_PRIVATE);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



    }















    public static Intent getIntent(Context context){
        Intent intent = new Intent(context, UserLandingPage.class);
        return intent;
    }
}
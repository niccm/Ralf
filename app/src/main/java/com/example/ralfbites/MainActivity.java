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
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.ralfbites.DB.AppDataBase;
import com.example.ralfbites.DB.RalfBitesDAO;
import com.example.ralfbites.databinding.ActivityMainBinding;

/**
 * @author Nicolas Mederos
 * @since 18 April 2023
 * Starting the project
 */

public class MainActivity extends AppCompatActivity {

    RalfBitesDAO ralfBitesDAO;
    Button loginButton;
    Button signupButton;

    ActivityMainBinding mainBinding;

    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //finish Logout
        //Didnt make a bran
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = mainBinding.getRoot();

        setContentView(view);
        loginButton = mainBinding.loginButton;
        signupButton = mainBinding.signupButton;

        sharedPref = getSharedPreferences(prefs, Context.MODE_PRIVATE);

        ralfBitesDAO = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.DATABASE_NAME)
                .allowMainThreadQueries()
                .build()
                .RalfBitesDAO();

        String u = sharedPref.getString(user,"");
        String p = sharedPref.getString(pass,"");
        String tempUser = ralfBitesDAO.checkUsername(u);
        String tempPass = ralfBitesDAO.checkPassword(p);
        Boolean check = ralfBitesDAO.checkAdmin(u);

        User testadminCheck = ralfBitesDAO.getUserByUsername("admin1");
        User testUserCheck = ralfBitesDAO.getUserByUsername("testuser1");

        if(testUserCheck == null && testadminCheck == null){
            String predefUsername = "admin1";
            String predefPass = "admin1";
            boolean admin = true;
            User yo = new User(predefUsername,predefPass,admin);
            ralfBitesDAO.insert(yo);

            String predefUsername1 = "testuser1";
            String predefPass1 = "testuser1";
            boolean admin1 = false;
            User yo1 = new User(predefUsername1,predefPass1,admin1);
            ralfBitesDAO.insert(yo1);
        }

        if(u.equals(tempUser) && p.equals(tempPass) && check == false) {
            Intent intent = UserLandingPage.getIntent(getApplicationContext());
            startActivity(intent);
        }else if (u.equals(tempUser) && p.equals(tempPass) && check == true) {
            Intent intent = AdminLandingPage.getIntent(getApplicationContext());
            startActivity(intent);
        }

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = LoginActivity.getIntent(getApplicationContext());
                startActivity(intent);
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = SignUpActivity.getIntent(getApplicationContext());
                startActivity(intent);

            }
        });





    }

    public static Intent getIntent(Context context){
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

}
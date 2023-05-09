package com.example.ralfbites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ralfbites.DB.AppDataBase;
import com.example.ralfbites.DB.RalfBitesDAO;

public class LoginActivity extends AppCompatActivity {

    RalfBitesDAO ralfBitesDAO;
    SharedPreferences sharedPref;
    public static final String prefs = "MyPrefs" ;
    public static final String user = "userKey";
    public static final String pass = "passKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText username;
        EditText passWord;
        Button loginSubmit;

        username =(EditText) findViewById(R.id.usernameInputLogin);
        passWord =(EditText)findViewById(R.id.editTextTextPassword);
        loginSubmit=(Button)findViewById(R.id.LoginButtonLoginPage);



        sharedPref = getSharedPreferences(prefs, Context.MODE_PRIVATE);

        ralfBitesDAO = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.DATABASE_NAME)
                .allowMainThreadQueries()
                .build()
                .RalfBitesDAO();

        loginSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Log.d("rat", "AAA");
                String u  = username.getText().toString();
                String p  = passWord.getText().toString();
                Log.d("rat", "BBB");
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString(user, u);
                editor.putString(pass, p);
                editor.apply();

                Log.d("rat", "CCC");
                Log.d("rat", u);
                Log.d("rat", p);

              String tempUser = ralfBitesDAO.checkUsername(u);
              String tempPass = ralfBitesDAO.checkPassword(p);
              Boolean check = ralfBitesDAO.checkAdmin(u);
                if(u.equals(tempUser) && p.equals(tempPass) && check == false) {
                    Intent intent = UserLandingPage.getIntent(getApplicationContext());
                    startActivity(intent);
                } else if (u.equals(tempUser) && p.equals(tempPass) && check == true) {
                    Intent intent = AdminLandingPage.getIntent(getApplicationContext());
                    startActivity(intent);
                }
            }
        });
    }



    public static Intent getIntent(Context context){
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }





}
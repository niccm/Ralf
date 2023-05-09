package com.example.ralfbites;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.ralfbites.DB.AppDataBase;
import com.example.ralfbites.DB.RalfBitesDAO;
import com.example.ralfbites.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {

    TextView ErrorMessage;
    EditText usernameInput;
    EditText passwordInput;
    EditText passwordCheckInput;

    Button signupSubmit;
    ActivitySignUpBinding signupBinding;

    RalfBitesDAO ralfBitesDAO;

    String checkedUsername;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        signupBinding = ActivitySignUpBinding.inflate(getLayoutInflater());
        View view = signupBinding.getRoot();

        setContentView(view);
        ErrorMessage = signupBinding.sameUsernameErrorTextView;
        usernameInput = signupBinding.usernameInputSignUp;
        passwordInput = signupBinding.passwordInputSignUp;
        passwordCheckInput = signupBinding.passwordInputCheck;
        signupSubmit = signupBinding.signupButtonDatabase;

        ralfBitesDAO = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.DATABASE_NAME)
                .allowMainThreadQueries()
                .build()
                .RalfBitesDAO();
        signupSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("rat", "idiot");
                String passwordInputs;
                String passwordInputChecks;
                String usernames;

                usernames = usernameInput.getText().toString();
                passwordInputs = passwordInput.getText().toString();
                passwordInputChecks = passwordCheckInput.getText().toString();
                Log.d("usernames", usernameInput.getText().toString());


                //Checks if username entered is already in the database
                checkedUsername = ralfBitesDAO.checkUsername(usernames);
                if(usernames.equals(checkedUsername)) {

                    ErrorMessage.setVisibility(View.VISIBLE);
                    Log.d("Why","aaaaaa");

                }else {
                    ErrorMessage.setVisibility(View.INVISIBLE);
                    if ((!usernames.equals("")) && (passwordInputs.equals(passwordInputChecks))) {
                        boolean admin = false;
                        User user = new User(usernames, passwordInputs, admin);
                        ralfBitesDAO.insert(user);
                        Log.d("Insertion", "Inserting");

                        Intent intent = UserLandingPage.getIntent(getApplicationContext());
                        startActivity(intent);
                    }
                }
            }});
   }






    public static Intent getIntent(Context context){
        Intent intent = new Intent(context, SignUpActivity.class);
        return intent;
    }
}
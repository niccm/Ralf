package com.example.ralfbites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.ralfbites.DB.AppDataBase;
import com.example.ralfbites.DB.RalfBitesDAO;
import com.example.ralfbites.databinding.ActivityDeleteUserBinding;


public class deleteUser extends AppCompatActivity {

    EditText usernameInput;
    Button deleteUserButton;
    ActivityDeleteUserBinding deleteUserBinding;

    RalfBitesDAO ralfBitesDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_user);

        deleteUserBinding = ActivityDeleteUserBinding.inflate(getLayoutInflater());
        View view = deleteUserBinding.getRoot();
        setContentView(view);

        ralfBitesDAO = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.DATABASE_NAME)
                .allowMainThreadQueries()
                .build()
                .RalfBitesDAO();

        deleteUserButton = deleteUserBinding.deleteButtonDeletePage;
        usernameInput = deleteUserBinding.usernameDeletePage;

        deleteUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("checkButton", "works");
                String userName = usernameInput.getText().toString();
                if(userName != ""){
                    String tempPassword = ralfBitesDAO.getPasswordFromUsername(userName);
                    String check = ralfBitesDAO.checkPassword(tempPassword);
                    Log.d("temp", tempPassword);
                    Log.d("check", check);
                    if(tempPassword.equals(check)){
                        User fetchedUser = ralfBitesDAO.getUserByUsername(userName);
                        Log.d("user", fetchedUser.toString());
                        ralfBitesDAO.delete(fetchedUser);
                        Log.d("delete check", "Deleted");
                        //TODO: Add notification to tell user they did it
                        Intent intent = AdminLandingPage.getIntent(getApplicationContext());
                        startActivity(intent);
                    }
                }
            }
        });



    }

























    public static Intent getIntent(Context context){
        Intent intent = new Intent(context, deleteUser.class);
        return intent;
    }
}
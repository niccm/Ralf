package com.example.ralfbites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class cartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
    }

























    public static Intent getIntent(Context context){
        Intent intent = new Intent(context, cartActivity.class);
        return intent;
    }
}
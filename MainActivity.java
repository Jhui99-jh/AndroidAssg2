package com.firebase1.prgassg2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.firebase.client.Firebase;

public class MainActivity extends AppCompatActivity {

    //Declare Variable
    Button hp_login, hp_register;
    public static final String Firebase_Server_URL = "https://myproject-e278a.firebaseio.com/";
    Firebase firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //adding MainActivity context into Firebase context
        Firebase.setAndroidContext(MainActivity.this);
        firebase = new Firebase(Firebase_Server_URL);

        hp_login = (Button)findViewById(R.id.button);
        hp_register = (Button)findViewById(R.id.button2);

        //hp_login.setOnClickListener();

        hp_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Register.class);
                startActivity(intent);
            }
        });

    }
}
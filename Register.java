package com.firebase1.prgassg2;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    //Declare Variable
    Button r_register;
    EditText r_fname, r_lname, r_email, r_tel, r_pwd, r_cpwd;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        r_fname = (EditText) findViewById(R.id.fName);
        r_lname = (EditText) findViewById(R.id.lName);
        r_email = (EditText) findViewById(R.id.emailAdd);
        r_tel = (EditText) findViewById(R.id.tel);
        r_pwd = (EditText) findViewById(R.id.pwd);
        r_cpwd = (EditText) findViewById(R.id.cpwd);
        r_register = (Button) findViewById(R.id.rbtnR);
        fAuth = FirebaseAuth.getInstance();

        //check if user exists
        if (fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }


        r_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fname = r_fname.getText().toString();
                String lname = r_lname.getText().toString();
                String email = r_email.getText().toString();
                String password = r_pwd.getText().toString();
                String confirm_password = r_cpwd.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    r_email.setError("Email is required.");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    r_pwd.setError("Passord is required.");
                    return;
                }
                if (password.length() < 6) {
                    r_pwd.setError("Password must be more than 6 characters");
                    return;
                }

                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Register.this, "User Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {
                            Toast.makeText(Register.this, "Error Occur", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
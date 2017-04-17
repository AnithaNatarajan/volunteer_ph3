package com.example.anitharaj.volunteer_ph3;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static com.example.anitharaj.volunteer_ph3.R.id.buttonLogin;

public class Login_Register_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__register_);


    }


    public void OnclickLogin(View v){
        System.out.print("Onclick Login");
        Intent intent = new Intent(Login_Register_Activity.this, Login_Activity.class);
        startActivity(intent);
        Toast.makeText(Login_Register_Activity.this,"Login page", Toast.LENGTH_LONG).show();
        finish();

    }
    public void OnclickRegister(View v){
    // take to user register page
            System.out.print("Onclick Register");
            Intent intent = new Intent(Login_Register_Activity.this, Register_Activity.class);
            startActivity(intent);
            Toast.makeText(Login_Register_Activity.this,"Register page", Toast.LENGTH_LONG).show();
            finish();
        }
}



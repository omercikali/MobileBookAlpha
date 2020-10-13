package com.example.mobilebookalpha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login_page extends AppCompatActivity {

    Button withphonenumber,withgoogleaccount,loginbutton;
    EditText emailet,passwordet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        emailet=findViewById(R.id.emailet);
        passwordet=findViewById(R.id.passwordet);
        withphonenumber = findViewById(R.id.withphonenumber);
        withgoogleaccount=findViewById(R.id.withgooglebtn);


        withphonenumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login_page.this,Sing_up_page.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
            }
        });
    }

    public void withgoogle(View view){


        Intent intent=new Intent(Login_page.this,With_google_sing_up.class);
        startActivity(intent);



    }
}
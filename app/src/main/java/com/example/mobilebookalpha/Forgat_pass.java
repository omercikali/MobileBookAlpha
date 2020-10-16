package com.example.mobilebookalpha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forgat_pass extends AppCompatActivity {
    Button resetpassword;
    EditText resetemailet;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgat_pass);

        auth = FirebaseAuth.getInstance();

        resetemailet = findViewById(R.id.resetemailet);
        resetpassword = findViewById(R.id.resetpassword);


        resetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String resetemailStr = resetemailet.getText().toString();
                auth.sendPasswordResetEmail(resetemailStr)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "mail adresinize sıfırlama linki göderildi", Toast.LENGTH_LONG).show();
                                } else
                                    Toast.makeText(getApplicationContext(), "beklenmedik bir hata oluştu", Toast.LENGTH_LONG).show();

                            }
                        });
                finish();
            }
        });
    }
}
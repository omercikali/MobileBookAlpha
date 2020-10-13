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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class With_google_sing_up extends AppCompatActivity {
    EditText emailet, passwordet;
    Button enterbt;
    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_google_sing_up);

        enterbt = findViewById(R.id.button3);
        emailet = findViewById(R.id.emailEt);
        passwordet = findViewById(R.id.passwordEt);


        enterbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String usernamestr = emailet.getText().toString();
                String passwordstr = passwordet.getText().toString();
                singUpFirebase(usernamestr, passwordstr);


            }
        });

    }

    public void singUpFirebase(String username, String userpassword) {

        auth.createUserWithEmailAndPassword(username, userpassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(getApplicationContext(), "hesap başarılı bir şekilde oluşturuldu", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(With_google_sing_up.this, Login_page.class);
                            startActivity(intent);
                            finish();
                            overridePendingTransition(R.anim.anim_out, R.anim.anim_in);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getApplicationContext(), "hesap oluşturulurken bir hata oluştu lütfen tekrar deneyiniz", Toast.LENGTH_LONG).show();

                        }

                        // ...
                    }
                });


    }
}
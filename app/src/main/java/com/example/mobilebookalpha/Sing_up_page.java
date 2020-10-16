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
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import javax.security.auth.callback.CallbackHandler;

public class Sing_up_page extends AppCompatActivity {
   private EditText gelenkodet, telefonnumberet;
   private Button koduiste, dogrula;
   private String codesend;
   private FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up_page);

        gelenkodet = findViewById(R.id.turncodeet);
        telefonnumberet = findViewById(R.id.editTextPhone);
        koduiste = findViewById(R.id.koduistebtn);
        dogrula = findViewById(R.id.dogrula);


        koduiste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userphonenumber = telefonnumberet.getText().toString();
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        userphonenumber, 60, TimeUnit.SECONDS,
                        Sing_up_page.this, mCallbacks);
            }
        });

        dogrula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                singWithphonecode();
            }
        });
    }

    private void singWithphonecode() {

        String userEnterCode = gelenkodet.getText().toString();
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codesend, userEnterCode);
        singinWithPhoneAuthCredential(credential);
    }

    private void singinWithPhoneAuthCredential(PhoneAuthCredential credential) {
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent i = new Intent(Sing_up_page.this, Login_page.class);
                            startActivity(i);
                            finish();

                        } else {
                            Toast.makeText(getApplicationContext(), "girdiğiniz kod hatalı", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }


    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks =
            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                @Override
                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                }

                @Override
                public void onVerificationFailed(@NonNull FirebaseException e) {

                }

                @Override
                public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                    super.onCodeSent(s, forceResendingToken);

                    codesend = s;

                }
            };

}
package com.example.mobilebookalpha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class With_Phone_Number extends AppCompatActivity {
    private Button kodualamadim, koduiste, dogrula;
    private EditText telefonEt, codeEt;
    private ProgressBar w_progress;
    private String codesendStr;
    private FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with__phone__number);
        kodualamadim = findViewById(R.id.W_kodualamadim);
        koduiste = findViewById(R.id.W_koduiste);
        dogrula = findViewById(R.id.W_dogrula);
        telefonEt = findViewById(R.id.W_phoneEt);
        codeEt = findViewById(R.id.W_codeEt);
        w_progress = findViewById(R.id.W_progres);

        //visibility
        w_progress.setVisibility(View.INVISIBLE);
        kodualamadim.setVisibility(View.INVISIBLE);
        dogrula.setVisibility(View.INVISIBLE);
        codeEt.setVisibility(View.INVISIBLE);


    }


    protected void W_kodualamadimcl(View view) {


    }

    protected void W_koduistecl(View view) {

        String telefonStr = telefonEt.getText().toString();
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                telefonStr, 120, TimeUnit.SECONDS, With_Phone_Number.this, mcallbacks);


    }

    protected void W_dogrulacl(View view) {

        SingWithphonecode();
    }

    protected void SingWithphonecode() {
        String userentercode = codeEt.getText().toString();
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codesendStr, userentercode);
        singingwithcreditinal(credential);
    }

    protected void singingwithcreditinal(PhoneAuthCredential credential) {

        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent i=new Intent(With_Phone_Number.this,MainActivity.class);
                            startActivity(i);
                            finish();
                        } else {

                        }
                    }
                });
    }

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mcallbacks =
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
                    codesendStr = s;
                }
            };
}
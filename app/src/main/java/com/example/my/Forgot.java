package com.example.my;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forgot extends AppCompatActivity {
Button forpass;
EditText email;

FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        forpass=findViewById(R.id.forpass);
        email=findViewById(R.id.femail);
        auth=FirebaseAuth.getInstance();
        forpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetpassword();
            }
        });

    }

    private void resetpassword() {
        String txtemail = email.getText().toString();

        if (txtemail.isEmpty() ) {
            email.setError("Enter your Email id");
            email.requestFocus();
            return;

        }
        if( !Patterns.EMAIL_ADDRESS.matcher(txtemail).matches())
        {
            email.setError("Enter the correct Email id");
            email.requestFocus();
            return;
        }
       // progressBar.setVisibility(View.VISIBLE);
        auth.sendPasswordResetEmail(txtemail).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(Forgot.this, "Check your email to reset your password", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(Forgot.this, "Try again.something went wrong", Toast.LENGTH_SHORT).show();

            }
        });
    }

}
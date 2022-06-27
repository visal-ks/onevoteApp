package com.example.my;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import java.util.concurrent.Executor;

public class Fingerprint extends AppCompatActivity {


   BiometricPrompt biometricPrompt;
    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fingerprint);
        TextView textmsg = findViewById(R.id.text_msg);
        Button btn = findViewById(R.id.Loginbtn);

        BiometricManager biometricManager= BiometricManager.from(this);
        switch(biometricManager.canAuthenticate())
        {
            case BiometricManager.BIOMETRIC_SUCCESS:
                textmsg.setText("You can use fingerprint");
                break;
            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                textmsg.setText("Fingerprint facility unavilable ");
                btn.setVisibility(View.GONE);
                break;
             case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                 textmsg.setText("You cant use  the fingerprint currently");
                 btn.setVisibility(View.GONE);

                 break;
            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                textmsg.setText("YOu dont have any fingerprint saved.pLease update and try again");
                btn.setVisibility(View.GONE);
                break;


        }

        Executor executor= ContextCompat.getMainExecutor(this);
         biometricPrompt = new androidx.biometric.BiometricPrompt(Fingerprint.this, executor, new androidx.biometric.BiometricPrompt.AuthenticationCallback() {
             @Override
             public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                 super.onAuthenticationError(errorCode, errString);
                 Toast.makeText(Fingerprint.this, "Error in finding you", Toast.LENGTH_SHORT).show();
             }

             @Override
             public void onAuthenticationFailed() {
                 super.onAuthenticationFailed();
                 Toast.makeText(Fingerprint.this, "Error in finding you", Toast.LENGTH_SHORT).show();
             }

             @Override
             public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                 super.onAuthenticationSucceeded(result);
                 Toast.makeText(Fingerprint.this, "Success", Toast.LENGTH_SHORT).show();
                 Intent intent=new Intent(Fingerprint.this,Userseecandidates.class);
                 intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                 startActivity(intent);
             }
         });

         BiometricPrompt.PromptInfo promptInfo= new BiometricPrompt.PromptInfo.Builder()
                 .setTitle("LOGIN")
                 .setDescription("Use your fingerprint to login")
                 .setNegativeButtonText("Cancel")
                 .build();






    }
}
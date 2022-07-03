package com.example.my;

import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class SplashScreen extends AppCompatActivity {
    FirebaseAuth fauth;
    FirebaseFirestore fstore;
    Handler handler;
   // @RequiresApi(api = Build.VERSION_CODES.P)
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        try{
            this.getSupportActionBar().hide();
        }
        catch(NullPointerException e){}

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);FirebaseAuth mAuth;
        mAuth=FirebaseAuth.getInstance();

       /* @Override
        public void onStart() {
            super.onStart();
            // Check if user is signed in (non-null) and update UI accordingly.
            FirebaseUser currentUser = mAuth.getCurrentUser();
            if(currentUser != null){
                currentUser.reload();
            }
        }*/
     //   try {
            handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override

                public void run() {
                    fauth = FirebaseAuth.getInstance();
                    fstore = FirebaseFirestore.getInstance();

              /*  String user = fauth.getCurrentUser().getUid();
                    // FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        // Name, email address etc
                        Intent intent = new Intent(SplashScreen.this, Login.class);
                        startActivity(intent);
                        finish();


                    } else {
                        Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                        startActivity(intent);
                    }*/


                }
            }, 3000);

       // }
       /* catch (Exception e)
        {
            Intent intent = new Intent(SplashScreen.this, MainActivity.class);
            startActivity(intent);
        }*/


    }
}
package com.example.my;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
   Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=findViewById(R.id.btn);
        FirebaseAuth fAuth = null;

/*        DocumentReference df = FirebaseFirestore.getInstance().collection("Users").document(fAuth.getCurrentUser().getUid()
        );
        DocumentSnapshot documentSnapshot = null;
        if(documentSnapshot.getString("isAdmin")!=null || documentSnapshot.getString("isUser")!=null) {
            Intent intent=new Intent(MainActivity.this,Login.class);
            startActivity(intent);
       }*/

            btn.setOnClickListener(new View.OnClickListener() {
               // @Override
                public void onClick(View view) {

                    Intent intent = new Intent(MainActivity.this, Register.class);
                    startActivity(intent);
                }
            });

        }


}
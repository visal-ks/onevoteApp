package com.example.my;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Login extends AppCompatActivity {
    TextView createRegister;

    FirebaseFirestore fauth;
    EditText lemail,lPassword;
    Button Loginbtn;
    FirebaseAuth fAuth;
    FirebaseUser fuser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        lemail=findViewById(R.id.lemail);
        lPassword=findViewById(R.id.lPassword);
        fAuth=FirebaseAuth.getInstance();
        Loginbtn=findViewById(R.id.Loginbtn);

        createRegister=findViewById(R.id.createRegister);
        createRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent( Login.this,Register.class));

            }
        });

        Loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=lemail.getText().toString();
                String pass=lPassword.getText().toString();
                if(email.isEmpty())
                {
                    lemail.setError("Enter Context Email");

                }else if(pass.isEmpty() || pass.length()<6)
                {
                    lPassword.setError("Enter proper Password");

                }
                else {
                    fAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {


                                if (FirebaseAuth.getInstance().getCurrentUser().isEmailVerified()) {

                                    sendUserToNextActivity();
                                } else {
                                    Toast.makeText(Login.this, "Please verify your email to login", Toast.LENGTH_LONG).show();


                                }






                           /* sendUserToNextActivity();
                            Toast.makeText(
                                    Login.this,"LOGGED IN", Toast.LENGTH_SHORT).show();*/


                            } else {
                                Toast.makeText(Login.this, "" + task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        }

                    });
                }
            }
        });
    }

    private void sendUserToNextActivity() {
        checkUserAccessLevel(FirebaseAuth.getInstance().getCurrentUser().getUid());

    }

    private void checkUserAccessLevel(String uid) {
        DocumentReference df = FirebaseFirestore.getInstance().collection("Users").document(fAuth.getCurrentUser().getUid()
        );
        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Log.d("TAG","onSuccess"+documentSnapshot.getData());
                if(documentSnapshot.getString("isAdmin")!=null)
                {
                    Intent intent = new Intent(Login.this,Admenu.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
                if(documentSnapshot.getString("isUser")!=null){
                    Intent intent=new Intent(Login.this,Menu.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }
        });
    }
}
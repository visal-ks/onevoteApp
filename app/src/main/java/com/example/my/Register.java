package com.example.my;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    TextView createlogin;
    EditText Fullname,Email,Password,Password2;
    Button Registerbtn;
   String namepattern="^([A-Za-z]{1}[A-Za-z\\d_]*\\.)+[A-Za-z][A-Za-z\\d_]*$";

    String emailPattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseUser mUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        createlogin = findViewById(R.id.createRegister);
        Fullname = findViewById(R.id.Fullname);
        Email = findViewById(R.id.Email);
        Password = findViewById(R.id.Password);
        Password2 = findViewById(R.id.Password2);
        Registerbtn = findViewById(R.id.Registerbtn);
        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();


        createlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this, Login.class));

            }
        });


        Registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


               PerforAuth();

            }
        });
    }

    private void PerforAuth() {
        String ffname=Fullname.getText().toString();
    //  startActivity(new Intent(Register.this, Login.class));
        String email=Email.getText().toString();
    //  startActivity(new Intent(Register.this, Login.class));
        String pass=Password.getText().toString();
        String pass2=Password2.getText().toString();
       // startActivity(new Intent(Register.this, Login.class));
        if(email.isEmpty() || !email.matches(emailPattern))
        {
            Email.setError("Enter Context Email");

        }
     else if(pass.isEmpty() || pass.length()<6)
        {
            Password.setError("Enter proper Password");

        }else if(!pass.equals(pass2))
        {
            Password2.setError("Password Not match the Field");
        }
     else{
            progressDialog.setMessage("Please wait while Registration...");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                     // FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();

                        /*if(user.isEmailVerified()) {


                            progressDialog.dismiss();

                            Toast.makeText(
                                    Register.this, "Registration Successfull", Toast.LENGTH_SHORT).show();

                        }
                        else{
                            user.sendEmailVerification();
                            Toast.makeText(Register.this,"Check your Email to verify your account!",Toast.LENGTH_SHORT).show();
                        }*/
                        FirebaseAuth.getInstance().getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()) {
                                    Toast.makeText(Register.this, "Registered successfully Please check your email for verification", Toast.LENGTH_LONG).show();

                                    DocumentReference df= FirebaseFirestore.getInstance().collection("Users").document(mAuth.getCurrentUser().getUid()
                                    );

                                    Map<String,Object> userinfo=new HashMap<>();
                                    userinfo.put("Fullname",ffname);
                                    userinfo.put("Emailid",email);
                                    userinfo.put("Isuser","1");
                                    userinfo.put("votestatus","0");
                                    df.set(userinfo);




                                    progressDialog.dismiss();
                                    Email.setText("");
                                    Password.setText("");
                                    sendUserToNextActivity();
                                }
                                else{
                                    Toast.makeText(Register.this,""+task.getException(),Toast.LENGTH_SHORT).show();
                                }
                            }
                        });




                   }
                    else{
                        progressDialog.dismiss();
                        Toast.makeText(Register.this,""+task.getException(),Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }

    }

    private void sendUserToNextActivity() {
            Intent intent = new Intent(Register.this, Login.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

    }
}
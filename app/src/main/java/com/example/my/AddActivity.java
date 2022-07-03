package com.example.my;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddActivity extends AppCompatActivity {

    EditText nam,pos,dep,imgurl;
    Button save,back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);


        nam=(EditText) findViewById(R.id.newname);
        pos=(EditText) findViewById(R.id.newposition);
        dep=(EditText) findViewById(R.id.newdept);
        imgurl=(EditText) findViewById(R.id.newimg);

        save=(Button) findViewById(R.id.save);
        back=(Button) findViewById(R.id.back);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Insertdata();
            clearall();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddActivity.this, AdminCreateCandidates.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);


            }
        });


    }

    private void clearall() {
        nam.setText("");
        dep.setText("");
        pos.setText("");
        imgurl.setText("");
    }

    private void Insertdata() {
        Map<String,Object> map=new HashMap<>();

        map.put("Name",nam.getText().toString());
        map.put("Position",pos.getText().toString());
        map.put("Dept",dep.getText().toString());
        map.put("Url",imgurl.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("Nominees").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddActivity.this, "Data Added Successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddActivity.this, "Data Insertion failed due to unknown reasons", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
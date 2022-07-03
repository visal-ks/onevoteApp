package com.example.my;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class Welcomeback extends AppCompatActivity {
    RecyclerView rcv;
    TextView name,position,dept;
    CircleImageView img;
    Button btn;
    FirebaseAuth mAuth;
    public boolean clicked=false;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomeback);
        img=findViewById(R.id.img);
        name=findViewById(R.id.name);
        dept=findViewById(R.id.dept);
        position=findViewById(R.id.position);
        btn=findViewById(R.id.btn);


        String nam=getIntent().getStringExtra("name");
        String pos=getIntent().getStringExtra("position");
        String dep=getIntent().getStringExtra("Dept");
        String url=getIntent().getStringExtra("url");
        Glide.with(Welcomeback.this).load(url)
                .placeholder(R.drawable.ic_launcher_background)
                .circleCrop()
                .error(com.google.firebase.appcheck.interop.R.drawable.common_google_signin_btn_icon_dark)
                .into(img);
        name.setText(nam);
        position.setText(pos);
        dept.setText(dep);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder=new AlertDialog.Builder(Welcomeback.this);
                builder.setTitle("Are you sure??");
                builder.setMessage("YOu want to submit your vote?");


                builder.setPositiveButton("SubmitVote", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(Welcomeback.this, "Successfully voted", Toast.LENGTH_SHORT).show();
                      btn.setEnabled(false);
                       // FirebaseDatabase.getInstance().getReference().child("Users").child(getRef(votestatus).getKey()).updateChildren(map)
                      // df.set(map);
                       // Task<Void> df= FirebaseFirestore.getInstance().collection("Users").document(mAuth.getCurrentUser().getUid()
                        //).upda;

                       // map.put("DeviceIP",getDeviceip());

                        Map<String,Object>map=new HashMap<>();
                        map.put("votestatus","1");
                        //FirebaseFirestore.collection("Users").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).update(map);
                        String uid= Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser().getUid());
                        DocumentReference df= FirebaseFirestore.getInstance().collection("Users").document(uid);

                        df.update(map);




                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(Welcomeback.this, "Vote the Best One", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // This method will be executed once the timer is over
                        clicked=true;
                        
                       // notifyDataSetChanged();
                        btn.setClickable(true);
                        Log.d(null,"resend1");

                    }
                },10000);

            }
        });










    }


}
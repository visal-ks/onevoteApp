package com.example.my;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

public class Welcomeback extends AppCompatActivity {
    RecyclerView rcv;
    TextView name,position,dept;
    CircleImageView img;
    Button btn;
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
        if(clicked)
            btn.setEnabled(false);
        else
            btn.setEnabled(true);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        // This method will be executed once the timer is over
                        clicked=true;
                        
                       // notifyDataSetChanged();
                        // holder.btn.setClickable(true);
                        Log.d(null,"resend1");

                    }
                },10000);

            }
        });










    }
}
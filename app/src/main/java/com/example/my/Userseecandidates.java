package com.example.my;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class Userseecandidates extends AppCompatActivity {
  RecyclerView recyclerView;

    Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userseecandidates);


       /* btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               //Toast.makeText(Userseecandidates.this,"Your VOTE have been SUCCESSFUL!", Toast.LENGTH_SHORT).show();
               btn.setEnabled(false);

                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        // This method will be executed once the timer is over
                        btn.setEnabled(true);
                        Log.d(TAG,"resend1");

                    }
                },2000);// set time as per your requirement*/
            //}
       // });



        recyclerView=(RecyclerView) findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Model> options =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Nominees"), Model.class)
                        .build();

        adapter =new Adapter(options);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}
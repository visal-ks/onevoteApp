
package com.example.my;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;



public class AdminCreateCandidates extends AppCompatActivity {
    RecyclerView recyclerView;

    adminadapter adapt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_create_candidates);



        recyclerView=(RecyclerView) findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Model> options =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Nominees"), Model.class)
                        .build();

        adapt =new adminadapter(options);
        recyclerView.setAdapter(adapt);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapt.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        adapt.stopListening();
    }

}





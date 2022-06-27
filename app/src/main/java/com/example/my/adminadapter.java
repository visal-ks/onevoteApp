package com.example.my;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class adminadapter extends FirebaseRecyclerAdapter<Model,adminadapter.myviewholder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     */
    public adminadapter(@NonNull FirebaseRecyclerOptions<Model> options) {
        super(options);
    }
    public boolean clicked=false;
    @Override

    protected void onBindViewHolder(@NonNull myviewholder holder, @SuppressLint("RecyclerView") int position, @NonNull Model model) {
        holder.dept.setText(model.getDept());
        holder.name.setText(model.getName());
        holder.position.setText(model.getPosition());
        Glide.with(holder.img.getContext())
                .load(model.getUrl())
                .placeholder(R.drawable.ic_launcher_background)
                .circleCrop()
                .error(com.google.firebase.appcheck.interop.R.drawable.common_google_signin_btn_icon_dark)
                .into(holder.img);


    /*   if(clicked)
             holder.btn.setEnabled(false);
       else
           holder.btn.setEnabled(true);*/

      holder.edit.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              final DialogPlus dialogplus=DialogPlus.newDialog(holder.img.getContext())
                      .setContentHolder(new ViewHolder(R.layout.updatepop))
                      .setExpanded(true,1200)
                      .create();


              //dialogplus.show();
              View view=dialogplus.getHolderView();
              EditText name,dept,pos,url;
              Button update;
              name=view.findViewById(R.id.name);
              dept=view.findViewById(R.id.dept);
              pos=view.findViewById(R.id.pos);
              url=view.findViewById(R.id.img1);
              update=view.findViewById(R.id.update);

             name.setText(model.getName());
             dept.setText(model.getDept());
             pos.setText(model.getPosition());
             url.setText(model.getUrl());
             dialogplus.show();
             update.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                 Map<String,Object> map =new HashMap<>();
                 map.put("Name",name.getText().toString());
                 map.put("Position",pos.getText().toString());
                 map.put("Dept",dept.getText().toString());
                 map.put("url",url.getText().toString());

                     FirebaseDatabase.getInstance().getReference().child("Nominees").child(getRef(position).getKey()).updateChildren(map)
                             .addOnSuccessListener(new OnSuccessListener<Void>() {
                                 @Override
                                 public void onSuccess(Void unused) {
                                     Toast.makeText(holder.name.getContext(), "Updated", Toast.LENGTH_SHORT).show();
                                     dialogplus.dismiss();
                                 }
                             })
                             .addOnFailureListener(new OnFailureListener() {
                                 @Override
                                 public void onFailure(@NonNull Exception e) {
                                     Toast.makeText(holder.name.getContext(), "Error while Updating", Toast.LENGTH_SHORT).show();

                                 }
                             });



                 }
             });

          }
      });

holder.delete.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        AlertDialog.Builder builder=new AlertDialog.Builder(holder.name.getContext());
        builder.setTitle("Are you sure??");
        builder.setMessage("Deleted data cant be undo!!!");

        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
               FirebaseDatabase.getInstance().getReference().child("Nominees").child(getRef(position).getKey()).removeValue();

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(holder.name.getContext(), "Cancelled", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }
});












    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item2,parent,false);


        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        CircleImageView img;
        TextView name,position,dept;
        CardView cv;

        Button edit,delete;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            img= itemView.findViewById(R.id.img1);
            name= itemView.findViewById(R.id.nametxt);
            position= itemView.findViewById(R.id.positiontxt);
            dept= itemView.findViewById(R.id.depttxt);
            cv=itemView.findViewById(R.id.cv);
            edit=itemView.findViewById(R.id.edit);
            delete=itemView.findViewById(R.id.delete);








        }
    }

}


package com.example.my;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class Adapter extends FirebaseRecyclerAdapter<Model,Adapter.myviewholder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     */
    public Adapter(@NonNull FirebaseRecyclerOptions<Model> options) {
        super(options);
    }
    public boolean clicked=false;
    @Override

    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull Model model) {
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

        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Success", Toast.LENGTH_SHORT).show();




               // holder.btn.setClickable(false);

               /* new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        // This method will be executed once the timer is over
                        clicked=true;
                        notifyDataSetChanged();
                       // holder.btn.setClickable(true);
                        Log.d(null,"resend1");

                    }
                },10000);// set time as per your requirement*/





                Intent intent=new Intent(view.getContext(),Welcomeback.class);

               intent.putExtra("name",model.getName());
                intent.putExtra("position",model.getPosition());
                intent.putExtra("Dept",model.getDept());
                intent.putExtra("url",model.getUrl());
                view.getContext().startActivity(intent);





            }
        });
















    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);


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

            edit= itemView.findViewById(R.id.edit);
            delete=itemView.findViewById(R.id.delete);







        }
    }

}

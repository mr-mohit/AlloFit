package com.example.mv.allofit.Workout;

import android.content.Context;
import android.content.Intent;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mv.allofit.R;

import java.util.ArrayList;

/**
 * Created by Gurpreet singh on 12/1/2018.
 */

public class Men_adapter extends RecyclerView.Adapter<Men_adapter.MyHolder> {
    private ArrayList<String> data = new ArrayList<>();
    private Context mcontext;
    public Men_adapter(Context context, ArrayList<String> dy)
    {
        mcontext=context;
        data=dy;
    }
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.list_item_men,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {

        holder.tv.setText(data.get(position));
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(position==0) {
                    Toast.makeText(mcontext, "Better Sore Than Sorry", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mcontext, menchest.class);
                /*intent.putExtra("image_name", data.get(position));*/

                    mcontext.startActivity(intent);
                }
                if(position==1)
                {
                    Toast.makeText(mcontext, "Work Out. Eat Well. Be Patient.", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(mcontext,menbiceps.class);
                    mcontext.startActivity(i);
                }
                if(position==2)
                {
                    Toast.makeText(mcontext, "The Best Way To Predict Future Is To Create It", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(mcontext,mentriceps.class);
                    mcontext.startActivity(i);
                }
                if(position==3)
                {
                    Toast.makeText(mcontext, "Sweat. Smile. Repeat.", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(mcontext,menback.class);
                    mcontext.startActivity(i);
                }
                if(position==4)
                {
                    Toast.makeText(mcontext, "Make Sweat Your Best Accessory", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(mcontext,menshoulder.class);
                    mcontext.startActivity(i);
                }
                if(position==5)
                {
                    Toast.makeText(mcontext, "Respect your body. It’s the only one you get.", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(mcontext,menabs.class);
                    mcontext.startActivity(i);
                }
                if(position==6)
                {
                    Toast.makeText(mcontext, "Strive for progress, not perfection.", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(mcontext,menlegs.class);
                    mcontext.startActivity(i);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder  {
        ImageView img;
        TextView tv;

      LinearLayout parentLayout;
        public MyHolder(View itemView) {

            super(itemView);

            img=itemView.findViewById(R.id.image);
            tv=itemView.findViewById(R.id.textView);
            parentLayout=itemView.findViewById(R.id.lin);
        }


    }
}

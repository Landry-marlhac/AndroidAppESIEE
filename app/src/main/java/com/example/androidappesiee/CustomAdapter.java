package com.example.androidappesiee;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private ArrayList<String> tache_id, tache_nom,
            tache_description, tache_type, tache_priorite, tache_isdone;
    int position;
    Activity activity;
    private Context mContext;
    Animation translate_anim;


    public CustomAdapter(Activity activity,Context context, ArrayList tache_id, ArrayList tache_nom,
                         ArrayList tache_description, ArrayList tache_type,
                         ArrayList tache_priorite, ArrayList tache_isdone) {

        this.activity = activity;
        this.mContext = context;
        this.tache_id = tache_id;
        this.tache_nom = tache_nom;
        this.tache_description = tache_description;
        this.tache_type = tache_type;
        this.tache_priorite = tache_priorite;
        this.tache_isdone = tache_isdone;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.one_line_task, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        this.position = position;
        holder.tv_NomTache.setText(String.valueOf(tache_nom.get(position)));
        holder.tv_Typetache.setText(String.valueOf(tache_type.get(position)));
        holder.effectuee.setChecked(Boolean.parseBoolean(tache_isdone.get(position)));
        holder.row_main_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(tache_id.get(position)));
                intent.putExtra("name", String.valueOf(tache_nom.get(position)));
                intent.putExtra("description", String.valueOf(tache_description.get(position)));
                intent.putExtra("type", String.valueOf(tache_type.get(position)));
                intent.putExtra("prioritee", String.valueOf(tache_priorite.get(position)));
                intent.putExtra("isDone", String.valueOf(tache_isdone.get(position)));
                activity.startActivityForResult(intent,1);

            }
        });

    }

    @Override
    public int getItemCount() {
        return tache_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_NomTache, tv_Typetache;
        CheckBox effectuee;
        androidx.constraintlayout.widget.ConstraintLayout row_main_layout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_NomTache = itemView.findViewById(R.id.tv_NomTache);
            tv_Typetache = itemView.findViewById(R.id.tv_TypeTache);
            effectuee = itemView.findViewById(R.id.Effectuee);
            row_main_layout = itemView.findViewById(R.id.row_main_layout);
            translate_anim = AnimationUtils.loadAnimation(mContext, R.anim.anim_slide_db);
            row_main_layout.setAnimation(translate_anim);
        }
    }
}

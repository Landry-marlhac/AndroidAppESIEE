package com.example.androidappesiee;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private ArrayList<String> tache_id, tache_nom,
            tache_description, tache_type, tache_priorite, tache_isdone;
    private Context mContext;


    public CustomAdapter(Context context, ArrayList tache_id, ArrayList tache_nom,
                         ArrayList tache_description, ArrayList tache_type,
                         ArrayList tache_priorite, ArrayList tache_isdone) {

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
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tv_NomTache.setText(String.valueOf(tache_nom.get(position)));
        holder.tv_Typetache.setText(String.valueOf(tache_type.get(position)));
        holder.effectuee.setChecked(Boolean.valueOf(tache_isdone.get(position)));


    }

    @Override
    public int getItemCount() {
        return tache_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_NomTache, tv_Typetache;
        CheckBox effectuee;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_NomTache = itemView.findViewById(R.id.tv_NomTache);
            tv_Typetache = itemView.findViewById(R.id.tv_TypeTache);
            effectuee = itemView.findViewById(R.id.Effectuee);
        }
    }
}

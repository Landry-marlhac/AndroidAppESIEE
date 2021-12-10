package com.example.androidappesiee;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> {

    List<ToDoTacheModel> tachesList;
    Context mContext;


    public RecycleViewAdapter(List<ToDoTacheModel> tachesList, Context context) {
        this.tachesList = tachesList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_line_task,parent,false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tv_NomTache.setText(tachesList.get(position).getNomTache());
        holder.tv_Typetache.setText(tachesList.get(position).getTypeDeTache());
        holder.effectuee.setChecked(tachesList.get(position).getEffectuee());


    }

    @Override
    public int getItemCount() {
        return tachesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_NomTache, tv_Typetache;
        CheckBox effectuee;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_NomTache = itemView.findViewById(R.id.tv_NomTAche);
            tv_Typetache = itemView.findViewById(R.id.tv_TypeTache);
            effectuee = itemView.findViewById(R.id.Effectuee);
        }
    }
}

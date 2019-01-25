package com.example.home.todo_ap;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class Myadapt extends RecyclerView.Adapter<Myadapt.AdapterViewHolder> {

    List<TaskInfo> tasks;
    Myadapt(List<TaskInfo> tasks){
        this.tasks = tasks;
    }
    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder adapterViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public class AdapterViewHolder extends RecyclerView.ViewHolder {
        public AdapterViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}

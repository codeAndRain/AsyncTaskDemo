package com.challenge.asynctaskdemo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class NamesAdapter extends RecyclerView.Adapter<NamesViewHolder> {

    List<String> nameList = new ArrayList<>();
    private RecyclerView recyclerView;

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public NamesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recycler_view_item, parent, false);
        return new NamesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NamesViewHolder holder, int position) {
        String name = nameList.get(position);
        holder.bind(name);
    }

    @Override
    public int getItemCount() {
        return nameList.size();
    }

    public void addName(String name) {
        nameList.add(name);
        notifyItemInserted(nameList.size() - 1);
        recyclerView.scrollToPosition(nameList.size() - 1);
    }

}

package com.challenge.asynctaskdemo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

class NamesViewHolder extends RecyclerView.ViewHolder {
    TextView nameTextView;
    public NamesViewHolder(@NonNull View itemView) {
        super(itemView);
        nameTextView = itemView.findViewById(R.id.nameTextView);
    }

    public void bind(String name) {
        nameTextView.setText(name);
    }
}

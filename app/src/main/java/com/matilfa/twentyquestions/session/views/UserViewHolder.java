package com.matilfa.twentyquestions.session.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.matilfa.twentyquestions.R;

public class UserViewHolder extends RecyclerView.ViewHolder {
    private final TextView userItemView;

    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
        userItemView = itemView.findViewById(R.id.userlist_item_tv);
    }

    public void bind(String text){
        userItemView.setText(text);
    }

    static UserViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.userlist_item, parent, false);
        return new UserViewHolder(view);
    }
}

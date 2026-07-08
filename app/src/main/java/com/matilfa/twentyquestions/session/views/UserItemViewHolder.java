package com.matilfa.twentyquestions.session.views;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.matilfa.twentyquestions.R;

public class UserItemViewHolder extends RecyclerView.ViewHolder {
    private final TextView userNameTextView;
    private final ImageView personIcon;
    private final FloatingActionButton addButton;

    public UserItemViewHolder(@NonNull View itemView) {
        super(itemView);
        userNameTextView = itemView.findViewById(R.id.username_tv);
        personIcon = itemView.findViewById(R.id.personIcon);
        addButton = itemView.findViewById(R.id.addUserToSessionButton);
    }

    public TextView getUserNameTextView() {
        return userNameTextView;
    }

    public ImageView getPersonIcon() {
        return personIcon;
    }

    public FloatingActionButton getAddButton() {
        return addButton;
    }

    //
//    public void bind(String text){
//        userItemView.setText(text);
//    }
//
//    static UserItemViewHolder create(ViewGroup parent) {
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.userlist_item, parent, false);
//        return new UserItemViewHolder(view);
//    }
}

package com.matilfa.twentyquestions.session.recyclerview;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.matilfa.twentyquestions.R;
import com.matilfa.twentyquestions.data.users.User;

public class UserItemViewHolder extends RecyclerView.ViewHolder {
    private final TextView userNameTextView;
    private User currentUser;
    private final ImageView personIcon;
    private final FloatingActionButton button;

    public UserItemViewHolder(@NonNull View itemView, OnUserButtonClickListener clickListener) {
        super(itemView);
        userNameTextView = itemView.findViewById(R.id.username_tv);
        personIcon = itemView.findViewById(R.id.personIcon);
        button = itemView.findViewById(R.id.addUserToSessionButton);

        button.setOnClickListener(v -> {
            clickListener.onUserButtonClick(currentUser);
        });
    }

    public TextView getUserNameTextView() {
        return userNameTextView;
    }

    public ImageView getPersonIcon() {
        return personIcon;
    }

    public FloatingActionButton getButton() {
        return button;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
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

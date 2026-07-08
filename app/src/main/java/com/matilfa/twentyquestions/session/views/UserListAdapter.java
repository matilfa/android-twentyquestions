package com.matilfa.twentyquestions.session.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.matilfa.twentyquestions.R;
import com.matilfa.twentyquestions.data.users.User;

import java.util.ArrayList;
import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserItemViewHolder> {
    private List<User> allUsers = new ArrayList<>();

    public UserListAdapter() {
    }

    public void setAllUsers(List<User> allUsers) {
        this.allUsers = allUsers;
        notifyDataSetChanged(); //Todo: More specific change event
    }

    @NonNull
    @Override
    public UserItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.userlist_item, parent, false);

        return new UserItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserItemViewHolder holder, int position) {
        holder.getUserNameTextView().setText(allUsers.get(position).name);
    }

    @Override
    public int getItemCount() {
        return allUsers.size();
    }


//    @NonNull
//    @Override
//    public UserItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return UserItemViewHolder.create(parent);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull UserItemViewHolder holder, int position) {
//        User current = getItem(position);
//        holder.bind(current.name);
//    }
//
//    public static class UserDiff extends DiffUtil.ItemCallback<User> {
//
//        @Override
//        public boolean areItemsTheSame(@NonNull User oldItem, @NonNull User newItem) {
//            return oldItem == newItem;
//        }
//
//        @Override
//        public boolean areContentsTheSame(@NonNull User oldItem, @NonNull User newItem) {
//            return oldItem.id == newItem.id;
//        }
//    }
}

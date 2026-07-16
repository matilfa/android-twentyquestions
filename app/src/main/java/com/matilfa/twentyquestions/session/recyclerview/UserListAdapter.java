package com.matilfa.twentyquestions.session.recyclerview;

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
    private List<User> users = new ArrayList<>();
    private OnUserButtonClickListener clickListener;
    private boolean hasAddButton = false;

    public UserListAdapter(OnUserButtonClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setUsers(List<User> users) {
        this.users = users;
        notifyDataSetChanged(); //Todo: More specific change event
    }

    @NonNull
    @Override
    public UserItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.userlist_item, parent, false);

        return new UserItemViewHolder(view, clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull UserItemViewHolder holder, int position) {
        holder.getUserNameTextView().setText(users.get(position).name);
        holder.setCurrentUser(users.get(position));
        if (!hasAddButton) {
            holder.getButton().setImageResource(R.drawable.outline_delete_24);
        } else {
            holder.getButton().setImageResource(R.drawable.outline_add_24);
        }
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void setHasAddButton(boolean hasAddButton) {
        this.hasAddButton = hasAddButton;
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

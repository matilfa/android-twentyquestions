package com.matilfa.twentyquestions.main.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.matilfa.twentyquestions.R;
import com.matilfa.twentyquestions.data.users.User;

import java.util.ArrayList;
import java.util.List;

public class MembersListAdapter extends RecyclerView.Adapter<GenericListItemViewHolder> {
    private List<User> usersInSession = new ArrayList<>();

    @NonNull
    @Override
    public GenericListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.generic_list_item, parent, false);
        return new GenericListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GenericListItemViewHolder holder, int position) {
        var currentUser = usersInSession.get(position);
        holder.getListItemTextView().setText(currentUser.name);
    }

    @Override
    public int getItemCount() {
        return usersInSession.size();
    }

    public void setUsersInSession(List<User> usersInSession) {
        this.usersInSession = usersInSession;
        notifyDataSetChanged();
    }
}

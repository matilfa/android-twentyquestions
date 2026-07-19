package com.matilfa.twentyquestions.session.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.matilfa.twentyquestions.R;
import com.matilfa.twentyquestions.data.sessions.Session;

import java.util.ArrayList;
import java.util.List;

public class SessionListAdapter extends RecyclerView.Adapter<SessionListItemViewHolder> {
    private OnSessionButtonClickListener clickListener;
    private List<Session> sessions = new ArrayList<>();

    public SessionListAdapter(OnSessionButtonClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
        notifyDataSetChanged(); //Todo: More specific change event
    }

    @NonNull
    @Override
    public SessionListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sessionlist_item, parent, false);

        return new SessionListItemViewHolder(view, clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull SessionListItemViewHolder holder, int position) {
        holder.getSessionNameTextView().setText(sessions.get(position).name);
        holder.setThisSession(sessions.get(position));
    }

    @Override
    public int getItemCount() {
        return sessions.size();
    }
}

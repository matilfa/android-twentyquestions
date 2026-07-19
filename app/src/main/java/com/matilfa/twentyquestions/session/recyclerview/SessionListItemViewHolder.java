package com.matilfa.twentyquestions.session.recyclerview;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.matilfa.twentyquestions.R;
import com.matilfa.twentyquestions.data.sessions.Session;

public class SessionListItemViewHolder extends RecyclerView.ViewHolder{
    private TextView sessionNameTextView;
    private Button loadButton;
    private Session thisSession;
    public SessionListItemViewHolder(@NonNull View itemView, OnSessionButtonClickListener clickListener) {
        super(itemView);
        sessionNameTextView = itemView.findViewById(R.id.sessionName_tv);
        loadButton = itemView.findViewById(R.id.loadSavedSession_button);

        loadButton.setOnClickListener(v -> {
            clickListener.onSessionButtonClick(thisSession);
        });
    }

    public TextView getSessionNameTextView() {
        return sessionNameTextView;
    }

    public Session getThisSession() {
        return thisSession;
    }

    public void setThisSession(Session thisSession) {
        this.thisSession = thisSession;
    }
}

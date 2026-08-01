package com.matilfa.twentyquestions.main.recyclerview;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.matilfa.twentyquestions.R;

public class HistoryListItemViewHolder extends RecyclerView.ViewHolder {
    private TextView prevQuestionTextView;

    public HistoryListItemViewHolder(@NonNull View itemView) {
        super(itemView);

        prevQuestionTextView = itemView.findViewById(R.id.prevQuestionText_tv);
    }

    public TextView getPrevQuestionTextView() {
        return prevQuestionTextView;
    }
}

package com.matilfa.twentyquestions.main.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.matilfa.twentyquestions.R;
import com.matilfa.twentyquestions.data.questions.Question;

import java.util.ArrayList;
import java.util.List;

public class HistoryListAdapter extends RecyclerView.Adapter<HistoryListItemViewHolder> {
    private List<Question> askedQuestions = new ArrayList<>();

    @NonNull
    @Override
    public HistoryListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.historylist_item, parent, false);
        return new HistoryListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryListItemViewHolder holder, int position) {
        var currentQ = askedQuestions.get(position);
        holder.getPrevQuestionTextView().setText(currentQ.questionNumber + ". " + currentQ.text);
    }

    @Override
    public int getItemCount() {
        return askedQuestions.size();
    }

    public void setAskedQuestions(List<Question> askedQuestions) {
        this.askedQuestions = askedQuestions;
        notifyDataSetChanged();
    }
}

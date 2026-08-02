package com.matilfa.twentyquestions.main.recyclerview;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.matilfa.twentyquestions.R;

public class GenericListItemViewHolder extends RecyclerView.ViewHolder {
    private TextView listItemTextView;

    public GenericListItemViewHolder(@NonNull View itemView) {
        super(itemView);

        listItemTextView = itemView.findViewById(R.id.listItemText_tv);
    }

    public TextView getListItemTextView() {
        return listItemTextView;
    }
}

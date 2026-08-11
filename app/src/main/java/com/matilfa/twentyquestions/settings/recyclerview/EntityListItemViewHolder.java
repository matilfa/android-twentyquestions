package com.matilfa.twentyquestions.settings.recyclerview;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.matilfa.twentyquestions.R;

public class EntityListItemViewHolder<T> extends RecyclerView.ViewHolder {
    private TextView entityName;
    private ImageButton editButton;
    private ImageButton deleteButton;

    private T currentEntity;

    public EntityListItemViewHolder(@NonNull View itemView,
                                    EntityListAdapter.OnButtonClickListener<T> editButtonClickListener,
                                    EntityListAdapter.OnButtonClickListener<T> deleteButtonClickListener) {
        super(itemView);
        entityName = itemView.findViewById(R.id.entity_name);

        editButton = itemView.findViewById(R.id.editEntity_button);
        deleteButton = itemView.findViewById(R.id.deleteEntity_button);

        editButton.setOnClickListener(v -> {
            editButtonClickListener.onButtonClicked(currentEntity);
        });
        deleteButton.setOnClickListener(v -> {
            deleteButtonClickListener.onButtonClicked(currentEntity);
        });
    }

    public TextView getEntityName() {
        return entityName;
    }

    public T getCurrentEntity() {
        return currentEntity;
    }

    public void setCurrentEntity(T currentEntity) {
        this.currentEntity = currentEntity;
    }
}

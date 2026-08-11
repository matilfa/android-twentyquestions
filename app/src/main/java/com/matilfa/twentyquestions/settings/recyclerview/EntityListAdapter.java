package com.matilfa.twentyquestions.settings.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.matilfa.twentyquestions.R;
import com.matilfa.twentyquestions.data.sessions.Session;
import com.matilfa.twentyquestions.data.users.User;

import java.util.ArrayList;
import java.util.List;

public class EntityListAdapter<T> extends RecyclerView.Adapter<EntityListItemViewHolder<T>> {
    private List<T> entities = new ArrayList<>();
    private OnButtonClickListener<T> editButtonClickListener;
    private OnButtonClickListener<T> deleteButtonClickListener;

    public EntityListAdapter(OnButtonClickListener<T> editButtonClickListener, OnButtonClickListener<T> deleteButtonClickListener) {
        this.editButtonClickListener = editButtonClickListener;
        this.deleteButtonClickListener = deleteButtonClickListener;
    }

    public void setEntities(List<T> entities) {
        this.entities = entities;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EntityListItemViewHolder<T> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.edit_entity_list_item, parent, false);

        return new EntityListItemViewHolder<>(view, editButtonClickListener, deleteButtonClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull EntityListItemViewHolder<T> holder, int position) {
        if (entities.get(position) instanceof User u) {
            holder.getEntityName().setText(u.name);
        } else if (entities.get(position) instanceof Session s) {
            holder.getEntityName().setText(s.name);
        }
        else {
            throw new RuntimeException("Trying to bind unsupported type " + entities.get(position).getClass() +
                    " to view holder.");
        }
        holder.setCurrentEntity(entities.get(position));
    }

    @Override
    public int getItemCount() {
        return entities.size();
    }

    public interface OnButtonClickListener<T> {
        void onButtonClicked(T entity);
    }
}

package com.example.androidapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidapp.R;
import com.example.androidapp.activity.NavigationListener;
import com.example.androidapp.model_class.Repository;

import java.util.List;

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.ViewHolder> {
    private List<Repository> repositories;
    private OnItemClickListener onItemClickListener;

    public RepositoryAdapter(List<Repository> repositories, OnItemClickListener onItemClickListener) {
        this.repositories = repositories;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_repository, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Repository repository = repositories.get(position);
        holder.bind(repository);
    }

    public void updateList(List<Repository> repositoryList){
        this.repositories.addAll(repositoryList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return repositories.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView repoNameTextView;
        public TextView repoDescriptionTextView;
        public ImageView share;
        public ConstraintLayout constraintLayout;
        private List<Repository> repositories;
        private OnItemClickListener onItemClickListener;
        private NavigationListener navigationListener;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            repoNameTextView = itemView.findViewById(R.id.repoNameTextView);
            repoDescriptionTextView = itemView.findViewById(R.id.repoDescriptionTextView);
            share=itemView.findViewById(R.id.share);
            constraintLayout=itemView.findViewById(R.id.constraint);

            share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

           /* itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    onItemClickListener.onItemClick(repositories.get(position).getUrl());
                }
            });*/
        }

        public void bind(Repository repository) {
            repoNameTextView.setText(repository.getName());
            repoDescriptionTextView.setText(repository.getDescription());
        }


    }

    public interface OnItemClickListener {
        void onItemClick(String url);
    }

}

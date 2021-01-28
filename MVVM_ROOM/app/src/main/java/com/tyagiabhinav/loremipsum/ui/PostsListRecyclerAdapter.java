package com.tyagiabhinav.loremipsum.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tyagiabhinav.loremipsum.R;
import com.tyagiabhinav.loremipsum.model.db.Posts;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PostsListRecyclerAdapter extends RecyclerView.Adapter<PostsListRecyclerAdapter.PostListsViewHolder>{

    List<Posts> posts;
    public PostsListRecyclerAdapter(List<Posts> allPosts) {
        posts = allPosts;
    }

    @NonNull
    @Override
    public PostListsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_posts, parent, false);
        return new PostListsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostListsViewHolder holder, int position) {
        Posts post = posts.get(position);
        holder.title.setText(post.getTitle());
        holder.description.setText(post.getDesc());
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class PostListsViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView description;

        View item;

        public PostListsViewHolder(@NonNull View itemView) {
            super(itemView);
            item = itemView;
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
        }
    }
}

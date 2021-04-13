package com.tyagiabhinav.loremipsum.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.tyagiabhinav.loremipsum.R;
import com.tyagiabhinav.loremipsum.databinding.ItemPostsBinding;
import com.tyagiabhinav.loremipsum.model.db.Posts;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class PostsListRecyclerAdapter extends RecyclerView.Adapter<PostsListRecyclerAdapter.PostListsViewHolder> {

    List<Posts> posts;

    public PostsListRecyclerAdapter(List<Posts> allPosts) {
        posts = allPosts;
    }

    @NonNull
    @Override
    public PostListsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemPostsBinding binding = DataBindingUtil.inflate(inflater, R.layout.item_posts, parent, false);
        return new PostListsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PostListsViewHolder holder, int position) {
        holder.bind(posts.get(position));
    }

    @Override
    public int getItemCount() {
        return posts != null ? posts.size() : 0;
    }

    class PostListsViewHolder extends RecyclerView.ViewHolder {
        ItemPostsBinding binding;

        public PostListsViewHolder(@NonNull ItemPostsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Posts posts) {
            this.binding.setPosts(posts);
            this.binding.executePendingBindings();
        }
    }
}

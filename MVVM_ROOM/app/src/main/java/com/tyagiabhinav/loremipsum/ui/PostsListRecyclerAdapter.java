package com.tyagiabhinav.loremipsum.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.tyagiabhinav.loremipsum.R;
import com.tyagiabhinav.loremipsum.databinding.ItemPostsBinding;
import com.tyagiabhinav.loremipsum.model.db.Posts;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class PostsListRecyclerAdapter extends RecyclerView.Adapter<PostsListRecyclerAdapter.PostListsViewHolder> {

    List<Posts> posts;
    public void setData(List<Posts> newPosts){
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new PostDiffCallback(posts, newPosts));
        posts = newPosts;
        diffResult.dispatchUpdatesTo(this);
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

    static class PostListsViewHolder extends RecyclerView.ViewHolder {
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

    static class PostDiffCallback extends DiffUtil.Callback{
        private final List<Posts> oldList;
        private final List<Posts> newList;

        PostDiffCallback(List<Posts> oList, List<Posts> nList){
            oldList = oList;
            newList = nList;
        }

        @Override
        public int getOldListSize() {
            return oldList.size();
        }

        @Override
        public int getNewListSize() {
            return newList.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return oldList.get(oldItemPosition).getId() == newList.get(newItemPosition).getId();
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
        }
    }
}

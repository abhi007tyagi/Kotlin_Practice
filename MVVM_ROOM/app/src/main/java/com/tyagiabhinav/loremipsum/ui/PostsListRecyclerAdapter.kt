package com.tyagiabhinav.loremipsum.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tyagiabhinav.loremipsum.databinding.ItemPostsBinding
import com.tyagiabhinav.loremipsum.model.dao.Post

class PostsListRecyclerAdapter(allPosts: List<Post>) : RecyclerView.Adapter<PostsListRecyclerAdapter.PostListsViewHolder>() {

    var posts: List<Post> = allPosts

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostListsViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemPostsBinding = ItemPostsBinding.inflate(inflater)
        return PostListsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostListsViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    override fun getItemCount(): Int {
        return posts.size ?: 0
    }

    inner class PostListsViewHolder(var binding: ItemPostsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post) {
            binding.post = post
            binding.executePendingBindings()
        }
    }

}
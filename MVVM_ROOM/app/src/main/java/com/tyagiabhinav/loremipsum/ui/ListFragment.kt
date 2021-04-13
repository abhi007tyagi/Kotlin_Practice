package com.tyagiabhinav.loremipsum.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.tyagiabhinav.loremipsum.R
import com.tyagiabhinav.loremipsum.viewmodel.PostsViewModel

class ListFragment : Fragment() {
    lateinit var viewModel: PostsViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        val recyclerview: RecyclerView = view.findViewById(R.id.postList)
        viewModel = ViewModelProvider(requireActivity()).get(PostsViewModel::class.java)
        viewModel.getPosts().observe(viewLifecycleOwner, Observer { postList ->
            // update UI
            val adapter = PostsListRecyclerAdapter(postList)
            recyclerview.adapter = adapter
            recyclerview.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        })
        return view
    }
}
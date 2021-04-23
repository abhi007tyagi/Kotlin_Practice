package com.tyagiabhinav.loremipsum.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.tyagiabhinav.loremipsum.databinding.FragmentListBinding
import com.tyagiabhinav.loremipsum.viewmodel.PostsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding = FragmentListBinding.inflate(inflater)
        val viewModel: PostsViewModel = ViewModelProvider(requireActivity()).get(PostsViewModel::class.java)
        viewModel.result.observe(viewLifecycleOwner, Observer { listOfPost ->
            // update UI
            if (listOfPost.isEmpty()) {
                binding.errorMsg.visibility = View.VISIBLE
                binding.errorMsg.text = "Some error occurred!"
            } else {
                val adapter = PostsListRecyclerAdapter(listOfPost)
                binding.postList.adapter = adapter
                binding.postList.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            }
        })
        binding.vm = viewModel
        return binding.root
    }
}
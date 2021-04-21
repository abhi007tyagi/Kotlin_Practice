package com.tyagiabhinav.loremipsum.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tyagiabhinav.loremipsum.R;
import com.tyagiabhinav.loremipsum.viewmodel.PostsViewModel;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        RecyclerView recyclerview = view.findViewById(R.id.postList);
        PostsViewModel viewModel = new ViewModelProvider(requireActivity()).get(PostsViewModel.class);
        viewModel.getAllPosts().observe(getViewLifecycleOwner(), postsList -> {
            // update UI
            PostsListRecyclerAdapter adapter = new PostsListRecyclerAdapter(postsList);
            recyclerview.setAdapter(adapter);
            recyclerview.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        });
        return view;
    }
}
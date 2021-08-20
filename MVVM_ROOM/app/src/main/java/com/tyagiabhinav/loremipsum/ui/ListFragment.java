package com.tyagiabhinav.loremipsum.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tyagiabhinav.loremipsum.R;
import com.tyagiabhinav.loremipsum.databinding.FragmentListBinding;
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
        FragmentListBinding binding = FragmentListBinding.inflate(inflater);
        RecyclerView recyclerview = binding.postList;
        PostsListRecyclerAdapter adapter = new PostsListRecyclerAdapter();
        recyclerview.setAdapter(adapter);
        recyclerview.addItemDecoration(new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));
        PostsViewModel viewModel = new ViewModelProvider(requireActivity()).get(PostsViewModel.class);
        viewModel.getAllPosts().observe(getViewLifecycleOwner(), postsList -> {
            if(postsList.isEmpty()){
                binding.errorMsg.setText(R.string.error_no_data);
                binding.errorMsg.setVisibility(View.VISIBLE);
            } else {
                // update UI
                adapter.setData(postsList);
            }
        });
        binding.setVm(viewModel);
        return binding.getRoot();
    }
}
package com.example.brcandroid.ui.viewapplications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import com.example.brcandroid.databinding.FragmentViewapplicationsBinding;

public class ViewApplicationsFragment extends Fragment {

    private FragmentViewapplicationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ViewApplicationsViewModel homeViewModel =
                new ViewModelProvider(this).get(ViewApplicationsViewModel.class);

        binding = FragmentViewapplicationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textViewapplications;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
package com.example.brcandroid.ui.viewrentals;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import com.example.brcandroid.databinding.FragmentViewrentalsBinding;

public class ViewRentalFragment extends Fragment {

    private FragmentViewrentalsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ViewRentalsViewModel slideshowViewModel =
                new ViewModelProvider(this).get(ViewRentalsViewModel.class);

        binding = FragmentViewrentalsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textViewrentals;
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
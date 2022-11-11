package com.example.carpoolas.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.carpoolas.R;
import com.example.carpoolas.databinding.FragmentFilterBinding;
import com.example.carpoolas.model.PageOfListings;


public class FilterFragment extends Fragment implements IFilterView{

    private FragmentFilterBinding binding;
    private Listener listener;


    public FilterFragment(Listener listener) {
        this.listener = listener;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.binding = FragmentFilterBinding.inflate(inflater);
        return this.binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void updateDisplay(PageOfListings newPage) {
        this.binding.filterLabel.setText(newPage.toString());
    }
}
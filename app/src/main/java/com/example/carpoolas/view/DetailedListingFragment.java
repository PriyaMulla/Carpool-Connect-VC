package com.example.carpoolas.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.carpoolas.databinding.FragmentDetailedListingBinding;
import com.example.carpoolas.view.IDetailedListingFragment;


public class DetailedListingFragment extends Fragment implements IDetailedListingFragment {

    FragmentDetailedListingBinding binding;
    Listener listener;
    FragmentManager fmanager;
    String curRRole = "";

    public DetailedListingFragment(String s) {curRRole = s;

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.binding = FragmentDetailedListingBinding.inflate(inflater);
        return this.binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //TODO Onclick close -goes back?
        //TODO Onclick either accept or message
        TextView exampleText = binding.exampleText;

                exampleText.setText(curRRole);


    }
}
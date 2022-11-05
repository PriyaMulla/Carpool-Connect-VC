package com.example.carpoolas.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.carpoolas.R;
import com.example.carpoolas.databinding.FragmentCreateListingBinding;
import com.example.carpoolas.model.Listing;

public class CreateListingFragment extends Fragment implements ICreateListingView {

    FragmentCreateListingBinding binding;
    Listener listener;

    public CreateListingFragment(Listener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.binding = FragmentCreateListingBinding.inflate(inflater);
        return this.binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View getRootView() {
        return null;
    }

    @Override
    public void updatePageOfListings(Listing lst) {

        this.binding.listingLabel.setText(lst.toString());
    }
}
package com.example.carpoolas.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.carpoolas.R;
import com.example.carpoolas.controller.MainActivity;
import com.example.carpoolas.databinding.FragmentDashboardBinding;
import com.example.carpoolas.model.CustomAdapter;
import com.example.carpoolas.model.Listing;

import java.util.ArrayList;
import java.util.Iterator;


public class DashboardFragment extends Fragment implements IDashboardView {

    FragmentDashboardBinding binding;
    Listener listener;

    public DashboardFragment(Listener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.binding = FragmentDashboardBinding.inflate(inflater);
        return this.binding.getRoot();

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        ArrayList<String> dash = new ArrayList<>();
        //dash.add("No listings available :'(");

        //somehow add listings into arrayList
        int i =0;
        Iterator<Listing> listingsIterator = new MainActivity().getListings().listings.iterator();
        while (listingsIterator.hasNext()){
            dash.add("Next");
            Listing listing = listingsIterator.next();
            dash.set(i, listing.toString());
            i++;
            }
        if(new MainActivity().getListings().isEmpty() == true){
            //dash.add("yes");
            dash.add("No listings available :'(");
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,dash);

        ListView listView = (ListView) view.findViewById(R.id.listview);
        listView.setAdapter(adapter);
        //listView.setOnItemClickListener();
//        listView.setOnClickListener(new AdapterView.OnItemClickListener(){
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getContext(), "Click ListItem Number " + position, Toast.LENGTH_LONG).show();
//            }
//        });
    }

}
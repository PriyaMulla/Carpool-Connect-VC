package com.example.carpoolas.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.carpoolas.R;
import com.example.carpoolas.controller.MainActivity;
import com.example.carpoolas.databinding.FragmentCreateAccountBinding;
import com.example.carpoolas.databinding.FragmentDashboardBinding;
import com.example.carpoolas.model.CustomAdapter;
import com.example.carpoolas.model.Listing;
import com.example.carpoolas.model.PageOfListings;
import com.google.android.material.snackbar.Snackbar;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


public class DashboardFragment extends Fragment implements IDashboardView {

    FragmentDashboardBinding binding;
    Listener listener;

    public DashboardFragment(IDashboardView.Listener listener) {
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
        dash.add("Listing 1");
        dash.add("Listing 2");
        dash.add("Listing 3");
        dash.add("Listing 4");
        dash.add("Listing 5");
        dash.add("Listing 1");
        dash.add("Listing 2");
        dash.add("Listing 3");
        dash.add("Listing 4");
        dash.add("Listing 5");
        dash.add("Listing 1");
        dash.add("Listing 2");
        dash.add("Listing 3");
        dash.add("Listing 4");
        dash.add("Listing 5");
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
            dash.add("yes");
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,dash);

        ListView listView = (ListView) view.findViewById(R.id.listview);
        listView.setAdapter(adapter);

//        listView.setOnClickListener(new AdapterView.OnItemClickListener(){
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getContext(), "Click ListItem Number " + position, Toast.LENGTH_LONG).show();
//            }
//        });
    }

}
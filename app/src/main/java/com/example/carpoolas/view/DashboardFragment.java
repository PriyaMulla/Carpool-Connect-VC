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

import com.example.carpoolas.R;
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
    private static final String TAG = "RecyclerViewFragment";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int SPAN_COUNT = 2;
    private static final int DATASET_COUNT = 10;
    public static Listener listener;
    //private Listener listener;
    //PageOfListings lst = DashboardFragment.this.listener.getListings();
    FragmentDashboardBinding binding;
    public DashboardFragment(IDashboardView.Listener listener) {
        this.listener = listener;
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard,container,false);
        //Iterator<Listing> listingsIterator = lst.listings.iterator();
        ArrayList<String> dash = new ArrayList<>();
        dash.add("Listing 1");
        dash.add("Listing 2");
        dash.add("Listing 3");
        dash.add("Listing 4");
        dash.add("Listing 5");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,dash);

        ListView lvData = (ListView) view.findViewById(R.id.listview);
        lvData.setAdapter(adapter);
        // Inflate the layout for this fragment
        this.binding = FragmentDashboardBinding.inflate(inflater);

        return view;
        //this.binding = FragmentDashboardBinding.inflate(inflater);
        //return this.binding.getRoot();

    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState,PageOfListings Pagelistings){
        super.onViewCreated(view, savedInstanceState);
        //ArrayList<String> dash = new ArrayList<>();
        //dash.add("Listing 1");
        //dash.add("Listing 2");
        //dash.add("Listing 3");
        //dash.add("Listing 4");
        //dash.add("Listing 5");
        //somehow add listings into arrayList
        //int i =0;
        //Iterator<Listing> listingsIterator = Pagelistings.listings.iterator();
        //while (listingsIterator.hasNext()){
        //    Listing listing = listingsIterator.next();
        //    dash.set(i, Pagelistings.listings.get(i).toString());
        //    i++;
        //    }
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,dash);

        //ListView lvData = (ListView) view.findViewById(R.id.listview);
        //lvData.setAdapter(adapter);
    }

}
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


public class DashboardFragment extends Fragment implements IDashboardView, OnItemClickListener {
    private static final String TAG = "RecyclerViewFragment";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int SPAN_COUNT = 2;
    private static final int DATASET_COUNT = 10;
    private Listener listener;
    //PageOfListings lst = DashboardFragment.this.listener.getListings();
    FragmentDashboardBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard,container,false);
        //Iterator<Listing> listingsIterator = lst.listings.iterator();
        ArrayList<String> data = new ArrayList<>();
        data.add("Windows 10");
        data.add("Android");
        data.add("iOS");
        data.add("Mac OSX");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,data);

        ListView lvData = (ListView) view.findViewById(R.id.listview);
        lvData.setAdapter(adapter);
        // Inflate the layout for this fragment
        this.binding = FragmentDashboardBinding.inflate(inflater);

        return view;

    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //bind data to listview

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    private enum LayoutManagerType {
        LINEAR_LAYOUT_MANAGER
    }

    protected LayoutManagerType mCurrentLayoutManagerType;

    protected RadioButton mLinearLayoutRadioButton;

    protected RecyclerView mRecyclerView;
    protected CustomAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;

    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm");
    Date date;

    {
        try {
            date = formatter.parse("12/12/2023 19:00");
        } catch (ParseException e) {
            Log.d("hio","wow");
        }
    }

    Listing listy = new Listing(date,"Driver",date,"123 Name Street, City, NY 12345","456 Name Street, City, NY 56789",4);
    protected String[] listings = {listy.toString(),listy.toString(),listy.toString(),listy.toString(),listy.toString(),listy.toString()};

    /**
     * Set RecyclerView's LayoutManager to the one given.
     *
     * @param layoutManagerType Type of layout manager to switch to.
     */
    public void setRecyclerViewLayoutManager(LayoutManagerType layoutManagerType) {
        int scrollPosition = 0;

        // If a layout manager has already been set, get current scroll position.
        if (mRecyclerView.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) mRecyclerView.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }
                mLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.scrollToPosition(scrollPosition);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save currently selected layout manager.
        savedInstanceState.putSerializable(KEY_LAYOUT_MANAGER, mCurrentLayoutManagerType);
        super.onSaveInstanceState(savedInstanceState);
    }

    /**
     * Generates Strings for RecyclerView's adapter. This data would usually come
     * from a local content provider or remote server.
     */
    private void initDataset() {
        //listings = new String[DATASET_COUNT];
        for (int i = 0; i < DATASET_COUNT; i++) {
            Log.d("hi","This is element #" + Array.get(listings,i));
        }
    }
}
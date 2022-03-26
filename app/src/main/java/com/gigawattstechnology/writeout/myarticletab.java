package com.gigawattstechnology.writeout;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import adapter.RandomNumListAdapter;


public class myarticletab extends Fragment {
    private RecyclerView recyclerView;
    String[] name={"1","2","3","1","2","3","1","2","3","1","2","3"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_myarticletab, container, false);
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new RandomNumListAdapter(name));
        return view;
    }
}
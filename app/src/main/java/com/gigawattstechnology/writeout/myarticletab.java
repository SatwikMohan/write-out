package com.gigawattstechnology.writeout;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.internal.ScrimInsetsFrameLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import adapter.RandomNumListAdapter;


public class myarticletab extends Fragment {
    private RecyclerView recyclerView;
    ArrayList<String> key=new ArrayList<>();
    ArrayList<String> name=new ArrayList<>();
    ArrayList<String> store=new ArrayList<>();
    int i;
    long r;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            DatabaseReference ref = (DatabaseReference) FirebaseDatabase.getInstance().getReference().child("satwikg17");
            ref.addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                        key.add(postSnapshot.getKey());
                    }
                    r= snapshot.getChildrenCount();
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
            for (i = 0; i < r; i++) {
                DatabaseReference v = (DatabaseReference) FirebaseDatabase.getInstance().getReference().child("satwikg17").child(key.get(i)).child("name");
                v.addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        name.add(snapshot.getValue(String.class));
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            }
            key.clear();
        Set<String> s=new HashSet<>(name);
//name.add("nsnsnsj 26/3/2022 sbbsbsbh Scientific facts");
//name.add("nsnsnsj 26/3/2022 sbbsbsbh Scientific facts");
        View view = inflater.inflate(R.layout.fragment_myarticletab, container, false);
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new RandomNumListAdapter(s));
        return view;
    }
}
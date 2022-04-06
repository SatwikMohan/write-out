package com.gigawattstechnology.writeout;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.internal.ScrimInsetsFrameLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import adapter.RandomNumListAdapter;


public class myarticletab extends Fragment  {
    private RecyclerView recyclerView;
    ArrayList<String> key=new ArrayList<>();
    ArrayList<String> name=new ArrayList<>();
    ArrayList<String> store=new ArrayList<>();
    String auth="satwikg17";
    int i,c=0;
    long r;
    TextView mytext;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_myarticletab, container, false);
            DatabaseReference ref = (DatabaseReference) FirebaseDatabase.getInstance().getReference().child("Write OUT").child(authtransfer.givename());
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
                DatabaseReference v = (DatabaseReference) FirebaseDatabase.getInstance().getReference().child("Write OUT").child(authtransfer.givename()).child(key.get(i)).child("name");
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
            Set<String> k=new HashSet<>(key);
        Set<String> s=new HashSet<>(name);
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new RandomNumListAdapter(s,k));
        key.clear();
        name.clear();
        return view;
    }
}
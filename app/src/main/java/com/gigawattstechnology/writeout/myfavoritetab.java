package com.gigawattstechnology.writeout;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import adapter.RandomNumListAdapter;
import adapter.RandomNumListAdapter3;


public class myfavoritetab extends Fragment {
    String[] name={"1","2","3","1","2","3","1","2","3","1","2","3"};
    private RecyclerView recyclerView;
ArrayList<String> key=new ArrayList<>();
ArrayList<String> favorite=new ArrayList<>();
     @RequiresApi(api = Build.VERSION_CODES.N)
     @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_myfavoritetab, container, false);
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("Articles");
        reference.addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
              for(DataSnapshot postSnapshot: snapshot.getChildren()){
                  key.add(postSnapshot.getKey());
              }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        for(int i=0;i<key.size();i++){
            DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference().child("Articles").child(key.get(i)).child("favorite").child(authtransfer.givename());
            int finalI = i;
            databaseReference.addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    favorite.add(snapshot.getValue(String.class));
                    if(snapshot.getValue(String.class).equals("#"))
                    {
                        key.remove(finalI);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        favorite.removeIf(n -> n.equals("#"));
        Set<String> favoriteset=new HashSet<>(favorite);
        Set<String> keyset=new HashSet<>(key);
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new RandomNumListAdapter3(favoriteset,keyset));
        favorite.clear();
        return view;
    }
}
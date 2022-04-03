package com.gigawattstechnology.writeout;

import android.os.Bundle;

import androidx.annotation.NonNull;
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

import java.util.ArrayList;

import adapter.RandomNumListAdapter;
import adapter.RandomNumListAdapter2;


public class othersarticletab extends Fragment {
    String[] name={"1","2","3","1","2","3","1","2","3","1","2","3"};
    ArrayList<String> users;
    ArrayList<ArrayList<String>> key;
    ArrayList<String> namevalues=new ArrayList<>();
    long r1,r2;
     private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_othersarticletab, container, false);
        /*DatabaseReference ref = (DatabaseReference) FirebaseDatabase.getInstance().getReference();
        ref.addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    if(postSnapshot.getKey().equals(authtransfer.givename())){
                        users.add(postSnapshot.getKey());
                    }
                }
                r1= snapshot.getChildrenCount();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        for(int i=0;i<r1;i++){
            DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference().child(users.get(i));
            int finalI = i;
            databaseReference.addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                        if(postSnapshot.getKey().equals(authtransfer.givename())){
                            key.get(finalI).add(postSnapshot.getKey());
                        }
                    }
                    r2= snapshot.getChildrenCount();
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }
        for(int i=0;i<users.size();i++)
        {
            for(int j=0;j<key.get(i).size();j++){
                DatabaseReference v = (DatabaseReference) FirebaseDatabase.getInstance().getReference().child(users.get(i)).child(key.get(i).get(j)).child("name");
                v.addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        namevalues.add(snapshot.getValue(String.class));
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            }
        }*/
        //namevalues.add("1");namevalues.add("2");namevalues.add("3");namevalues.add("4");namevalues.add("5");namevalues.add("6");
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new RandomNumListAdapter2(name));
        return view;
    }
}
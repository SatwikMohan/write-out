package com.gigawattstechnology.writeout;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashSet;
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
    SearchView searchView;
    /*RecyclerView recyclerView;
    DatabaseReference database;
    MyAdapter myAdapter;
    ArrayList<User> list=new ArrayList<>();
    ArrayList<String> listkey=new ArrayList<>();*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_myarticletab, container, false);
        searchView=view.findViewById(R.id.searchView);

            DatabaseReference ref = (DatabaseReference) FirebaseDatabase.getInstance().getReference().child("Write OUT").child(authtransfer.givename());
            ref.addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                        //key.add(postSnapshot.getKey());
                        DatabaseReference v = (DatabaseReference) FirebaseDatabase.getInstance().getReference().child("Write OUT").child(authtransfer.givename()).child(postSnapshot.getKey()).child("name");
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
                    r= snapshot.getChildrenCount();
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
            /*for (i = 0; i < r; i++) {
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
            }*/
        Set<String> s=new HashSet<>(name);
        authtransfer.storearticlepublished(s.size());
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        RandomNumListAdapter randomNumListAdapter=new RandomNumListAdapter(s);
        recyclerView.setAdapter(randomNumListAdapter);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                search(s);
                return true;
            }
        });


        return view;
    }

    private void search(String s) {
        ArrayList<String> mylist=new ArrayList<>();
        for(String object: name ){
            if(object.toLowerCase().contains(s.toLowerCase())){
                mylist.add(object);
            }
        }
        Set<String> set=new HashSet<>(mylist);
        RandomNumListAdapter randomNumListAdapter=new RandomNumListAdapter(set);
        recyclerView.setAdapter(randomNumListAdapter);
    }

    /*@Override
    public void onStart() {
        super.onStart();
        myadaptermy.startListening();
    }
    @Override
    public void onStop() {
        super.onStop();
        myadaptermy.stopListening();
    }*/

}
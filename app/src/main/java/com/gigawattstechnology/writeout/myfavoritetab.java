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
import android.widget.SearchView;
import android.widget.TextView;

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
import adapter.RandomNumListAdapter2;
import adapter.RandomNumListAdapter3;


public class myfavoritetab extends Fragment {
    String[] name={"1","2","3","1","2","3","1","2","3","1","2","3"};
    private RecyclerView recyclerView;
ArrayList<String> key=new ArrayList<>();
ArrayList<String> favorite=new ArrayList<>();
TextView textView;
SearchView searchView;
     @RequiresApi(api = Build.VERSION_CODES.N)
     @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_myfavoritetab, container, false);
        searchView=view.findViewById(R.id.searchViewfav);
        textView=view.findViewById(R.id.nothing);
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

            databaseReference.addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    favorite.add(snapshot.getValue(String.class));

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        /*if(favorite.size()==0){
            favorite.add("no more articles in here");
            key.clear();
            key.add("no more articles in here");
        }*/
        favorite.removeIf(n -> n.equals("#"));
        if(favorite.size()==0){
            textView.setVisibility(View.VISIBLE);
        }else {
            textView.setVisibility(View.INVISIBLE);
            Set<String> favoriteset = new HashSet<>(favorite);
            recyclerView = view.findViewById(R.id.recyclerview);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
            recyclerView.setAdapter(new RandomNumListAdapter3(favoriteset));
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
        }
        return view;
    }
        private void search(String s) {
            ArrayList<String> mylist=new ArrayList<>();
            for(String object: favorite ){
                if(object.toLowerCase().contains(s.toLowerCase())){
                    mylist.add(object);
                }
            }
            Set<String> set=new HashSet<>(mylist);
            RandomNumListAdapter3 randomNumListAdapter3=new RandomNumListAdapter3(set);
            recyclerView.setAdapter(randomNumListAdapter3);
        }

}
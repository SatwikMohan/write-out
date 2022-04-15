package com.gigawattstechnology.writeout;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import adapter.RandomNumListAdapter;
import adapter.RandomNumListAdapter4;


public class myratingstab extends Fragment {

    private RecyclerView recyclerView;
ArrayList<String> userkey=new ArrayList<>();
    ArrayList<String> name=new ArrayList<>();
    ArrayList<String> namevalueskey=new ArrayList<>();
    ArrayList<String> namevalues=new ArrayList<>();
    database database=new database();
    TextView nameauth,articlepublishedcount,totalfavoritescount;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_myratingstab, container, false);
        /*DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("Users");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot postSnapshot: snapshot.getChildren()){
                    userkey.add(postSnapshot.getKey());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        for(int i=0;i<userkey.size();i++){
            DatabaseReference database= FirebaseDatabase.getInstance().getReference().child("Users").child(userkey.get(i)).child("name");
            database.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    name.add(snapshot.getValue(String.class));
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }


        database.storetheusers(name);
        /*for(int i=0;i< userkey.size();i++){
        DatabaseReference fornamevalueskey = FirebaseDatabase.getInstance().getReference().child("Write OUT").child(name.get(i));
        fornamevalueskey.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    namevalueskey.add(postSnapshot.getKey());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        for (int j = 0; j < namevalueskey.size(); j++) {
            DatabaseReference fornamevalues = FirebaseDatabase.getInstance().getReference().child("Write OUT").child(name.get(i)).child(namevalueskey.get(j)).child("name");
            fornamevalues.addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    namevalues.add(snapshot.getValue(String.class));
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
        namevalueskey.clear();
    }*/
/*name.add("11");name.add("10");name.add("9");name.add("8");name.add("7");name.add("6");name.add("5");name.add("4");name.add("3");name.add("2");name.add("1");
        Set<String> nameset=new HashSet<>(name);*/
        /*Set<String> namevaluesset=new HashSet<>(database.givethenamevalues());*/
        /*recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new RandomNumListAdapter4(nameset));*/
        nameauth=view.findViewById(R.id.nameauth);
        nameauth.setText(authtransfer.givename());
        articlepublishedcount=view.findViewById(R.id.articlepublishedcount);
        articlepublishedcount.setText(""+authtransfer.givearticlepublished());
        totalfavoritescount=view.findViewById(R.id.totalfavoritescount);
        totalfavoritescount.setText(""+authtransfer.givetotalfavorites());
        return view;
    }
}
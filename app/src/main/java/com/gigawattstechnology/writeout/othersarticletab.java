package com.gigawattstechnology.writeout;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import adapter.RandomNumListAdapter2;


public class othersarticletab extends Fragment {
    String[] name={"1","2","3","1","2","3","1","2","3","1","2","3"};
    ArrayList<String> users=new ArrayList<>();
    //ArrayList<ArrayList<String>> key=new ArrayList<>();
    ArrayList<String> namevalues=new ArrayList<>();
    ArrayList<String> namevalueskey=new ArrayList<>();
    ArrayList<String> userkey=new ArrayList<>();
    long r1,r2;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_othersarticletab, container, false);

        /*DatabaseReference ref = (DatabaseReference) FirebaseDatabase.getInstance().getReference("Write OUT");
        ref.addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                        users.add(postSnapshot.getKey());
                }
                r1= snapshot.getChildrenCount();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

                int i=0;
                for( i=0;i< users.size();i++){
                    DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("Write OUT").child(users.get(i));
                    int t = i;
                    databaseReference.addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                                    key.get(t).add(postSnapshot.getKey());

                            }
                            //r2= snapshot.getChildrenCount();
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                }

                for(i=0;i<users.size();i++)
                {
                    for(int j=0;j<key.get(i).size();j++){
                        DatabaseReference v = (DatabaseReference) FirebaseDatabase.getInstance().getReference("Write OUT").child(users.get(i)).child(key.get(i).get(j)).child("name");
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
/////
        DatabaseReference foruserkey=FirebaseDatabase.getInstance().getReference("Users");
        foruserkey.addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot postSnapshot : snapshot.getChildren()){
                    userkey.add(postSnapshot.getKey());
                }
                r1= snapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        for(int i=0;i<r1;i++) {
            DatabaseReference forusername = FirebaseDatabase.getInstance().getReference("Users").child(userkey.get(i)).child("name");
            forusername.addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                        users.add(snapshot.getValue(String.class));
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

        for(int i=0;i< users.size();i++){
            DatabaseReference fornamevalueskey = FirebaseDatabase.getInstance().getReference("Write OUT").child(users.get(i));
            fornamevalueskey.addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for(DataSnapshot postSnapshot : snapshot.getChildren()){
                        namevalueskey.add(postSnapshot.getKey());
                    }
                    r2= snapshot.getChildrenCount();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            for(int j=0;j<r2;j++) {
                if (users.get(i) != authtransfer.givename()) {
                    DatabaseReference fornamevalues = FirebaseDatabase.getInstance().getReference("Write OUT").child(users.get(i)).child(namevalueskey.get(j)).child("name");
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
            }
            namevalueskey.clear();
        }
       Set<String> nameset=new HashSet<>(namevalues);
        Set<String> userset=new HashSet<>(users);
        //namevalues.add("1");namevalues.add("2");namevalues.add("3");namevalues.add("4");namevalues.add("5");namevalues.add("6");
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new RandomNumListAdapter2(nameset));
        namevalues.clear();
        return view;
    }
}
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
import android.widget.SearchView;

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
    String[] name={"satwikg17","satwikplay69"};
    ArrayList<String> users=new ArrayList<>();
    //ArrayList<ArrayList<String>> key=new ArrayList<>();
    ArrayList<String> namevalues=new ArrayList<>();
    ArrayList<String> namevalueskey=new ArrayList<>();
    ArrayList<String> usersn=new ArrayList<>();
    ArrayList<String> userkey=new ArrayList<>();
    RecyclerView recyclerView;
    long r1,r2;
    SearchView searchView;
    DatabaseReference foruserkey=FirebaseDatabase.getInstance().getReference().child("Users");
    DatabaseReference forusernames=FirebaseDatabase.getInstance().getReference().child("Users");
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_othersarticletab, container, false);
        searchView=view.findViewById(R.id.searchViewoth);
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
/////////////////////////

        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference().child("Articles");
        databaseReference.addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot postSnapshot: snapshot.getChildren()){
                    //usersn.add(postSnapshot.getKey());
                    DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference().child("Articles").child(postSnapshot.getKey()).child("name");
                    databaseReference1.addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() {
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

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        /*for(int i=0;i< usersn.size();i++) {
            DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference().child("Articles").child(usersn.get(i)).child("name");
            databaseReference1.addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    namevalues.add(snapshot.getValue(String.class));
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }*/
      Set<String> nameset=new HashSet<>(namevalues);
        //Set<String> usersnset=new HashSet<>(usersn);
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new RandomNumListAdapter2(nameset));
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
        ArrayList<String> mylistkey=new ArrayList<>();
        for(String object: namevalues ){
            if(object.toLowerCase().contains(s.toLowerCase())){
                mylist.add(object);
            }
        }
        Set<String> set=new HashSet<>(mylist);
        RandomNumListAdapter2 randomNumListAdapter2=new RandomNumListAdapter2(set);
        recyclerView.setAdapter(randomNumListAdapter2);
    }
}
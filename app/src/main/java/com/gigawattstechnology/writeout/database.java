package com.gigawattstechnology.writeout;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class database {
    ArrayList<String> names=new ArrayList<>();
    ArrayList<ArrayList<String>> namevalueskey=new ArrayList<>();
    ArrayList<String> namevalues=new ArrayList<>();
    int i,j;
    public void storetheusers(ArrayList<String> names){
        this.names=names;
    }
    public ArrayList<String> givethenamevalues(){
        for(i=0;i< names.size();i++){
            DatabaseReference fornamevalueskey = FirebaseDatabase.getInstance().getReference().child("Write OUT").child(names.get(i));
            fornamevalueskey.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                        namevalueskey.get(i).add(postSnapshot.getKey());
                    }

                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            /*for (j = 0; j < namevalueskey.size(); j++) {
                DatabaseReference fornamevalues = FirebaseDatabase.getInstance().getReference().child("Write OUT").child(names.get(i)).child(namevalueskey.get(j)).child("name");
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
            namevalueskey.clear();*/
        }
        for(j=0;j< names.size();j++) {
            for (i = 0; i < namevalueskey.get(j).size(); i++) {
                DatabaseReference fornamevalues = FirebaseDatabase.getInstance().getReference().child("Write OUT").child(names.get(i)).child(namevalueskey.get(j).get(i)).child("name");
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
        return namevalues;
    }
}

package com.gigawattstechnology.writeout;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class database {
    public static long r2;
    public static ArrayList<String> namevalueskey=new ArrayList<>();
    public static ArrayList<String> namevalues=new ArrayList<>();
    private static ArrayList<String> keysdata=new ArrayList<>();
    private static ArrayList<String> usersdata=new ArrayList<>();
    private static long r;
    public static ArrayList<String> give(){
       /* DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
        databaseReference.addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    keysdata.add(postSnapshot.getKey());
                }
                r = snapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        for (int i = 0; i < r; i++) {
            DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference().child("Users").child(keysdata.get(i)).child("name");
            databaseReference1.addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    usersdata.add(snapshot.getValue(String.class));
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }*/


        //for(int i=0;i<r;i++) {
                DatabaseReference fornamevalueskey = FirebaseDatabase.getInstance().getReference().child("Write OUT").child("satwikplay69");
                fornamevalueskey.addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                            namevalueskey.add(postSnapshot.getKey());
                        }
                        r2 = snapshot.getChildrenCount();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                for (int j = 0; j < r2; j++) {

                    DatabaseReference fornamevalues = FirebaseDatabase.getInstance().getReference().child("Write OUT").child("satwikplay69").child(namevalueskey.get(j)).child("name");
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
        //}
        return namevalues;
    }
}

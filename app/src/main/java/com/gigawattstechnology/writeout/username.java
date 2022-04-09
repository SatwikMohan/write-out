package com.gigawattstechnology.writeout;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class username {

    private static int l;
    private static  ArrayList<String> keysdata=new ArrayList<>();
    private static ArrayList<String> usersdata=new ArrayList<>();
    private static long r;
public static void start(){
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
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
    }
}

    public static long size(){
        return r;
    }
    public static String give(int position){
    return usersdata.get(position);
    }
    /*public static ArrayList<String> give(){
        return users;
    }
    public static String giveat(int position){
        return users.get(position);
    }
    public static int givesize(){
        return l;
    }*/
}

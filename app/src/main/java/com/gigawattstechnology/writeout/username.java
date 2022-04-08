package com.gigawattstechnology.writeout;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class username {
    private static  ArrayList<String> keys=new ArrayList<>();
    private static ArrayList<String> users=new ArrayList<>();
    private static int l;
    private static long r;
    public static void start() {


        l = users.size();
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

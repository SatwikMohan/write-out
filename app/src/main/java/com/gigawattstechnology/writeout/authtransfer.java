package com.gigawattstechnology.writeout;

import android.widget.TextView;

public class authtransfer {
    private static String name,url,key,username;
    private static int articlepublished;
    private static int num,totalfavorites;
    public static void storename(String storename){
        name=storename;
    }
    public static String givename(){
        return name;
    }
    public static void storeurl(String storeurl){
        url=storeurl;
    }
    public  static String giveurl(){
        return url;
    }
    public static void storekey(String storekey){
        key=storekey;
    }
    public static String givekey(){
        return key;
    }
    public static void storeusername(String storeusername){
        username=storeusername;
    }
    public static String giveusername(){
        return username;
    }
    public static void storerating(int number){num=number;}
    public static int giverating(){return num;}
    public static void storearticlepublished(int storearticlepublished){articlepublished=storearticlepublished;}
    public static int givearticlepublished(){return articlepublished;}
    public static void storetotalfavorites(int storetotalfavorites){totalfavorites=storetotalfavorites;}
    public static int givetotalfavorites(){return totalfavorites;}
}

package com.gigawattstechnology.writeout;

import android.widget.TextView;

public class authtransfer {
    private static String name,url,key;
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
}

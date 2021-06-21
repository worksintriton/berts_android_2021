package com.triton.bertsproject.sessionmanager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class Connectivity {

//    Context context;
//    String key,value;
//
//    public Connectivity(Context context,String key,String value) {
//        this.context = context;
//        this.key = key;
//        this.value = value;
//    }

    @SuppressLint("LongLogTag")
    public void storeData(Context context, String key, String value){

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key,value);
//        Log.w("Connectivity Class Store"," Key : "+ key + " value : "+ value);
        editor.apply();
    }

    public String getData(Context context, String key){

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String value = preferences.getString(key, "");
//        Log.w("Connectivity Class Get"," Key : "+ key + " value : "+ value);
        if(value!=null&&!value.isEmpty())
        {
          return value;
        }
        else {

            return "";
        }
    }

    public void clearData(Context context, String key){

        SharedPreferences settings = context.getSharedPreferences(key, 0);
        settings.edit().remove(key).apply();
    }


}

package com.saad.youssif.e3rfmodrsk.SharedData;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {

    private static final String SHARED_PREF_NAME = "Login";
    private static final String TAG_TYPE = "type";
    private static final String TAG_STD_NAME = "std_name";
    private static final String TAG_TCH_NAME = "tch_name";
    private static final String TAG_ID = "id";
    private static final String TAG_PASSWORD = "password";
    private static final String TAG_PHONE = "phone";
    private static final String TAG_LOCATION = "location";

    private String std_name;
    private String tch_name;
    private String type;
    private String id;
    private String password;
    private String tch_phone;



    private String tch_location;


    private static SharedPrefManager mInstance;
    private static Context mCtx;

    private SharedPrefManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    public String getStd_name() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return  sharedPreferences.getString(TAG_STD_NAME, null);
    }

    public void setStd_name(String std_name) {
        this.std_name = std_name;
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TAG_STD_NAME,std_name);
        editor.apply();
    }

    public String getTch_name() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return  sharedPreferences.getString(TAG_TCH_NAME, null);    }

    public void setTch_name(String tch_name) {
        this.tch_name = tch_name;
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TAG_TCH_NAME,tch_name);
        editor.apply();
    }

    public String getType() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return  sharedPreferences.getString(TAG_TYPE, null);    }

    public void setType(String type) {
        this.type = type;
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TAG_TYPE,type);
        editor.apply();    }

    public String getId() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return  sharedPreferences.getString(TAG_ID, null);    }

    public void setId(String id) {
        this.id = id;
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TAG_ID,id);
        editor.apply();     }

    public String getPassword() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return  sharedPreferences.getString(TAG_PASSWORD, null);    }

    public void setPassword(String password) {
        this.password = password;
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TAG_PASSWORD,password);
        editor.apply();     }

    public String getTch_phone() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return  sharedPreferences.getString(TAG_PHONE, null);    }

    public void setTch_phone(String tch_phone) {
        this.tch_phone = tch_phone;
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TAG_PHONE,tch_phone);
        editor.commit();
    }

    public String getTch_location() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return  sharedPreferences.getString(TAG_LOCATION, "no location");    }

    public void setTch_location(String tch_location) {
        this.tch_location = tch_location;
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TAG_LOCATION,tch_location);
        editor.commit();
    }


    public void clearSharedData()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear().commit();

    }


}

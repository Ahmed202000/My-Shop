package com.hegazy.myshop.data.locale;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.hegazy.myshop.data.model.loginModel.LoginModel;
import com.hegazy.myshop.data.model.loginModel.LoginModelData;


public class SharedPreferencesManger {

    private static SharedPreferences sharedPreferences = null;
    public static String USER_PASSWORD = "USER_PASSWORD";
    public static String REMEMBER = "REMEMBER";

    public static void setSharedPreferences(Activity activity) {
        if (sharedPreferences == null) {
            sharedPreferences = activity.getSharedPreferences(
                    "My Shop", Context.MODE_PRIVATE);
        }
    }

    public static void SaveData(Activity activity, String data_Key, String data_Value) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(data_Key, data_Value);
            editor.commit();
        } else {
            setSharedPreferences(activity);
        }
    }

    public static void SaveDatab(Activity activity, String data_Key, boolean data_Value) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(data_Key, data_Value);
            editor.commit();
        } else {
            setSharedPreferences(activity);
        }
    }

    public static String LoadData(Activity activity, String data_Key) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
        } else {
            setSharedPreferences(activity);
        }

        return sharedPreferences.getString(data_Key, null);
    }

    public static boolean LoadBoolean(Activity activity, String data_Key) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
        } else {
            setSharedPreferences(activity);
        }

        return sharedPreferences.getBoolean(data_Key, false);
    }

    public static void SaveData(Activity activity, String data_Key, Object data_Value) {
        setSharedPreferences(activity);
        if (sharedPreferences != null)
        {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            Gson gson = new Gson();
            String StringData = gson.toJson(data_Value);
            editor.putString(data_Key, StringData);
            editor.commit();
        }
    }

    public static void saveUserData(Activity activity,String key, LoginModelData userData) {
        SaveData(activity, key, userData);
    }


    public static LoginModelData loadUserData(Activity activity , String key) {
        setSharedPreferences(activity);
        LoginModelData userData = null;
        Gson gson = new Gson();
        userData = gson.fromJson(LoadData(activity, key), LoginModelData.class);

        return userData;
    }



    public static void clean(Activity activity) {
        setSharedPreferences(activity);
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.commit();
        }
    }

}

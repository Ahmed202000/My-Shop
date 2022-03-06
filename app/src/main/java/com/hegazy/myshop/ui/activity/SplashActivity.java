package com.hegazy.myshop.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.hegazy.myshop.R;
import com.hegazy.myshop.data.model.loginModel.LoginModel;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;
import static com.hegazy.myshop.data.api.RetrofitClient.getClient;
import static com.hegazy.myshop.data.locale.Const.FIREBASE_TOKEN;
import static com.hegazy.myshop.data.locale.Const.USER_DATA;
import static com.hegazy.myshop.data.locale.SharedPreferencesManger.SaveData;
import static com.hegazy.myshop.data.locale.SharedPreferencesManger.loadUserData;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splach);


        // Add code to print out the key hash
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.hegazy.specialis",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }


        getFirebaseToken();




        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (loadUserData(SplashActivity.this, USER_DATA) == null)
                {
                    startActivity(new Intent(SplashActivity.this, AuthActivity.class));
                    finish();
                }
                else  if (loadUserData(SplashActivity.this, USER_DATA) != null)
                {
                    startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                    finish();
                }


            }
        },2000);
    }


    public void getFirebaseToken() {

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();
                        SaveData(SplashActivity.this, FIREBASE_TOKEN, token);

                    }
                });



    }





}
package com.hegazy.myshop.ui.activity;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.hegazy.myshop.helpar.ConfigurationFile;
import com.hegazy.myshop.ui.fragment.BaseFragment;

import java.util.Locale;

public class BaseActivity extends AppCompatActivity {

    public BaseFragment baseFragment;

    @Override
    public void onBackPressed() {
        baseFragment.onBack();
    }

    public void superBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updateBaseContextLocale(this);
        createNotificationChanel();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void createNotificationChanel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(getPackageName(), "Notification chanel",
                    NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(updateBaseContextLocale(base));
    }

    private Context updateBaseContextLocale(Context context) {
        Locale locale = new Locale(ConfigurationFile.getCurrentLanguage(context));
        Locale.setDefault(locale);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return updateResourcesLocale(context, locale);
        }

        return updateResourcesLocaleLegacy(context, locale);
    }

    @TargetApi(Build.VERSION_CODES.N)
    private Context updateResourcesLocale(Context context, Locale locale) {
        Configuration configuration = context.getResources().getConfiguration();
        configuration.setLocale(locale);
        return context.createConfigurationContext(configuration);
    }

    @SuppressWarnings("deprecation")
    private Context updateResourcesLocaleLegacy(Context context, Locale locale) {
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        return context;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
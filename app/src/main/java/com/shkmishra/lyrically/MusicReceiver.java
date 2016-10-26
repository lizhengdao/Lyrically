package com.shkmishra.lyrically;

import android.app.ActivityManager;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;


public class MusicReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {


        Bundle extras  = intent.getExtras();
        boolean isPlaying = extras.getBoolean(extras.containsKey("playstate") ? "playstate" : "playing", true);


        boolean isRunning = false;

        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if ("com.shkmishra.lyrically.LyricsService".equals(service.service.getClassName())) {
                isRunning = true;
            }
        }

        if(isPlaying) {
            Intent intent1 = new Intent(context, LyricsService.class);
            if(!isRunning)
            context.startService(intent1);
        }

        else{
            final NotificationManager mNotifyManager =
                                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                        mNotifyManager.cancel(26181317);
                        Intent intent1 = new Intent(context,LyricsService.class);
            context.stopService(intent1);

        }

    }


}

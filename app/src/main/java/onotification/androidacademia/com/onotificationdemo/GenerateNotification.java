package onotification.androidacademia.com.onotificationdemo;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;

/**
 * Created by girishkumarshakya on 31/03/18.
 */

public class GenerateNotification {
    public static final String CHANNEL_ID = "n_channel";
    public static final String CHANNEL_NAME = "Channel Name";
    private Context context;

    public GenerateNotification(Context context){
        this.context = context;
    }

    public boolean getOSVersion(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            return true;
        else
            return false;
    }

    public NotificationChannel getNotificationChannel(){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            channel.enableLights(true);
            channel.enableVibration(true);
            channel.setLightColor(R.color.colorAccent);
            return  channel;
        }else {
            return null;
        }
    }


    public void sendNotification(String title, String message, Intent intent){
        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        NotificationCompat.Builder mBuild = null;
        if (getOSVersion()){
            mBuild = new NotificationCompat.Builder(context,CHANNEL_ID);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationManager.createNotificationChannel(getNotificationChannel());
            }
        }else {
            mBuild = new NotificationCompat.Builder(context);
        }
        mBuild.setContentTitle(title);
        mBuild.setContentText(message);
        mBuild.setSmallIcon(R.drawable.ic_stat_name);
        mBuild.setContentIntent(pendingIntent);

        notificationManager.notify(1234,mBuild.build());
    }
}

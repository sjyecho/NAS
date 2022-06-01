package com.example.app6;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;

import androidx.core.app.NotificationCompat;

/**
 * @ClassName MyBroadcastReceiver
 * @Description TODO BroadcastReceiver,发送广播时出现弹窗提示
 * @Auther Shui Junyang
 * @Date 2022/5/27 上午10:40
 * @Version 1.0
 */
public class MyBroadcastReceiver extends BroadcastReceiver {

    static final int NOTIFICATION_ID = 0x1123;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.sendNotification(context,"BroadcastReceiver发送的通知");
    }

    private void sendNotification(Context context, String message) {
        NotificationChannel channel = new NotificationChannel("channel_1", "123", NotificationManager.IMPORTANCE_HIGH);
        channel.enableLights(true);
        channel.enableLights(true);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);

        PendingIntent pi = PendingIntent.getActivity(context, 0, new Intent(), 0);

        Notification notification=new NotificationCompat.Builder(context,"channel_1")
                .setContentTitle("Receiver发来通知")
                .setContentText(message)
                .setSubText("SubText的内容")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.mipmap.ic_launcher))
                .setWhen(System.currentTimeMillis())
                .setPriority(NotificationCompat.PRIORITY_LOW)//优先级
                .setOngoing(false)
                .setAutoCancel(true)
                .setTicker("setTicker的内容")
                .setContentIntent(pi)//如不设置此项，则通知不能响应点击事件。点击后通知也不会消失。
                .build();
        notificationManager.notify(NOTIFICATION_ID,notification);
    }
}

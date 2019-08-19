package pxgd.hyena.com.remindlist;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Date;

public class RemindReceiver extends BroadcastReceiver {

    public static final String REMINDER_TEXT = "REMINDER_TEXT";


    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d(RemindActivity.TAG, "RemindReceiver.onReceive！");

        //创建意图
        Intent i = new Intent(context, RemindActivity.class);
        //封装意图（getActivity为跳转到意图所指的活动）
        //requestCode不同则表示不是同一个PendingIntent
        PendingIntent pending = PendingIntent.getActivity(context, 0, i,  0);
        //创建通知对象
        String message = intent.getStringExtra(REMINDER_TEXT);
        Notification notification = new Notification.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("Reminder!")
                .setWhen(new Date().getTime())
                .setContentText(message)
                .setContentIntent(pending)
                .build();
        //得到系统通知服务的引用
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        //发送通知（通知ID=1）
        manager.notify(1, notification);
    }
}

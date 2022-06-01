package com.example.app6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    MyBroadcastReceiver myBroadcastReceiver;
    IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myBroadcastReceiver=new MyBroadcastReceiver();
        intentFilter=new IntentFilter("com.example.MYBROADCASTRECEIVER");

        Button send=findViewById(R.id.send);
        send.setOnClickListener(v -> {
            Intent intent=new Intent();
            intent.setPackage(getPackageName());
            intent.setAction("com.example.MYBROADCASTRECEIVER");
            sendBroadcast(intent);
        });
    }
}
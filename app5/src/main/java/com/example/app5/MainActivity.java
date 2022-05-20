package com.example.app5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.app5.fragment.ClassicDialogDemo;
import com.example.app5.fragment.CustomDialogFragmentDemo;
import com.example.app5.fragment.DialogFragmentDemo;
import com.example.app5.fragment.TopDialogFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.bt2).setOnClickListener(this);
        findViewById(R.id.bt3).setOnClickListener(this);
        findViewById(R.id.bt4).setOnClickListener(this);
        findViewById(R.id.bt1).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt4:
                new DialogFragmentDemo().show(getSupportFragmentManager(), "1");
                break;
            case R.id.bt2:
                new CustomDialogFragmentDemo().show(getSupportFragmentManager(), "2");
                break;
            case R.id.bt3:
                new ClassicDialogDemo().show(getSupportFragmentManager(), "3");
                break;
            case R.id.bt1:
                new TopDialogFragment().show(getSupportFragmentManager(),"4");
                break;
            default:
                break;
        }
    }
}
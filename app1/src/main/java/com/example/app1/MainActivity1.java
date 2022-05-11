package com.example.app1;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity1 extends AppCompatActivity implements SensorEventListener {

    private static final String TAG = "aaa";

    //定义系统的Sensor管理器
    SensorManager sensorManager;
    EditText editText;
    ImageView imageView;
    int x, y, z;

    boolean isA = true;
    boolean isB = true;
    boolean isC = true;
    boolean isD = true;

    /*
        这两个变量用于确定手机当前处于哪个转向
     */
    int locationX = 0;
    int locationY = 9;

    ObjectAnimator animator = new ObjectAnimator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        editText = findViewById(R.id.edittext);
        imageView = findViewById(R.id.image);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //为系统的加速度传感器注册监听
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_UI);

    }

    @Override
    protected void onStop() {
        //取消注册
        sensorManager.unregisterListener(this);
        super.onStop();
    }

    //当传感器的值发生改变时回调该方法
    @Override
    public void onSensorChanged(SensorEvent event) {

        float[] values = event.values;
        StringBuilder sb = new StringBuilder();

        sb.append("X方向上的加速度：");
        sb.append(values[0]);

        sb.append("\nY方向上的加速度：");
        sb.append(values[1]);

        sb.append("\nZ方向上的加速度：");
        sb.append(values[2]);

        editText.setText(sb.toString());

        x = (int) values[0];
        y = (int) values[1];
        z = (int) values[2];

        /*
            记录手机当前处于哪个转向
         */
        if (x == 9) {//左
            locationX = x;
        } else if (y == 9) {//正
            locationY = y;
        } else if (y == -9) {//倒
            locationY = y;
        } else if (x == -9) {//右
            locationX = x;
        }

        if (locationY == 9) {//此时是正方向
            if (x == 9 && isA) {//向左
                isA = false;
                isB = true;
                isC = true;
                isD = true;
                animator = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 90f);
                animator.setDuration(500);
                animator.start();
                locationX = 9;
                locationY = 0;
            } /*else if (y == 9 && isB) {//转正
                isB = false;
                isA = true;
                isC = true;
                isD = true;
                animator = ObjectAnimator.ofFloat(imageView, "rotation", 90f, 0f);
                animator.setDuration(500);
                animator.start();
                locationY = 9;
                Log.d(TAG, "onSensorChanged: y == 9 && isB 条件下，locationY 的值: " + locationY);
                Toast.makeText(this, "逆时针--90°--转正", Toast.LENGTH_SHORT).show();
            } */
            if (y == -9 && isC) {//倒过来
                isC = false;
                isB = true;
                isA = true;
                isD = true;
                animator = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 180f);
                animator.setDuration(500);
                animator.start();
                locationY = -9;
                locationX = 0;
            }
            if (x == -9 && isD) {//向右
                isD = false;
                isB = true;
                isA = true;
                isC = true;
                animator = ObjectAnimator.ofFloat(imageView, "rotation", 0f, -90f);
                animator.setDuration(500);
                animator.start();
                locationX = -9;
                locationY = 0;
            }
        }

        if (locationX == -9) {//此时处于右方向
            if (x == 9 && isA) {//右方向 -> 左方向
                isA = false;
                isB = true;
                isC = true;
                isD = true;
                animator = ObjectAnimator.ofFloat(imageView, "rotation", -90f, 90f);
                animator.setDuration(500);
                animator.start();
                locationX = 9;
                locationY = 0;
            }
            if (y == 9 && isB) {//转正
                isB = false;
                isA = true;
                isC = true;
                isD = true;
                animator = ObjectAnimator.ofFloat(imageView, "rotation", -90f, 0f);
                animator.setDuration(500);
                animator.start();
                locationY = 9;
                locationX = 0;
            }
            if (y == -9 && isC) {//右方向->倒方向
                isC = false;
                isB = true;
                isA = true;
                isD = true;
                animator = ObjectAnimator.ofFloat(imageView, "rotation", -90f, -180f);
                animator.setDuration(500);
                animator.start();
                locationY = -9;
                locationX = 0;
            }
            /*if (x == -9 && isD) {//向右
                isD = false;
                isB = true;
                isA = true;
                isC = true;
                animator = ObjectAnimator.ofFloat(imageView, "rotation", 0f, -90f);
                animator.setDuration(500);
                animator.start();
                locationX = -9;
                locationY = 0;
//                Log.d(TAG, "onSensorChanged: x == -9 && isD 条件下，locationX 的值: " + locationX);
//                Toast.makeText(this, "顺时针---90°", Toast.LENGTH_SHORT).show();
            }*/
        }

        if (locationX == 9) {//此时处于左方向
            /*if (x == 9 && isA) {//向左
                isA = false;
                isB = true;
                isC = true;
                isD = true;
                animator = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 90f);
                animator.setDuration(500);
                animator.start();
                locationX = 9;
                Log.d(TAG, "onSensorChanged: x == 9 && isA 条件下，locationX 的值: " + locationX);
                Toast.makeText(this, "逆时针--90°", Toast.LENGTH_SHORT).show();
            }*/
            if (y == 9 && isB) {//转正
                isB = false;
                isA = true;
                isC = true;
                isD = true;
                animator = ObjectAnimator.ofFloat(imageView, "rotation", 90f, 0f);
                animator.setDuration(500);
                animator.start();
                locationY = 9;
                locationX = 0;
            }
            if (y == -9 && isC) {//左方向->倒方向
                isC = false;
                isB = true;
                isA = true;
                isD = true;
                animator = ObjectAnimator.ofFloat(imageView, "rotation", 90f, 180f);
                animator.setDuration(500);
                animator.start();
                locationY = -9;
                locationX = 0;
            }
            if (x == -9 && isD) {//从左方向到右方向
                isD = false;
                isB = true;
                isA = true;
                isC = true;
                animator = ObjectAnimator.ofFloat(imageView, "rotation", 0f, -90f);
                animator.setDuration(500);
                animator.start();
                locationX = -9;
                locationY = 0;
            }
        }

        if (locationY == -9) {//此时处于倒方向
            if (x == 9 && isA) {//从倒方向到左方向
                isA = false;
                isB = true;
                isC = true;
                isD = true;
                animator = ObjectAnimator.ofFloat(imageView, "rotation", 180f, 90f);
                animator.setDuration(500);
                animator.start();
                locationX = 9;
            }
            if (y == 9 && isB) {//转正
                isB = false;
                isA = true;
                isC = true;
                isD = true;
                animator = ObjectAnimator.ofFloat(imageView, "rotation", 90f, 0f);
                animator.setDuration(500);
                animator.start();
                locationY = 9;
                locationX = 0;
            }
            /*if (y == -9 && isC) {//倒过来
                isC = false;
                isB = true;
                isA = true;
                isD = true;
                animator = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 180f);
                animator.setDuration(500);
                animator.start();
                locationY = -9;
                locationX = 0;
//                Log.d(TAG, "onSensorChanged: y == -9 && isC 条件下，locationY 的值: " + locationY);
//                Toast.makeText(this, "3333333333333333333333", Toast.LENGTH_SHORT).show();
            }*/
            if (x == -9 && isD) {//从倒方向到右方向
                isD = false;
                isB = true;
                isA = true;
                isC = true;
                animator = ObjectAnimator.ofFloat(imageView, "rotation", -180f, -90f);
                animator.setDuration(500);
                animator.start();
                locationX = -9;
                locationY = 0;
            }
        }

//        if (x == 9 && isA) {
//            isA = false;
//            isB = true;
//            isC = true;
//            isD = true;
//            animator = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 90f);
//            animator.setDuration(500);
//            animator.start();
//            locationX = 9;
//            Log.d(TAG, "onSensorChanged: x == 9 && isA 条件下，locationX 的值: " + locationX);
//            Toast.makeText(this, "逆时针--90°", Toast.LENGTH_SHORT).show();
//        } else if (y == 9 && isB) {
//            isB = false;
//            isA = true;
//            isC = true;
//            isD = true;
//            animator = ObjectAnimator.ofFloat(imageView, "rotation", 90f, 0f);
//            animator.setDuration(500);
//            animator.start();
//            locationY = 9;
//            Log.d(TAG, "onSensorChanged: y == 9 && isB 条件下，locationY 的值: " + locationY);
//            Toast.makeText(this, "逆时针--90°--转正", Toast.LENGTH_SHORT).show();
//        } else if (y == -9 && isC) {
//            isC = false;
//            isB = true;
//            isA = true;
//            isD = true;
//            animator = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 180f);
//            animator.setDuration(500);
//            animator.start();
//            locationY = -9;
//            Log.d(TAG, "onSensorChanged: y == -9 && isC 条件下，locationY 的值: " + locationY);
//            Toast.makeText(this, "3333333333333333333333", Toast.LENGTH_SHORT).show();
//        } else if (x == -9 && isD) {
//            isD = false;
//            isB = true;
//            isA = true;
//            isC = true;
//            animator = ObjectAnimator.ofFloat(imageView, "rotation", 0f, -90f);
//            animator.setDuration(500);
//            animator.start();
//            locationX = -9;
//            Log.d(TAG, "onSensorChanged: x == -9 && isD 条件下，locationX 的值: " + locationX);
//            Toast.makeText(this, "顺时针---90°", Toast.LENGTH_SHORT).show();
//        }
    }

    //传感器精度改变时回调该方法
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
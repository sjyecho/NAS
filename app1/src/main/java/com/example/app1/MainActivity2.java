package com.example.app1;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.OrientationEventListener;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    EditText editText;
    ImageView imageView;
    int durationTime = 250;//旋转动画持续时间
    float startRotation = 0f;//旋转开始角度
    float rotation = 0f;//旋转结束角度
    boolean isAnimate = false;//标志位，用于控制动画不会反复执行

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        editText = findViewById(R.id.edittext);
        imageView = findViewById(R.id.image);
        new MyOrientoinListener(this, SensorManager.SENSOR_DELAY_FASTEST).enable();
    }

    class MyOrientoinListener extends OrientationEventListener {

        public MyOrientoinListener(Context context, int rate) {
            super(context, rate);
        }

        @Override
        public void onOrientationChanged(int orientation) {

            //Log.d(TAG, "orientation：" + orientation );

            if (orientation > 340 || orientation < 20) {//正向
                editText.setText("当前设备方向为: 正向");
                rotation = 0f;
                startAnimator();
            }

            if (orientation > 70 && orientation < 110) {//右向
                editText.setText("当前设备方向为: 右向");
                rotation = 270f;
                startAnimator();
            }

            if (orientation > 250 && orientation < 290) {//左向
                editText.setText("当前设备方向为: 左向");
                rotation = 90f;
                startAnimator();
            }

            if (orientation > 160 && orientation < 200) {//倒向
                editText.setText("当前设备方向为: 倒向");
                rotation = 180f;
                startAnimator();
            }
        }
    }

    private void startAnimator(){
        if (!isAnimate && startRotation != rotation){

            if (startRotation == 90f && rotation == 360f){
                rotation =0f;
            }
            if (startRotation == 270f && rotation == 0f){
                rotation = 360f;
            }

            if (startRotation == 360f && rotation == 90f){
                startRotation = 0f;
            }

            if (startRotation == 0f && rotation == 270f){
                startRotation=360f;
            }
            ObjectAnimator animator = ObjectAnimator.ofFloat(
                    imageView,
                    "rotation",
                    startRotation,
                    rotation);
            animator.addListener(animatorListener);
            animator.setDuration(durationTime);
            animator.start();
        }
    }

    private Animator.AnimatorListener animatorListener = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animation) {
            isAnimate = true;
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            startRotation = rotation;
            //lastRotation = rotation;
            isAnimate = false;
        }

        @Override
        public void onAnimationCancel(Animator animation) {
            isAnimate = false;
        }

        @Override
        public void onAnimationRepeat(Animator animation) {
            isAnimate = true;
        }
    };
}
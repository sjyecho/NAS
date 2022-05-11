package com.example.app1;

import androidx.appcompat.app.AppCompatActivity;

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
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private static final String TAG = "MainActivity";

    //定义系统的Sensor管理器
    SensorManager sensorManager;
    EditText editText;
    ImageView imageView;
    int x,y,z;
    boolean isA=true;
    boolean isB=true;
    boolean isC=true;
    boolean isD=true;

    ObjectAnimator animator=new ObjectAnimator();

    /**
     * public RotateAnimation(float fromDegrees, float toDegrees, int pivotXType, float pivotXValue,int pivotYType, float pivotYValue)
     * float fromDegrees：旋转的起始点(旋转开始的角度)
     * float toDegrees：旋转的结束点(旋转最终角度)
     * int pivotXType：动画在X轴相对于物件位置类型，与下面的pivotXValue结合，确定X轴上旋转中心。
     *                  可能值为：
     *                          Animation.ABSOLUTE: 此参数为旋转中心在屏幕上X轴的值
     *                          Animation.RELATIVE_TO_SELF: 参数pivotXValue为旋转中心在控件自身水平位置百分比，如果X和Y的Value都设置为0.5，则该控件以自身中心旋转
     *                          Animation.RELATIVE_TO_PARENT: 参数pivotXValue为旋转中心在父控件水平位置百分比，如0.5表示在父控件水平方向中间位置
     * float pivotXValue：
     * int pivotYType：
     * float pivotYValue：
     */
    RotateAnimation rotate/*= new RotateAnimation(0f, 90f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)*/;
    RotateAnimation rotate2/*= new RotateAnimation(0f, 90f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)*/;
    RotateAnimation rotate3/*= new RotateAnimation(0f, 90f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)*/;
    RotateAnimation rotate4/*= new RotateAnimation(0f, 90f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)*/;
//    LinearInterpolator lin = new LinearInterpolator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=findViewById(R.id.edittext);
        imageView=findViewById(R.id.image);
        sensorManager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);

        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                super.onAnimationRepeat(animation);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }

            @Override
            public void onAnimationPause(Animator animation) {
                super.onAnimationPause(animation);
            }

            @Override
            public void onAnimationResume(Animator animation) {
                super.onAnimationResume(animation);
            }
        });

//        rotate.setDuration(500);//设置动画持续时间
//        rotate.setRepeatCount(0);//设置重复次数
//        rotate.setFillAfter(true);//动画执行完后是否停留在执行完的状态
//        rotate.setStartOffset(0);//执行前的等待时间
    }

    @Override
    protected void onResume() {
        super.onResume();
        //为系统的加速度传感器注册监听
        sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_UI);

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
        float[] values=event.values;
        StringBuilder sb=new StringBuilder();

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
        Log.d(TAG, "x=(int) values[0]---  " + x);
        if (x == 9 && isA) {
            isA = false;
            isB=true;
//            animator = ObjectAnimator.ofFloat(imageView, "rotation", 0, 90, 0);
//            animator.setDuration(500);
//            animator.setRepeatCount(0);
//            animator.start();
            rotate  = new RotateAnimation(0f, 90f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            rotate.setDuration(500);//设置动画持续时间
            rotate.setRepeatCount(0);//设置重复次数
            rotate.setFillAfter(true);//动画执行完后是否停留在执行完的状态
            rotate.setStartOffset(0);//执行前的等待时间
            //rotate.setInterpolator(lin);
            //imageView.setAnimation(rotate);
            imageView.startAnimation(rotate);
            //Toast.makeText(this,"imageView.startAnimation(rotate);下面",Toast.LENGTH_SHORT).show();
//            imageView.setRotation(90);
        }else if (y==9&& isB){
            isA = true;
            isB=false;
            rotate2  = new RotateAnimation(90f, 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//            //rotate.setInterpolator(lin);
            rotate2.setDuration(500);//设置动画持续时间
            rotate2.setRepeatCount(0);//设置重复次数
            rotate2.setFillAfter(true);//动画执行完后是否停留在执行完的状态
            rotate2.setStartOffset(0);//执行前的等待时间
//            //imageView.setAnimation(rotate);
            imageView.startAnimation(rotate2);
//            //imageView.setRotation(0);
        }else if (y== -9&& isC){
            isA = true;
//            rotate  = new RotateAnimation(0f, 180f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//            //rotate.setInterpolator(lin);
//            rotate.setDuration(500);//设置动画持续时间
//            rotate.setRepeatCount(1);//设置重复次数
//            rotate.setFillAfter(true);//动画执行完后是否停留在执行完的状态
//            rotate.setStartOffset(0);//执行前的等待时间
//            //imageView.setAnimation(rotate);
//            imageView.startAnimation(rotate);
//            //imageView.setRotation(180);
        }else if (x== -9&& isD){
            isA = true;
//            rotate  = new RotateAnimation(0f, -90f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
//            //rotate.setInterpolator(lin);
//            rotate.setDuration(500);//设置动画持续时间
//            rotate.setRepeatCount(1);//设置重复次数
//            rotate.setFillAfter(true);//动画执行完后是否停留在执行完的状态
//            rotate.setStartOffset(0);//执行前的等待时间
//            //imageView.setAnimation(rotate);
//            imageView.startAnimation(rotate);
//            //imageView.setRotation(-90);
        }
    }

    //传感器精度改变时回调该方法
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
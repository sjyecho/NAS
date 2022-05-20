package com.example.app2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView mContentTv;
    private static final String TAG = "aaaaa";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContentTv = findViewById(R.id.tv_content);

        new UserModel().mUserLiveData.postValue(new User(2, "name2"));

        //构建ViewModel实例
        final UserModel userModel = ViewModelProviders.of(this).get(UserModel.class);

        //userModel.mUserLiveData.postValue(new User(2, "name2"));

        Log.d("Echo", userModel.mUserLiveData + "");

        //让TextView观察ViewModel中数据的变化,并实时展示
        userModel.mUserLiveData.observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                mContentTv.setText(user.toString());
            }
        });

        /*
            点击按钮后,文字变换,age=15
            此时让手机屏幕旋转,Activity被重新创建,onCreate()方法被再次调用
            但是ViewModel其实没有重新创建,还是之前的那个ViewModel
            ViewModel的生命周期,只有在Activity销毁之后,它才会自动销毁(所以ViewModel不可以持有Activity引用,会造成内存泄漏)
         */
        findViewById(R.id.btn_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userModel.doSomething();
            }
        });

        NetworkLiveData.getInstance(this).observe(this, new Observer<NetworkInfo>() {
            @Override
            public void onChanged(@Nullable NetworkInfo networkInfo) {
                Toast.makeText(MainActivity.this, "NetworkLiveData->onChanged", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onChanged: networkInfo=" + networkInfo);
            }
        });
    }
}
package com.example.nas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;

import com.shuyu.gsyvideoplayer.GSYVideoBaseManager;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.video.base.GSYVideoViewBridge;

public class MainActivity extends AppCompatActivity {

    GSYVideoViewBridge gsyVideoManager = new GSYVideoBaseManager() {
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager2 viewPager2 = findViewById(R.id.viewpager);
        Pager_Two_Adapter adapter = new Pager_Two_Adapter(this, gsyVideoManager);
        viewPager2.setAdapter(adapter);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                Log.d("========", "onPageSelected position = " + position);
                Log.d("========", "onPageSelected getPlayPosition = " + GSYVideoManager.instance().getPlayPosition());
                if (GSYVideoManager.instance().getPlayPosition() != position){
                    GSYVideoManager.releaseAllVideos();
                }
                super.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }
}
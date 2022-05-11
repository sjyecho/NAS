package com.example.nas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.shuyu.gsyvideoplayer.video.base.GSYVideoViewBridge;

public class Pager_Two_Adapter extends RecyclerView.Adapter<Pager_Two_Adapter.PagerViewHolder> {

    private View view;
    private Context context;
    GSYVideoViewBridge gsyVideoManager;
    String source = "http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4";

    public Pager_Two_Adapter(Context context, GSYVideoViewBridge gsyVideoManager) {
        this.context = context;
        this.gsyVideoManager = gsyVideoManager;
    }

    /*
     * 创建ViewHolder时的回调函数
     * 传入ViewGroup parent, int viewType
     * 返回MyHolder
     * MyHolder是list中每个item
     * */
    @NonNull
    @Override
    public PagerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        PagerViewHolder holder = new PagerViewHolder(view);

        return holder;
    }

    /*
     * 绑定ViewHolder时的回调函数
     * 传入自定义内部类的 holder 和 int position
     * */
    @Override
    public void onBindViewHolder(@NonNull PagerViewHolder holder, int position) {

        holder.videoView.setUp(source, true, "测试视频" + (position + 1));
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(R.mipmap.img);
        holder.videoView.setThumbImageView(imageView);
        holder.videoView.setPlayPosition(position);
    }

    /*
     * 控制创建item的条数
     * */
    @Override
    public int getItemCount() {
        return 10;
    }

    /**
     * 作为主类的泛型
     * 构造方法：接收由布局生成的子View，并加载子View中的控件
     */
    class PagerViewHolder extends RecyclerView.ViewHolder {

        StandardGSYVideoPlayer videoView;

        public PagerViewHolder(@NonNull View itemView) {
            super(itemView);
            videoView = (StandardGSYVideoPlayer) itemView.findViewById(R.id.video_player);
        }
    }
}

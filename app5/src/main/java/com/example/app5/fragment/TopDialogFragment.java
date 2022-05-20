package com.example.app5.fragment;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.app5.R;

public class TopDialogFragment extends DialogFragment {

    private LinearLayout ll;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View dialogView = inflater.inflate(R.layout.top_dialog, container);
        ll = dialogView.findViewById(R.id.mView);
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) ll.getLayoutParams();
        params.topMargin = getStatusBarHeight(getDialog().getContext());
        ll.setLayoutParams(params);
        return dialogView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.DialogFullScreen);
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams windowParams = window.getAttributes();
        windowParams.dimAmount = 0.0f;//设置Dialog弹出后Activity背景不变暗
        window.setLayout(-1, -2); //高度自适应，宽度全屏
        windowParams.gravity = Gravity.TOP; //在顶部显示
        windowParams.windowAnimations = R.style.TopDialogAnimation;
        window.setAttributes(windowParams);
    }

    /**
     * 获取状态栏高度（范围 ： px）
     *
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        return resources.getDimensionPixelSize(resourceId) == 0 ? 60 : resources.getDimensionPixelSize(resourceId);
    }
}

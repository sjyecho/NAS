package com.example.app5.fragment;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.app5.R;

public class DialogFragmentDemo extends DialogFragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Window window = this.getDialog().getWindow();
        //去掉Dialog默认的padding
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        //设置Dialog的位置在底部
        params.gravity = Gravity.BOTTOM;
        //设置Dialog的动画
        params.windowAnimations = R.style.BottomDialogAnimation;
        window.setAttributes(params);
        window.setBackgroundDrawable(new ColorDrawable());
        //TODO Dialog弹出后背景不变暗
        window.setDimAmount(0f);
        View view = inflater.inflate(R.layout.bottom_dialog, container, false);
        view.findViewById(R.id.cancel).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel:
                dismiss();
        }
    }
}

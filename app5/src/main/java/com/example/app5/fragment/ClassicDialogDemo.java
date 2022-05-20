package com.example.app5.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class ClassicDialogDemo extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity())
                .setTitle("经典方式创建Dialog")
                .setPositiveButton("确定",null)
                .setMessage("重写onCreateDialog,其他就和Dialog设置方式相同");
        return builder.create();
    }
}

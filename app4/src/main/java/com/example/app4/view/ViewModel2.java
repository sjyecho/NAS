package com.example.app4.view;

import android.app.Application;
import android.view.View;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.app4.BR;
import com.example.app4.R;
import com.example.app4.callback.CallBack2;
import com.example.app4.databinding.Mvvm2Binding;
import com.example.app4.domain.Account2;
import com.example.app4.model.MvvmModel2;

public class ViewModel2 extends BaseObservable {

    private final MvvmModel2 mvvmModel;
    private String result2;
    Application application;
    Mvvm2Binding binding;

    public ViewModel2(Application application, Mvvm2Binding binding) {
        mvvmModel = new MvvmModel2();
        this.application = application;
        this.binding = binding;
    }

    @Bindable
    public String getResult2() {
        return result2;
    }

    public void setResult2(String result2) {
        this.result2 = result2;
        notifyPropertyChanged(BR.result2);
    }

    public void getData(View view) {
        switch (view.getId()) {
            case R.id.button:
                String input = binding.editText.getText().toString();
                int length = input.length();
                mvvmModel.setAccountData(input, new CallBack2() {
                    @Override
                    public void onSuccess(Account2 account) {
                        String info = account.getAccountName() + "|" + account.getAge();
                        setResult2(info);
                    }

                    @Override
                    public void onFailed() {
                        setResult2("数据读取失败");
                    }
                }, length);
                break;
            default:
                break;
        }
    }
}

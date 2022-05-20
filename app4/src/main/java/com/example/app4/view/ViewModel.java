package com.example.app4.view;

import android.app.Application;
import android.view.View;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.app4.BR;
import com.example.app4.R;
import com.example.app4.callback.CallBack;
import com.example.app4.databinding.ActivityMainBinding;
import com.example.app4.domain.Account;
import com.example.app4.model.MvvmModel;

public class ViewModel extends BaseObservable {
    private final MvvmModel mvvmModel;
    private String result;
    ActivityMainBinding binding;
    Application application;

    @Bindable
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
        notifyPropertyChanged(BR.result);
    }

    public ViewModel( Application application,ActivityMainBinding binding) {
        mvvmModel=new MvvmModel();
        this.binding = binding;
        this.application = application;
    }

    public void getData(View view){
        switch (view.getId()){
            case R.id.btn:
                String userInput=binding.etText.getText().toString();
                mvvmModel.setAccountData(userInput, new CallBack() {
                    @Override
                    public void onSuccess(Account account) {
                        String info=account.getName()+"|"+account.getLevel();
                        setResult(info);
                    }

                    @Override
                    public void onFailed() {
                        setResult("获取数据失败");
                    }
                });
                break;
            default:
                break;
        }
    }
}

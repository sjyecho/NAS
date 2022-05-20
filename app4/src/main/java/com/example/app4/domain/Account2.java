package com.example.app4.domain;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.app4.BR;


public class Account2 extends BaseObservable {

    private String accountName;
    private int age;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    @Bindable
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
        notifyPropertyChanged(BR.age);
    }
}

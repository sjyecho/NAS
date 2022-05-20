package com.example.app4.domain;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.app4.BR;

public class Account extends BaseObservable {

    private String name;
    private int level;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Bindable//用于数据更新自动刷新视图. 自动生成BR的ID
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
        notifyPropertyChanged(BR.level);
    }
}

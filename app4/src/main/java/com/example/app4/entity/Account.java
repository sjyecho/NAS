package com.example.app4.entity;

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

    @Bindable
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
        notifyPropertyChanged(BR.level);
    }
}

package com.example.app2;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserModel extends ViewModel {

    public final MutableLiveData<User> mUserLiveData=new MutableLiveData<>();

    public UserModel(){
        //模拟从网络加载用户信息
        mUserLiveData.postValue(new User(1,"name1"));
    }

    //模拟进行一下数据操作
    public void doSomething(){
        User user=mUserLiveData.getValue();
        if (user!=null){
            user.age=15;
            user.name="name15";
            mUserLiveData.setValue(user);
        }
    }
}

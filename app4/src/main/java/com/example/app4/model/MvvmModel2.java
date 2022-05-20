package com.example.app4.model;

import com.example.app4.callback.CallBack2;
import com.example.app4.domain.Account2;

import java.util.Random;

public class MvvmModel2 {

    public void setAccountData(String name, CallBack2 callBack,int length){
        if (length>3){
            Account2 account2=new Account2();
            account2.setAge(20);
            account2.setAccountName(name);
            callBack.onSuccess(account2);
        }else {
            callBack.onFailed();
        }
    }
}

package com.example.app4.model;

import com.example.app4.callback.CallBack;
import com.example.app4.domain.Account;

import java.util.Random;

public class MvvmModel {

    public void setAccountData(String accountName, CallBack callBack){
        Random random=new Random();
        boolean isSuccess= random.nextBoolean();
        if (isSuccess){
            Account account=new Account();
            account.setName(accountName);
            account.setLevel(100);
            callBack.onSuccess(account);
        }else {
            callBack.onFailed();
        }
    }
}

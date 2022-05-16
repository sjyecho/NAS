package com.example.app4.callback;

import com.example.app4.entity.Account;

/**
 * 数据返回接口
 */
public interface CallBack {
    void onSuccess(Account account);
    void onFailed();
}

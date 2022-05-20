package com.example.app4.callback;

import com.example.app4.domain.Account2;

/**
 * 数据返回接口
 */
public interface CallBack2 {

    void onSuccess(Account2 account);
    void onFailed();
}

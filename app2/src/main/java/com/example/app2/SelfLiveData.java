package com.example.app2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.lifecycle.LiveData;

public class SelfLiveData extends LiveData<NetworkInfo> {

    private final Context mContext;
    static SelfLiveData mSelfLiveData;
    private final NetworkLiveData.NetworkReceiver mNetworkReceiver;
    private final IntentFilter mIntentFilter;

    public SelfLiveData(Context Context) {
        mContext= Context.getApplicationContext();
        mNetworkReceiver=new NetworkLiveData.NetworkReceiver();
        mIntentFilter=new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
    }

    public static SelfLiveData getInstance(Context context){
        if (mSelfLiveData==null){
            mSelfLiveData=new SelfLiveData(context);
        }
        return mSelfLiveData;
    }

    @Override
    protected void onActive() {
        super.onActive();
        mContext.registerReceiver(mNetworkReceiver,mIntentFilter);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        mContext.unregisterReceiver(mNetworkReceiver);
    }

    static class NetworkReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager manager=(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork=manager.getActiveNetworkInfo();
            getInstance(context).setValue(activeNetwork);
        }
    }
}

package com.fsmc.app;

import android.app.Application;

import com.fsmc.app.network.NetworkDataProvider;
import com.fsmc.app.network.impl.NetworkDataProviderImpl;

public class FsmcApplication extends Application {

    private static NetworkDataProvider networkDataProvider;


    @Override
    public void onCreate() {
        super.onCreate();
        networkDataProvider = new NetworkDataProviderImpl(getApplicationContext());
    }

    public static NetworkDataProvider getNetworkDataProvider() {
        return networkDataProvider;
    }
}

package com.fsmc.app.ui.main.base;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.fsmc.app.FsmcApplication;
import com.fsmc.app.network.NetworkDataProvider;

public class BaseViewModel<T> extends ViewModel {

    protected final NetworkDataProvider networkDataProvider;
    protected final MutableLiveData<T> mutableData;

    public BaseViewModel() {
        this.networkDataProvider = FsmcApplication.getNetworkDataProvider();
        this.mutableData = new MutableLiveData<>();
    }

    protected NetworkDataProvider getNetworkDataProvider() {
        return networkDataProvider;
    }

    public LiveData<T> getMutableData() {
        return mutableData;
    }
}

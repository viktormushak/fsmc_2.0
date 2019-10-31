package com.fsmc.app.ui.base;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.fsmc.app.network.NetworkDataProvider;

import java.util.List;

public class BaseViewModel<T> extends ViewModel {

    protected final NetworkDataProvider networkDataProvider;
    protected final MutableLiveData<List<T>> mutableListData;

    public BaseViewModel(NetworkDataProvider networkDataProvider) {
        this.networkDataProvider = networkDataProvider;
        this.mutableListData = new MutableLiveData<>();
    }

    protected NetworkDataProvider getNetworkDataProvider() {
        return networkDataProvider;
    }

    public LiveData<List<T>> getMutableListData() {
        return mutableListData;
    }
}

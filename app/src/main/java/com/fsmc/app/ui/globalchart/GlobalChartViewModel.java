package com.fsmc.app.ui.globalchart;

import com.fsmc.app.data.model.Client;
import com.fsmc.app.network.NetworkDataProvider;
import com.fsmc.app.network.base.ResponseResultObserver;
import com.fsmc.app.ui.base.BaseViewModel;

import java.util.List;

public class GlobalChartViewModel extends BaseViewModel<Client> implements ResponseResultObserver<List<Client>> {

    public GlobalChartViewModel(NetworkDataProvider networkDataProvider) {
        super(networkDataProvider);
    }

    void loadClientList(){
        getNetworkDataProvider().loadClientList(this);
    }

    @Override
    public void observe(List<Client> response) {
        for (int i = 0; i < response.size(); i++) {
            response.get(i).setRate(i+1);
        }
        mutableListData.setValue(response);
    }
}

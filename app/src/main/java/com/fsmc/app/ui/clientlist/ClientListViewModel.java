package com.fsmc.app.ui.clientlist;

import android.text.TextUtils;

import com.fsmc.app.data.model.Client;
import com.fsmc.app.network.NetworkDataProvider;
import com.fsmc.app.network.base.ResponseResultObserver;
import com.fsmc.app.ui.base.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

public class ClientListViewModel extends BaseViewModel<Client> implements ResponseResultObserver<List<Client>> {

    private List<Client> unsearchableList = new ArrayList<>();

    public ClientListViewModel(NetworkDataProvider networkDataProvider) {
        super(networkDataProvider);
    }

    void loadClientList(String company){
        getNetworkDataProvider().loadClientList(company, this);
    }

    void loadClientDetailsList(){
        List<Client> clients = getMutableListData().getValue();
        if (clients != null) {
            for (Client client : clients) {
                networkDataProvider.loadClientDetails(client.getHashId(), response -> {});
            }
        }
    }

    boolean searchClients(String query) {
        if (TextUtils.isEmpty(query)){
            mutableListData.setValue(unsearchableList);
        } else {
            List<Client> searchResult = new ArrayList<>();
            for (Client client : unsearchableList) {
                if (client.getName().toLowerCase().contains(query.toLowerCase())){
                    searchResult.add(client);
                }
            }
            mutableListData.setValue(searchResult);
        }
        return false;
    }



    @Override
    public void observe(List<Client> response) {
        for (int i = 0; i < response.size(); i++) {
            response.get(i).setRate(i+1);
        }
        unsearchableList.addAll(response);
        mutableListData.setValue(unsearchableList);
    }
}

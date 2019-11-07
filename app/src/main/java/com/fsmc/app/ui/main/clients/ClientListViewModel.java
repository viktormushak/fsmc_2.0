package com.fsmc.app.ui.main.clients;

import android.text.TextUtils;

import com.fsmc.app.data.model.Client;
import com.fsmc.app.ui.main.base.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

public class ClientListViewModel extends BaseViewModel<List<Client>> {

    private List<Client> unsearchableList = new ArrayList<>();

    void loadClientList(String company){
        if (company == null){
            getNetworkDataProvider().loadClientList(response -> {
                setRates(response);
                unsearchableList.addAll(response);
                mutableData.setValue(response);
            });
        } else {
            getNetworkDataProvider().loadClientListByCompanyName(company, response -> {
                setRates(response);
                unsearchableList.addAll(response);
                mutableData.setValue(response);
            });
        }
    }

    private void setRates(List<Client> clientList) {
        for (int i = 0; i < clientList.size(); i++) {
            clientList.get(i).setRate(i+1);
        }
    }

    boolean searchClients(String query) {
        if (TextUtils.isEmpty(query)){
            mutableData.setValue(unsearchableList);
        } else {
            List<Client> searchResult = new ArrayList<>();
            for (Client client : unsearchableList) {
                if (client.getName().toLowerCase().contains(query.toLowerCase()) ||
                    client.getAddress().toLowerCase().contains(query.toLowerCase())){
                    searchResult.add(client);
                }
            }
            mutableData.setValue(searchResult);
        }
        return false;
    }
}

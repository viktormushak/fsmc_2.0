package com.fsmc.app.network;

import com.fsmc.app.data.model.Client;
import com.fsmc.app.data.model.ClientDetails;
import com.fsmc.app.data.model.Company;
import com.fsmc.app.network.base.ResponseResultObserver;

import java.util.List;

public interface NetworkDataProvider {
    void loadCompanyList(ResponseResultObserver<List<Company>> observer);
    void loadClientList(ResponseResultObserver<List<Client>> observer);
    void loadClientList(String company, ResponseResultObserver<List<Client>> observer);
    void loadClientDetails(int clientId, ResponseResultObserver<ClientDetails> observer);
    void clearCache();
}

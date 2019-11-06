package com.fsmc.app.network;

import com.android.volley.Response;
import com.fsmc.app.data.model.Client;
import com.fsmc.app.data.model.ClientData;
import com.fsmc.app.data.model.ClientDetails;
import com.fsmc.app.data.model.Company;
import com.fsmc.app.network.base.ResponseResultObserver;

import java.util.List;

public interface NetworkDataProvider {
    void loadCompanyList(ResponseResultObserver<List<Company>> observer);
    void loadClientList(ResponseResultObserver<List<Client>> observer);
    void loadClientList(String company, ResponseResultObserver<List<Client>> observer);
    void loadClientDetails(int clientId, ResponseResultObserver<ClientDetails> observer);
    void loadClientData(Integer integer, Response.Listener<ClientData> listener);
    void postClientData(ClientData clientData, Response.Listener<Boolean> listener);
    void clearCache();
}

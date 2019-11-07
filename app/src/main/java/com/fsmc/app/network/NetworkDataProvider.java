package com.fsmc.app.network;

import com.android.volley.Response;
import com.fsmc.app.data.model.Client;
import com.fsmc.app.data.model.ClientData;
import com.fsmc.app.data.model.ClientDetails;
import com.fsmc.app.data.model.Company;

import org.json.JSONObject;

import java.util.List;

public interface NetworkDataProvider {
    void loadCompanyList(Response.Listener<List<Company>> listener);
    void loadClientList(Response.Listener<List<Client>> listener);
    void loadClientListByCompanyName(String companyName, Response.Listener<List<Client>> listener);
    void loadClientDetailsByClientId(int clientId, Response.Listener<ClientDetails> listener);
    void postClientData(ClientData clientData, Response.Listener<ClientData> listener);
    void loadClientDataByClientId(int integer, Response.Listener<ClientData> listener);
}

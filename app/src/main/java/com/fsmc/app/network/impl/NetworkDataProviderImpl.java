package com.fsmc.app.network.impl;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.fsmc.app.data.model.Client;
import com.fsmc.app.data.model.ClientData;
import com.fsmc.app.data.model.ClientDetails;
import com.fsmc.app.data.model.Company;
import com.fsmc.app.network.NetworkDataProvider;
import com.fsmc.app.network.requests.GetClientDataRequest;
import com.fsmc.app.network.requests.GetClientDetailsCachedRequest;
import com.fsmc.app.network.requests.GetClientsCachedRequest;
import com.fsmc.app.network.requests.GetCompaniesCachedRequest;
import com.fsmc.app.network.requests.PostClientDataRequest;

import java.util.HashMap;
import java.util.List;

public class NetworkDataProviderImpl implements NetworkDataProvider {

    private static final int CACHE_SIZE = 1024 * 1024;
    private Context context;
    private RequestQueue requestQueue;

    public NetworkDataProviderImpl(Context applicationContext) {
        this.context = applicationContext;
    }

    private RequestQueue getRequestQueue(){
        if (requestQueue == null){
            requestQueue = new RequestQueue(
                    new DiskBasedCache(context.getCacheDir(), CACHE_SIZE),
                    new BasicNetwork(new HurlStack()));
            requestQueue.start();
        }
        return requestQueue;
    }

    @Override
    public void loadCompanyList(Response.Listener<List<Company>> listener) {
        getRequestQueue().add(new GetCompaniesCachedRequest(new HashMap<>(), listener));
    }

    @Override
    public void loadClientList(Response.Listener<List<Client>> listener) {
        getRequestQueue().add(new GetClientsCachedRequest(new HashMap<>(), listener));
    }

    @Override
    public void loadClientListByCompanyName(String companyName, Response.Listener<List<Client>> listener) {
        HashMap<String, String> params = new HashMap<>();
        params.put("company", companyName);
        getRequestQueue().add(new GetClientsCachedRequest(params, listener));
    }

    @Override
    public void loadClientDetailsByClientId(int clientId, Response.Listener<ClientDetails> listener) {
        HashMap<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(clientId));
        getRequestQueue().add(new GetClientDetailsCachedRequest(params, listener));
    }

    @Override
    public void postClientData(ClientData clientData, Response.Listener<ClientData> listener) {
        getRequestQueue().add(new PostClientDataRequest(clientData, new HashMap<>(), listener));
    }

    @Override
    public void loadClientDataByClientId(int clientId, Response.Listener<ClientData> listener) {
        HashMap<String, String> params = new HashMap<>();
        params.put("id", String.valueOf(clientId));
        getRequestQueue().add(new GetClientDataRequest(params, listener));
    }
}

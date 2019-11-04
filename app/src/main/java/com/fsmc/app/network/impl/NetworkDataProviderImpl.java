package com.fsmc.app.network.impl;

import android.content.Context;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.fsmc.app.data.model.Client;
import com.fsmc.app.data.model.ClientDetails;
import com.fsmc.app.data.model.Company;
import com.fsmc.app.network.NetworkDataProvider;
import com.fsmc.app.network.base.GsonListCachedRequest;
import com.fsmc.app.network.base.GsonObjectCachedRequest;
import com.fsmc.app.network.base.ResponseResultObserver;

import java.util.List;

public class NetworkDataProviderImpl implements NetworkDataProvider {

    private static final String BASE_URL = "http://91.226.253.178:6128";
    private static final int CACHE_SIZE = 1024 * 1024;
    private Context context;
    private Cache cache;
    private RequestQueue requestQueue;

    public NetworkDataProviderImpl(Context applicationContext) {
        this.context = applicationContext;
    }

    private RequestQueue getRequestQueue(){
        cache = new DiskBasedCache(context.getCacheDir(), CACHE_SIZE);
        if (requestQueue == null){
            requestQueue = new RequestQueue(cache, new BasicNetwork(new HurlStack())
            );
            requestQueue.start();
        }
        return requestQueue;
    }

    private <T> Request<List<T>> gsonListGetRequest(String url, Class<T> clazz, ResponseResultObserver<List<T>> resultObserver){
        return new GsonListCachedRequest<>(Request.Method.GET, url, clazz, resultObserver);
    }

    @SuppressWarnings("SameParameterValue")
    private <T> Request<T> gsonObjectGetRequest(String url, Class<T> clazz, ResponseResultObserver<T> resultObserver){
        return new GsonObjectCachedRequest<>(Request.Method.GET, url, clazz, resultObserver);
    }

    @Override
    public void loadCompanyList(ResponseResultObserver<List<Company>> resultObserver) {
        String url = BASE_URL + "/api/companies";
        getRequestQueue().add(gsonListGetRequest(url, Company.class, resultObserver));
    }

    @Override
    public void loadClientList(ResponseResultObserver<List<Client>> observer) {
        String url = BASE_URL + "/api/clients/global";
        getRequestQueue().add(gsonListGetRequest(url, Client.class, observer));
    }

    @Override
    public void loadClientList(String company, ResponseResultObserver<List<Client>> resultObserver) {
        String url = BASE_URL + "/api/clients?company=" + company;
        getRequestQueue().add(gsonListGetRequest(url, Client.class, resultObserver));

    }

    @Override
    public void loadClientDetails(int clientId, ResponseResultObserver<ClientDetails> resultObserver) {
        String url = BASE_URL + "/api/clients/details?id=" + clientId;
        getRequestQueue().add(gsonObjectGetRequest(url, ClientDetails.class, resultObserver));
    }

    @Override
    public void clearCache() {
        cache.clear();
    }
}

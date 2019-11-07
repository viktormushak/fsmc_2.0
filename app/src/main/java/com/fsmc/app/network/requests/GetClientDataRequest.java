package com.fsmc.app.network.requests;

import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.fsmc.app.data.model.ClientData;
import com.fsmc.app.network.base.AbsFsmcRequest;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

public class GetClientDataRequest extends AbsFsmcRequest<ClientData> {

    private final static String URL = "/api/clients/data";

    public GetClientDataRequest(HashMap<String, String> params, Response.Listener<ClientData> listener) {
        super(Method.GET, URL, params, listener, Throwable::printStackTrace);
    }

    @Override
    protected ClientData entry(NetworkResponse response) {
        try {
            return new Gson().fromJson(
                    new String(response.data, HttpHeaderParser.parseCharset(response.headers)),
                    ClientData.class);
        } catch (UnsupportedEncodingException e) {
            return ClientData.EMPTY;
        }
    }

    @Override
    protected Cache.Entry cacheEntry(NetworkResponse response) {
        return null;
    }
}

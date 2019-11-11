package com.fsmc.app.network.requests;

import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.fsmc.app.data.model.Address;
import com.fsmc.app.data.model.ClientData;
import com.fsmc.app.network.base.AbsFsmcRequest;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

public class GetClientAddressRequest extends AbsFsmcRequest<Address> {

    private final static String URL = "/api/clients/address";

    public GetClientAddressRequest(HashMap<String, String> params, Response.Listener<Address> listener) {
        super(Method.GET, URL, params, listener, Throwable::printStackTrace);
    }

    @Override
    protected Address entry(NetworkResponse response) {
        try {
            return new Gson().fromJson(
                    new String(response.data, HttpHeaderParser.parseCharset(response.headers)),
                    Address.class);
        } catch (UnsupportedEncodingException e) {
            return Address.EMPTY;
        }
    }

    @Override
    protected Cache.Entry cacheEntry(NetworkResponse response) {
        return null;
    }
}

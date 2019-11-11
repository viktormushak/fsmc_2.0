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

public class PostClientAddressRequest extends AbsFsmcRequest<Address> {

    private final static String URL = "/api/clients/address";
    private Address address;

    public PostClientAddressRequest(Address address, HashMap<String, String> params, Response.Listener<Address> listener, Response.ErrorListener errorListener) {
        super(Method.POST, URL, params, listener, errorListener);
        this.address = address;
    }

    @Override
    public String getBodyContentType() {
        return "application/json";
    }

    @Override
    public byte[] getBody() {
        return new Gson().toJson(address).getBytes();
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

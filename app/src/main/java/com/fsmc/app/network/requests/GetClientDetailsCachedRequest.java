package com.fsmc.app.network.requests;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.fsmc.app.data.model.ClientDetails;
import com.fsmc.app.network.base.AbsFsmcCachedRequest;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

public class GetClientDetailsCachedRequest extends AbsFsmcCachedRequest<ClientDetails> {

    private final static String URL = "";

    public GetClientDetailsCachedRequest(HashMap<String, String> params, Response.Listener<ClientDetails> listener) {
        super(Method.GET, URL, params, listener, Throwable::printStackTrace);
    }

    @Override
    protected ClientDetails entry(NetworkResponse response) {
        try {
            return new Gson().fromJson(
                    new String(response.data, HttpHeaderParser.parseCharset(response.headers)),
                    ClientDetails.class);
        } catch (UnsupportedEncodingException e) {
            return ClientDetails.EMPTY;
        }
    }
}
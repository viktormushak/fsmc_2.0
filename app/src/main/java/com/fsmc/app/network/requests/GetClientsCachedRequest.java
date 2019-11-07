package com.fsmc.app.network.requests;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.fsmc.app.data.model.Client;
import com.fsmc.app.network.base.AbsFsmcCachedRequest;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class GetClientsCachedRequest extends AbsFsmcCachedRequest<List<Client>> {

    private final static String URL = "/api/clients";

    public GetClientsCachedRequest(HashMap<String, String> params, Response.Listener<List<Client>> listener) {
        super(Method.GET, URL, params, listener, Throwable::printStackTrace);
    }

    @Override
    protected List<Client> entry(NetworkResponse response) {
        try {
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return new Gson().fromJson(json, (Type) Array.newInstance(Client.class, 0).getClass());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}

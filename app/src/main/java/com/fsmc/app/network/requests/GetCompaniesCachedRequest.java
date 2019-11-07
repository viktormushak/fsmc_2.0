package com.fsmc.app.network.requests;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.fsmc.app.data.model.Company;
import com.fsmc.app.network.base.AbsFsmcCachedRequest;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class GetCompaniesCachedRequest extends AbsFsmcCachedRequest<List<Company>> {

    private final static String URL = "/api/companies";

    public GetCompaniesCachedRequest(HashMap<String, String> params, Response.Listener<List<Company>> listener) {
        super(Method.GET, URL, params, listener, Throwable::printStackTrace);
    }

    @Override
    protected List<Company> entry(NetworkResponse response) {
        try {
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return new Gson().fromJson(json, (Type) Array.newInstance(Company.class, 0).getClass());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}

package com.fsmc.app.network.base;

import androidx.annotation.Nullable;

import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;

import java.util.HashMap;

public abstract class AbsFsmcRequest<R> extends Request<R> {

    private static final String BASE_URL = "http://91.226.253.178:6128";
    private Response.Listener<R> listener;

    protected AbsFsmcRequest(int method, String url, HashMap<String, String> params, Response.Listener<R> listener, @Nullable Response.ErrorListener errorListener) {
        super(method, getUrlWithParams(url, params), errorListener);
        this.listener = listener;

    }

    private static String getUrlWithParams(String url, HashMap<String, String> params) {
        StringBuilder parametrizedUrlBuilder = new StringBuilder();
        parametrizedUrlBuilder.append(BASE_URL);
        parametrizedUrlBuilder.append(url);
        if (!params.isEmpty()){
            parametrizedUrlBuilder.append("?");
            for (HashMap.Entry<String, String> entry : params.entrySet()) {
                parametrizedUrlBuilder
                        .append(entry.getKey())
                        .append("=")
                        .append(entry.getValue())
                        .append("&");
            }
            parametrizedUrlBuilder.deleteCharAt(parametrizedUrlBuilder.lastIndexOf("&"));
        }
        return parametrizedUrlBuilder.toString();
    }

    protected abstract R entry(NetworkResponse response);

    protected abstract Cache.Entry cacheEntry(NetworkResponse response);

    @Override
    protected Response<R> parseNetworkResponse(NetworkResponse response) {
        return Response.success(entry(response), cacheEntry(response));
    }

    @Override
    protected void deliverResponse(R response) {
        this.listener.onResponse(response);
    }
}

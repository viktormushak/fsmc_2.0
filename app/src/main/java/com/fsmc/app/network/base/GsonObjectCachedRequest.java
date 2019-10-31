package com.fsmc.app.network.base;

import androidx.annotation.Nullable;

import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;

public class GsonObjectCachedRequest<R> extends CachedRequest<R> {

    private final Gson gson;
    private final Class<R> clazz;

    public GsonObjectCachedRequest(int method, String url, Class<R> clazz, @Nullable ResponseResultObserver<R> observer) {
        super(method, url, response -> {
            if (observer != null){
                observer.observe(response);
            }
        });
        this.gson = new Gson();
        this.clazz = clazz;

    }

    @Override
    Response<R> parseResponse(NetworkResponse response, Cache.Entry cacheEntry) {
        try {
            return Response.success(getEntry(response), cacheEntry);
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }

    private R getEntry(NetworkResponse response) throws UnsupportedEncodingException {
        String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        return gson.fromJson(json, clazz);
    }
}

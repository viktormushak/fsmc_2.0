package com.fsmc.app.network.base;

import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

public class GsonListCachedRequest<R> extends CachedRequest<List<R>> {

    private final Gson gson;
    private final Class<R> clazz;

    public GsonListCachedRequest(int method, String url, Class<R> clazz, ResponseResultObserver<List<R>> observer) {
        super(method, url, observer::observe);
        this.gson = new Gson();
        this.clazz = clazz;

    }

    @Override
    Response<List<R>> parseResponse(NetworkResponse response, Cache.Entry cacheEntry) {
        try {
            return Response.success(getListEntry(response), cacheEntry);
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }

    private List<R> getListEntry(NetworkResponse response) throws UnsupportedEncodingException {
        String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        return Arrays.asList(gson.fromJson(json, (Type) Array.newInstance(clazz, 1).getClass()));
    }

}

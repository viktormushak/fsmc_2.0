package com.fsmc.app.network.base;

import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;

import java.io.UnsupportedEncodingException;

public abstract class CachedRequest<R> extends Request<R>  {

    private final Response.Listener<? super R> listener;

    CachedRequest(int method, String url, Response.Listener<? super R> listener) {
        super(method, url, Throwable::printStackTrace);
        this.listener = listener;
    }

    @Override
    protected Response<R> parseNetworkResponse(NetworkResponse response) {
        try {
            return parseResponse(response, cacheResponse(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new VolleyError("Can not parse response!"));
        }
    }

    abstract Response<R> parseResponse(NetworkResponse response, Cache.Entry cacheEntry) throws UnsupportedEncodingException;

    private Cache.Entry cacheResponse(NetworkResponse response) {
        Cache.Entry cacheEntry = HttpHeaderParser.parseCacheHeaders(response);
        if (cacheEntry == null) {
            cacheEntry = new Cache.Entry();
        }
        cacheEntry.data = response.data;
        long now = System.currentTimeMillis();
        cacheEntry.softTtl = now + 3600000;
        cacheEntry.ttl = now + 86400000;
        String headerValue = response.headers.get("Date");
        if (headerValue != null) {
            cacheEntry.serverDate = HttpHeaderParser.parseDateAsEpoch(headerValue);
        }
        headerValue = response.headers.get("Last-Modified");
        if (headerValue != null) {
            cacheEntry.lastModified = HttpHeaderParser.parseDateAsEpoch(headerValue);
        }
        cacheEntry.responseHeaders = response.headers;
        return cacheEntry;
    }

    @Override
    protected void deliverResponse(R response) {
        listener.onResponse(response);
    }
}

package com.fsmc.app.network.base;

import androidx.annotation.Nullable;

import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import java.util.HashMap;

public abstract class AbsFsmcCachedRequest<R> extends AbsFsmcRequest<R> {

    protected AbsFsmcCachedRequest(int method, String url, HashMap<String, String> params, Response.Listener<R> listener, @Nullable Response.ErrorListener errorListener) {
        super(method, url, params, listener, errorListener);

    }

    @Override
    protected Cache.Entry cacheEntry(NetworkResponse response) {
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
}

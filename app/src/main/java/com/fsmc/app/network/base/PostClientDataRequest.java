package com.fsmc.app.network.base;

import androidx.annotation.Nullable;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;

public class PostClientDataRequest extends Request<Boolean> {

    private Response.Listener<Boolean> listener;

    public PostClientDataRequest(String url, @Nullable Response.Listener<Boolean> listener) {
        super(Method.POST, url, Throwable::printStackTrace);
        this.listener = listener;
    }

    @Override
    protected Response<Boolean> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return Response.success(new Gson().fromJson(json, Boolean.class), null);
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(Boolean response) {
        listener.onResponse(response);
    }
}

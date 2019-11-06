package com.fsmc.app.network.base;

import androidx.annotation.Nullable;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.fsmc.app.data.model.ClientData;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;

public class GetClientDataRequest extends Request<ClientData> {

    private Response.Listener<ClientData> listener;

    public GetClientDataRequest(String url, @Nullable Response.Listener<ClientData> listener) {
        super(Method.GET, url, Throwable::printStackTrace);
        this.listener = listener;
    }

    @Override
    protected Response<ClientData> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return Response.success(new Gson().fromJson(json, ClientData.class), null);
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(ClientData response) {
        listener.onResponse(response);
    }
}

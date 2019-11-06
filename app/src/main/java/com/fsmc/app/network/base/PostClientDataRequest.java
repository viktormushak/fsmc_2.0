package com.fsmc.app.network.base;

import androidx.annotation.Nullable;

import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONObject;
import java.util.HashMap;

public class PostClientDataRequest extends JsonObjectRequest {

    public PostClientDataRequest(String url, HashMap clientData, @Nullable Response.Listener<JSONObject> listener) {
        super(Method.POST, url, new JSONObject(clientData), listener, Throwable::printStackTrace);
    }
}

package com.fsmc.app.ui.clientdata.personal;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.volley.Response;
import com.fsmc.app.FsmcApplication;
import com.fsmc.app.data.model.ClientData;

@SuppressWarnings("WeakerAccess")
public class EditPersonalDataViewModel extends ViewModel {

    private MutableLiveData<ClientData> clientDataLiveData = new MutableLiveData<>();

    LiveData<ClientData> getClientDataLiveData() {
        return clientDataLiveData;
    }

    void loadClientDataByClientId(int clientId) {
        FsmcApplication.getNetworkDataProvider()
                .loadClientDataByClientId(clientId, clientDataLiveData::setValue);
    }

    void postClientData(Context context, ClientData clientData) {
        FsmcApplication.getNetworkDataProvider()
                .postClientData(clientData,
                        response -> {
                            clientDataLiveData.setValue(response);
                            Toast.makeText(context, "Данные сохранены", Toast.LENGTH_LONG).show();
                            ((Activity) context).finish();
                        },
                        error -> Toast.makeText(context, "Не удалось сохранить", Toast.LENGTH_LONG).show());
    }
}

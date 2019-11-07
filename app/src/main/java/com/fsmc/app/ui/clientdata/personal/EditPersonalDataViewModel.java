package com.fsmc.app.ui.clientdata.personal;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.fsmc.app.FsmcApplication;
import com.fsmc.app.data.model.ClientData;

class EditPersonalDataViewModel extends ViewModel {

    private MutableLiveData<ClientData> clientDataLiveData = new MutableLiveData<>();

    LiveData<ClientData> getClientDataLiveData() {
        return clientDataLiveData;
    }

    void loadClientDataByClientId(int clientId) {
        FsmcApplication.getNetworkDataProvider()
                .loadClientDataByClientId(clientId, clientDataLiveData::setValue);
    }

    void postClientData(ClientData clientData) {
        FsmcApplication.getNetworkDataProvider()
                .postClientData(clientData, clientDataLiveData::setValue);
    }
}

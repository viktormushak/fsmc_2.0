package com.fsmc.app.ui.clientdata.address;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.fsmc.app.FsmcApplication;
import com.fsmc.app.data.model.Address;

@SuppressWarnings("WeakerAccess")
public class EditAddressViewModel extends ViewModel {

    private MutableLiveData<Address> clientAddressLiveData = new MutableLiveData<>();

    LiveData<Address> getClientAddressLiveData() {
        return clientAddressLiveData;
    }

    void loadClientAddressByClientId(int clientId) {
        FsmcApplication.getNetworkDataProvider()
                .loadClientAddressByClientId(clientId, clientAddressLiveData::setValue);
    }

    void postClientAddress(Context context, Address address, int clientId) {
        FsmcApplication.getNetworkDataProvider()
                .postClientAddress(clientId, address,
                        response -> {
                            clientAddressLiveData.setValue(response);
                            Toast.makeText(context, "Данные сохранены", Toast.LENGTH_LONG).show();
                            ((Activity) context).finish();
                        },
                        error -> Toast.makeText(context, "Не удалось сохранить", Toast.LENGTH_LONG).show());
    }}

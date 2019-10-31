package com.fsmc.app.ui.clientdetails;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.fsmc.app.data.model.Client;
import com.fsmc.app.data.model.ClientDetails;
import com.fsmc.app.network.NetworkDataProvider;
import com.fsmc.app.network.base.ResponseResultObserver;
import com.fsmc.app.ui.base.BaseViewModel;

import java.util.List;

public class ClientDetailsViewModel extends BaseViewModel<ClientDetails.Brand> implements ResponseResultObserver<ClientDetails> {

    private MutableLiveData<String> clientNameLiveData;
    private MutableLiveData<Double> clientScoreLiveData;
    private MutableLiveData<List<ClientDetails.Address>> addressesLiveData;

    public ClientDetailsViewModel(NetworkDataProvider networkDataProvider) {
        super(networkDataProvider);
        this.clientNameLiveData = new MutableLiveData<>();
        this.clientScoreLiveData = new MutableLiveData<>();
        this.addressesLiveData = new MutableLiveData<>();
    }

    void loadClientDetails(Client client) {
        networkDataProvider.loadClientDetails(client.getHashId(), this);
    }

    LiveData<String> getClientNameLiveData() {
        return clientNameLiveData;
    }

    LiveData<Double> getClientScoreLiveData() {
        return clientScoreLiveData;
    }

    LiveData<List<ClientDetails.Address>> getAddressesLiveData() {
        return addressesLiveData;
    }

    @Override
    public void observe(ClientDetails clientDetails) {
        clientNameLiveData.setValue(clientDetails.getName());
        clientScoreLiveData.setValue(clientDetails.getTotalScore());
        mutableListData.setValue(clientDetails.getBrands());
        addressesLiveData.setValue(clientDetails.getAddresses());
    }
}

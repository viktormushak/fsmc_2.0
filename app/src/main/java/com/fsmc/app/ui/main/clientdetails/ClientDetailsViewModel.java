package com.fsmc.app.ui.main.clientdetails;

import com.fsmc.app.data.model.Client;
import com.fsmc.app.data.model.ClientDetails;
import com.fsmc.app.ui.main.base.BaseViewModel;

@SuppressWarnings("WeakerAccess")
public class ClientDetailsViewModel extends BaseViewModel<ClientDetails> {

    void loadClientDetails(Client client) {
        networkDataProvider.loadClientDetailsByClientId(client.getHashId(), mutableData::setValue);
    }
}

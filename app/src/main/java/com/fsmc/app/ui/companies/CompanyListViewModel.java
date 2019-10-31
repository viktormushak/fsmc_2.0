package com.fsmc.app.ui.companies;

import com.fsmc.app.data.model.Company;
import com.fsmc.app.network.NetworkDataProvider;
import com.fsmc.app.network.base.ResponseResultObserver;
import com.fsmc.app.ui.base.BaseViewModel;

import java.util.List;

public class CompanyListViewModel extends BaseViewModel<Company> implements ResponseResultObserver<List<Company>> {

    public CompanyListViewModel(NetworkDataProvider networkDataProvider) {
        super(networkDataProvider);
    }

    void loadCompanyList(){
        this.networkDataProvider.loadCompanyList(this);
    }

    @Override
    public void observe(List<Company> response) {
        this.mutableListData.setValue(response);
    }

    void updateCache() {
        networkDataProvider.clearCache();
        loadCompanyList();
    }
}

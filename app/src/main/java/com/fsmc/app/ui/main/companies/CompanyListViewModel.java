package com.fsmc.app.ui.main.companies;

import com.fsmc.app.data.model.Company;
import com.fsmc.app.ui.main.base.BaseViewModel;

import java.util.List;

public class CompanyListViewModel extends BaseViewModel<List<Company>> {

    void loadCompanyList(){
        this.networkDataProvider.loadCompanyList(mutableData::setValue);
    }
}

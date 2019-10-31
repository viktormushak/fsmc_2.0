package com.fsmc.app.ui.base;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.fsmc.app.FsmcApplication;
import com.fsmc.app.ui.clientdetails.ClientDetailsViewModel;
import com.fsmc.app.ui.clientlist.ClientListViewModel;
import com.fsmc.app.ui.companies.CompanyListViewModel;
import com.fsmc.app.ui.globalchart.GlobalChartViewModel;

public class FragmentsViewModelFactory implements ViewModelProvider.Factory {

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(ClientListViewModel.class)) {
            return (T) new ClientListViewModel(FsmcApplication.getNetworkDataProvider());
        } else if(modelClass.isAssignableFrom(ClientDetailsViewModel.class)) {
            return (T) new ClientDetailsViewModel(FsmcApplication.getNetworkDataProvider());
        } else if(modelClass.isAssignableFrom(CompanyListViewModel.class)) {
            return (T) new CompanyListViewModel(FsmcApplication.getNetworkDataProvider());
        } else {
            return (T) new GlobalChartViewModel(FsmcApplication.getNetworkDataProvider());
        }
    }
}

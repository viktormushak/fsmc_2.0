package com.fsmc.app.ui.clientdata.address;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.fsmc.app.R;
import com.fsmc.app.data.model.Address;
import com.fsmc.app.data.model.ClientData;

public class EditAddressFragment extends Fragment {

    private EditAddressViewModel mViewModel;
    private EditText formRegion;
    private EditText formCity;
    private EditText formAddress;
    private int clientId;

    public static EditAddressFragment newInstance(int clientId) {
        EditAddressFragment fragment = new EditAddressFragment();
        fragment.clientId = clientId;
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.edit_address_fragment, container, false);
        formRegion = view.findViewById(R.id.form_region);
        formCity = view.findViewById(R.id.form_city);
        formAddress = view.findViewById(R.id.form_address);
        Button saveButton = view.findViewById(R.id.save_address_button);
        saveButton.setOnClickListener(v -> {
            Address address = new Address();
            address.setRegion(formRegion.getText().toString());
            address.setCity(formCity.getText().toString());
            address.setAddress(formAddress.getText().toString());
            if (mViewModel != null) {
                mViewModel.postClientAddress(requireActivity(), address, clientId);
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(EditAddressViewModel.class);
        mViewModel.getClientAddressLiveData().observe(this, address -> {
            if (!TextUtils.isEmpty(address.getRegion())) {
                formRegion.setText(address.getRegion());
            }
            if (!TextUtils.isEmpty(address.getCity())) {
                formCity.setText(address.getCity());
            }
            if (!TextUtils.isEmpty(address.getAddress()) && !address.getAddress().equals("none")) {
                formAddress.setText(address.getAddress());
            }

        });
        mViewModel.loadClientAddressByClientId(clientId);
    }

}

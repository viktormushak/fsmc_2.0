package com.fsmc.app.ui.clientdata.personal;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.fsmc.app.R;
import com.fsmc.app.data.model.ClientData;

public class EditPersonalDataFragment extends Fragment {

    private EditPersonalDataViewModel mViewModel;
    private EditText formSurname;
    private EditText formName;
    private EditText formPatronymic;
    private EditText formPhone;
    private EditText formEmail;
    private CheckBox hasEmail;
    private Button saveButton;
    private int clientId;

    public static EditPersonalDataFragment newInstance(int clientId) {
        EditPersonalDataFragment fragment = new EditPersonalDataFragment();
        fragment.clientId = clientId;
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.edit_personal_data_fragment, container, false);
        formSurname = view.findViewById(R.id.form_surname);
        formName = view.findViewById(R.id.form_name);
        formPatronymic = view.findViewById(R.id.form_patronymic);
        formPhone = view.findViewById(R.id.form_phone);
        formEmail = view.findViewById(R.id.form_email);
        hasEmail = view.findViewById(R.id.has_email);
        saveButton = view.findViewById(R.id.save_data_button);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(EditPersonalDataViewModel.class);
        mViewModel.getClientDataLiveData().observe(this, clientData -> {
            if (!TextUtils.isEmpty(clientData.getSurname())) {
                formSurname.setText(clientData.getSurname());
            }
            if (!TextUtils.isEmpty(clientData.getName())) {
                formName.setText(clientData.getName());
            }
            if (!TextUtils.isEmpty(clientData.getPatronymic())) {
                formPatronymic.setText(clientData.getPatronymic());
            }
            if (!TextUtils.isEmpty(clientData.getPhone())) {
                formPhone.setText(clientData.getPhone());
            }
            if (!TextUtils.isEmpty(clientData.getEmail())) {
                if ("none".equals(clientData.getEmail())){
                    formEmail.setText("");
                    hasEmail.setChecked(true);
                }else {
                    formEmail.setText(clientData.getEmail());
                    hasEmail.setChecked(false);
                }
            }
        });
        mViewModel.loadClientDataByClientId(clientId);
        saveButton.setOnClickListener(view -> {
            ClientData clientData = new ClientData();
            clientData.setHashId(clientId);
            clientData.setName(formName.getText().toString());
            clientData.setSurname(formSurname.getText().toString());
            clientData.setPatronymic(formPatronymic.getText().toString());
            clientData.setPhone(formPhone.getText().toString());
            clientData.setEmail(hasEmail.isChecked() ? "none" : formEmail.getText().toString());
            mViewModel.postClientData(clientData);
        });
    }

}

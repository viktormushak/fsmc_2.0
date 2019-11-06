package com.fsmc.app.ui.editperson;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;

import com.fsmc.app.FsmcApplication;
import com.fsmc.app.R;
import com.fsmc.app.data.model.ClientData;

import org.json.JSONException;

public class EditPersonActivity extends AppCompatActivity {

    MutableLiveData<ClientData> clientDataLiveData = new MutableLiveData<>();
    private EditText formSurname;
    private EditText formName;
    private EditText formPatronymic;
    private EditText formPhone;
    private EditText formEmail;
    private CheckBox hasEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_person);

        formSurname = findViewById(R.id.form_surname);
        formName = findViewById(R.id.form_name);
        formPatronymic = findViewById(R.id.form_patronymic);
        formPhone = findViewById(R.id.form_phone);
        formEmail = findViewById(R.id.form_email);
        hasEmail = findViewById(R.id.has_email);
        Button saveButton = findViewById(R.id.save_data_button);

        clientDataLiveData.observe(this, clientData -> {
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

        int id = getIntent().getIntExtra("clientId", 0);

        FsmcApplication.getNetworkDataProvider()
                .loadClientData(id, response -> clientDataLiveData.setValue(response));

        saveButton.setOnClickListener(view -> {
            ClientData clientData = new ClientData();
            clientData.setHashId(id);
            clientData.setName(formName.getText().toString());
            clientData.setSurname(formSurname.getText().toString());
            clientData.setPatronymic(formPatronymic.getText().toString());
            clientData.setPhone(formPhone.getText().toString());
            clientData.setEmail(hasEmail.isChecked() ? "none" : formEmail.getText().toString());
            FsmcApplication.getNetworkDataProvider()
                    .postClientData(clientData, response -> {
                        try {
                            if (response.getBoolean("")){
                                Toast.makeText(getApplicationContext(), "Данные сохранены", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Ошибка сохранения данных", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    });
        });
    }
}

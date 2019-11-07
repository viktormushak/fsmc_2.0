package com.fsmc.app.ui.clientdata;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.fsmc.app.FsmcApplication;
import com.fsmc.app.R;
import com.fsmc.app.data.model.ClientData;
import com.fsmc.app.ui.base.BaseActivity;
import com.fsmc.app.ui.clientdata.personal.EditPersonalDataFragment;

import org.json.JSONException;

public class EditPersonActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_activity_layout);

        int clientId = getIntent().getIntExtra("clientId", 0);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, EditPersonalDataFragment.newInstance(clientId))
                .commit();
    }
}

package com.fsmc.app.ui.clientdata;

import android.os.Bundle;

import com.fsmc.app.R;
import com.fsmc.app.ui.base.BaseActivity;
import com.fsmc.app.ui.clientdata.personal.EditPersonalDataFragment;

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

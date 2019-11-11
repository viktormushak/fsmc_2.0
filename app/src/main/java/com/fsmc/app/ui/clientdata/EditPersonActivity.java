package com.fsmc.app.ui.clientdata;

import android.os.Bundle;

import androidx.viewpager.widget.ViewPager;

import com.fsmc.app.R;
import com.fsmc.app.ui.base.BaseActivity;
import com.fsmc.app.ui.clientdata.personal.EditPersonalDataFragment;
import com.google.android.material.tabs.TabLayout;

public class EditPersonActivity extends BaseActivity {

    private int clientId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed);

        clientId = getIntent().getIntExtra("clientId", 0);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }

    public int getClientId() {
        return clientId;
    }
}

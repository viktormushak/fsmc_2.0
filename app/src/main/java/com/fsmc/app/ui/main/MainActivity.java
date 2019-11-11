package com.fsmc.app.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.fsmc.app.R;
import com.fsmc.app.ui.base.BaseActivity;
import com.fsmc.app.ui.main.companies.CompanyListFragment;

public class MainActivity extends BaseActivity implements MainActivityNavigator {

    private Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_activity_layout);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (currentFragment == null){
            navigateToFragment(CompanyListFragment.newInstance(this));
        } else {
            showFragment();
        }
    }
    @Override
    public void navigateToFragment(Fragment fragment) {
        this.currentFragment = fragment;
        showFragment();
    }

    private void showFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, currentFragment)
                .commitNow();
    }
}

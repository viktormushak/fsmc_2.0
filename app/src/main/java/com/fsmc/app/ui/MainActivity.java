package com.fsmc.app.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.fsmc.app.R;
import com.fsmc.app.ui.companies.CompanyListFragment;

public class MainActivity extends AppCompatActivity implements MainActivityNavigator {

    private Fragment currentFragment;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

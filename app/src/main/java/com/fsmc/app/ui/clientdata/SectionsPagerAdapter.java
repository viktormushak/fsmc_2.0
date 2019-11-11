package com.fsmc.app.ui.clientdata;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.fsmc.app.R;
import com.fsmc.app.ui.clientdata.address.EditAddressFragment;
import com.fsmc.app.ui.clientdata.personal.EditPersonalDataFragment;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.personal, R.string.address};
    private static Fragment[] fragments;
    private final Context mContext;

    SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mContext = context;
        fragments = new Fragment[]{
                EditPersonalDataFragment.newInstance(((EditPersonActivity) context).getClientId()),
                EditAddressFragment.newInstance(((EditPersonActivity) context).getClientId())
        };
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return 2;
    }
}
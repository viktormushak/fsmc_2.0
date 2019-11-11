package com.fsmc.app.ui.main.companies;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.fsmc.app.R;
import com.fsmc.app.data.model.Company;
import com.fsmc.app.ui.main.MainActivityNavigator;
import com.fsmc.app.ui.main.base.RecyclerViewFragment;
import com.fsmc.app.ui.main.clients.ClientListFragment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CompanyListFragment extends RecyclerViewFragment {

    private MainActivityNavigator navigator;

    public static CompanyListFragment newInstance(MainActivityNavigator navigator) {
        CompanyListFragment fragment = new CompanyListFragment();
        fragment.navigator = navigator;
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        requireActivity().getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                requireActivity().finish();
            }
        });
        CompanyListViewModel viewModel = ViewModelProviders.of(this).get(CompanyListViewModel.class);
        viewModel.getMutableData().observe(this, companies -> {
            recyclerView.setAdapter(new RecyclerView.Adapter() {
                @NonNull
                @Override
                public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_company_item, parent, false);
                    return new RecyclerView.ViewHolder(view){};
                }

                @Override
                public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                    Company company = companies.get(position);
                    ((TextView) holder.itemView.findViewById(R.id.company_item_name)).setText(company.getName());
                    ((TextView) holder.itemView.findViewById(R.id.company_item_date))
                            .setText(new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(new Date(company.getLastUpdate())));
                    holder.itemView.setOnClickListener(view -> navigator.navigateToFragment(ClientListFragment.newInstance(company.getName(), navigator)));
                }

                @Override
                public int getItemCount() {
                    return companies.size();
                }
            });
            inProgress(false);
        });
        viewModel.loadCompanyList();
        inProgress(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.global_chart_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.global_chart){
            navigator.navigateToFragment(ClientListFragment.newInstance(null, navigator));
        }
        return true;
    }
}

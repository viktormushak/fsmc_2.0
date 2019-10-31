package com.fsmc.app.ui.globalchart;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fsmc.app.R;
import com.fsmc.app.data.model.Client;
import com.fsmc.app.ui.MainActivityNavigator;
import com.fsmc.app.ui.base.FragmentsViewModelFactory;
import com.fsmc.app.ui.base.RecyclerViewFragment;
import com.fsmc.app.ui.clientdetails.ClientDetailsFragment;
import com.fsmc.app.ui.companies.CompanyListFragment;

public class GlobalChartFragment extends RecyclerViewFragment {

    private MainActivityNavigator navigator;

    public static GlobalChartFragment newInstance(MainActivityNavigator navigator) {
        GlobalChartFragment fragment = new GlobalChartFragment();
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
        GlobalChartViewModel viewModel = ViewModelProviders.of(this, new FragmentsViewModelFactory()).get(GlobalChartViewModel.class);
        viewModel.getMutableListData().observe(this, clients -> recyclerView.setAdapter(new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_client_item, parent, false);
                return new RecyclerView.ViewHolder(view){};
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                Client client = clients.get(position);
                ((TextView) holder.itemView.findViewById(R.id.client_item_rate)).setText(String.valueOf(client.getRate()));
                ((TextView) holder.itemView.findViewById(R.id.client_item_name)).setText(client.getName());
                ((TextView) holder.itemView.findViewById(R.id.client_item_score)).setText(String.valueOf(client.getTotalScore()));
                ((TextView) holder.itemView.findViewById(R.id.client_item_address)).setText(client.getAddress());
                holder.itemView.setOnClickListener(view -> navigator.navigateToFragment(ClientDetailsFragment.newInstance(client, navigator)));
            }

            @Override
            public int getItemCount() {
                return clients.size();
            }
        }));

        viewModel.loadClientList();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.global_chart_on_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.global_chart_off){
            navigator.navigateToFragment(CompanyListFragment.newInstance(navigator));
        }
        return true;
    }

}

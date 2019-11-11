package com.fsmc.app.ui.main.clients;

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
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.fsmc.app.R;
import com.fsmc.app.data.model.Client;
import com.fsmc.app.ui.main.MainActivityNavigator;
import com.fsmc.app.ui.main.base.RecyclerViewFragment;
import com.fsmc.app.ui.main.clientdetails.ClientDetailsFragment;
import com.fsmc.app.ui.main.companies.CompanyListFragment;

public class ClientListFragment extends RecyclerViewFragment {

    private MainActivityNavigator navigator;
    private ClientListViewModel viewModel;
    private String company;

    public static ClientListFragment newInstance(String company, MainActivityNavigator navigator) {
        ClientListFragment fragment = new ClientListFragment();
        fragment.company = company;
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
                navigator.navigateToFragment(CompanyListFragment.newInstance(navigator));
            }
        });
        viewModel = ViewModelProviders.of(this).get(ClientListViewModel.class);
        viewModel.getMutableData().observe(this, clients -> {
            recyclerView.setAdapter(new RecyclerView.Adapter() {
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
                    ((TextView) holder.itemView.findViewById(R.id.client_item_score)).setText(getString( R.string.item_score, client.getTotalScore()));
                    ((TextView) holder.itemView.findViewById(R.id.client_item_address)).setText(client.getAddress());
                    holder.itemView.setOnClickListener(view -> navigator.navigateToFragment(ClientDetailsFragment.newInstance(client, navigator)));
                }

                @Override
                public int getItemCount() {
                    return clients.size();
                }
            });
            inProgress(false);
        });
        viewModel.loadClientList(company);
        inProgress(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.clients_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return viewModel.searchClients(query);
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return viewModel.searchClients(newText);
            }
        });
    }
}

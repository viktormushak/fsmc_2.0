package com.fsmc.app.ui.clientdetails;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fsmc.app.R;
import com.fsmc.app.data.model.Client;
import com.fsmc.app.data.model.ClientDetails;
import com.fsmc.app.ui.base.FragmentsViewModelFactory;
import com.fsmc.app.ui.MainActivityNavigator;
import com.fsmc.app.ui.clientlist.ClientListFragment;

public class ClientDetailsFragment extends Fragment {

    private MainActivityNavigator navigator;
    private Client client;
    private TextView textViewName;
    private TextView textViewScore;
    private RecyclerView recyclerViewAddresses;
    private RecyclerView recyclerViewBrands;

    public static ClientDetailsFragment newInstance(Client client, MainActivityNavigator navigator) {
        ClientDetailsFragment fragment = new ClientDetailsFragment();
        fragment.navigator = navigator;
        fragment.client = client;
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_client_details, container, false);
        textViewName = root.findViewById(R.id.client_name);
        textViewScore = root.findViewById(R.id.client_brands_quantity);
        recyclerViewAddresses = root.findViewById(R.id.client_addresses);
        recyclerViewAddresses.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewBrands = root.findViewById(R.id.client_brands);
        recyclerViewBrands.setLayoutManager(new LinearLayoutManager(getContext()));
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ClientDetailsViewModel viewModel = ViewModelProviders.of(this, new FragmentsViewModelFactory()).get(ClientDetailsViewModel.class);
        viewModel.getClientNameLiveData().observe(this, clientName -> textViewName.setText(clientName));
        viewModel.getClientScoreLiveData().observe(this, clientScore -> textViewScore.setText(getString( R.string.item_score, clientScore)));
        viewModel.getAddressesLiveData().observe(this, addresses -> recyclerViewAddresses.setAdapter(new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_address_item, parent, false);
                return new RecyclerView.ViewHolder(view){};
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                ClientDetails.Address address = addresses.get(position);
                TextView textView = holder.itemView.findViewById(R.id.address_item);
                textView.setText(address.isFullAddressCorrect() ?
                        getString(R.string.address, address.getRegion(), address.getCity(), address.getStreet()) : address.getAddress());
            }

            @Override
            public int getItemCount() {
                return addresses.size();
            }
        }));
        viewModel.getMutableListData().observe(this, brands -> recyclerViewBrands.setAdapter(new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_brand_item, parent, false);
                return new RecyclerView.ViewHolder(view){};
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                ClientDetails.Brand brand = brands.get(position);
                TextView brandName = holder.itemView.findViewById(R.id.brand_item_name);
                TextView brandQuantity = holder.itemView.findViewById(R.id.brand_item_quantity);
                brandName.setText(brand.getName());
                brandQuantity.setText(getString(R.string.item_score, brand.getQuality()));
            }

            @Override
            public int getItemCount() {
                return brands.size();
            }
        }));
        viewModel.loadClientDetails(client);
        requireActivity().getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                navigator.navigateToFragment(ClientListFragment.newInstance(client.getCompany(), navigator));
            }
        });
    }

}

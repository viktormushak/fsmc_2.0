package com.fsmc.app.ui.main.clientdetails;

import android.content.Intent;
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
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fsmc.app.R;
import com.fsmc.app.data.model.Brand;
import com.fsmc.app.data.model.Client;
import com.fsmc.app.ui.main.MainActivityNavigator;
import com.fsmc.app.ui.main.clients.ClientListFragment;
import com.fsmc.app.ui.clientdata.EditPersonActivity;

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
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
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.edit_person_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_edit){
            Intent intent = new Intent(requireActivity(), EditPersonActivity.class);
            intent.putExtra("clientId", client.getHashId());
            startActivity(intent);
        }
        return true;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ClientDetailsViewModel viewModel = ViewModelProviders.of(this).get(ClientDetailsViewModel.class);

        viewModel.getMutableData().observe(this, clientDetails -> {
            textViewName.setText(clientDetails.getName());
            textViewScore.setText(getString( R.string.item_score, clientDetails.getTotalScore()));
            recyclerViewAddresses.setAdapter(new RecyclerView.Adapter() {
                @NonNull
                @Override
                public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                    return new RecyclerView.ViewHolder(
                            LayoutInflater.from(parent.getContext())
                                    .inflate(R.layout.recycler_view_address_item, parent, false)){};
                }

                @Override
                public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                    ((TextView) holder.itemView.findViewById(R.id.address_item))
                            .setText(getString(R.string.full_address,
                                    clientDetails.getAddresses().get(position).getRegion(),
                                    clientDetails.getAddresses().get(position).getCity(),
                                    clientDetails.getAddresses().get(position).getAddress()));
                }

                @Override
                public int getItemCount() {
                    return clientDetails.getAddresses().size();
                }
            });
            recyclerViewBrands.setAdapter(new RecyclerView.Adapter() {
                @NonNull
                @Override
                public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_brand_item, parent, false);
                    return new RecyclerView.ViewHolder(view){};
                }

                @Override
                public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                    Brand brand = clientDetails.getBrands().get(position);
                    TextView brandName = holder.itemView.findViewById(R.id.brand_item_name);
                    TextView brandQuantity = holder.itemView.findViewById(R.id.brand_item_quantity);
                    brandName.setText(brand.getName());
                    brandQuantity.setText(getString(R.string.item_score, brand.getQuality()));
                }

                @Override
                public int getItemCount() {
                    return clientDetails.getBrands().size();
                }
            });
        });

        viewModel.loadClientDetails(client);

        requireActivity().getOnBackPressedDispatcher().addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                navigator.navigateToFragment(ClientListFragment.newInstance(client.getCompany(), navigator));
            }
        });
    }

}

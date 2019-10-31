package com.fsmc.app.ui.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fsmc.app.R;

public class RecyclerViewFragment extends Fragment {

    protected RecyclerView recyclerView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_recycler_view, container, false);
        recyclerView =root.findViewById(R.id.fragment_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return root;
    }
}

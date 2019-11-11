package com.fsmc.app.ui.main.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fsmc.app.R;

public class RecyclerViewFragment extends Fragment {

    protected RecyclerView recyclerView;
    private ProgressBar progressbar;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_recycler_view, container, false);
        recyclerView = root.findViewById(R.id.fragment_recycler_view);
        progressbar = root.findViewById(R.id.progressBar);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return root;
    }

    protected void inProgress(boolean b){
        progressbar.setVisibility(b ? View.VISIBLE: View.GONE);
    }
}

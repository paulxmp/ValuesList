package com.nomadsoftwareconsultants.valueslist.ui.organizations;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nomadsoftwareconsultants.valueslist.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class OrganizationsFragment extends Fragment {

    private OrganizationsViewModel organizationsViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        organizationsViewModel = new ViewModelProvider(getActivity()).get(OrganizationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_organizations, container, false);
        final TextView textView = root.findViewById(R.id.text_organizations);
        organizationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

}
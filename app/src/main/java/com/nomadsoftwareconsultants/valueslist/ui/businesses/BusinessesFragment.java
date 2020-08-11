package com.nomadsoftwareconsultants.valueslist.ui.businesses;

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

public class BusinessesFragment extends Fragment {

    private BusinessesViewModel businessesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        businessesViewModel = new ViewModelProvider(getActivity()).get(BusinessesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_businesses, container, false);
        final TextView textView = root.findViewById(R.id.text_businesses);
        businessesViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}

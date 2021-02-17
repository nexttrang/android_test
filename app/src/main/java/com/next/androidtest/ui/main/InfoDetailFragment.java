package com.next.androidtest.ui.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.next.androidtest.R;
import com.next.androidtest.databinding.FragmentBodyContentBinding;
import com.next.androidtest.databinding.FragmentInfoDetailBinding;

public class InfoDetailFragment extends Fragment {

    private FragmentInfoDetailBinding binding;
    private VisitorViewModel visitorViewModel;

    public InfoDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        visitorViewModel = InfoDetailFragmentArgs.fromBundle(getArguments()).getVisitorViewModel();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentInfoDetailBinding.inflate(inflater, container, false);

        if(visitorViewModel != null){
            String text = "Timestamp: " + visitorViewModel.getVisitor().getValue().getTimeStamp() +
                            "\nVisitor name: " + visitorViewModel.getVisitor().getValue().getVisitorName() +
                            "\nType: " + visitorViewModel.getVisitor().getValue().getType()+
                            "\nStatus: " + visitorViewModel.getVisitor().getValue().getStatus();
            binding.tvInfo.setText(text);
        }

        return binding.getRoot();
    }
}
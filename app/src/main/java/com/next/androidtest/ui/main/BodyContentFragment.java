package com.next.androidtest.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.next.androidtest.data.model.Visitor;
import com.next.androidtest.data.model.VisitorAdapter;
import com.next.androidtest.data.sqlite.MyDatabaseHelper;
import com.next.androidtest.databinding.FragmentBodyContentBinding;

import java.util.ArrayList;
import java.util.List;

public class BodyContentFragment extends Fragment {

    private FragmentBodyContentBinding binding;
    private List<VisitorViewModel> visitorViewModels = new ArrayList<>();

    private int totalVisitors = 0;
    private int regVisitors = 0;
    private int inVisitors = 0;
    private int outVisitors = 0;

    public BodyContentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MyDatabaseHelper db = new MyDatabaseHelper(this.getContext());
        db.createDefaultNotesIfNeed();
        List<Visitor> visitors = db.getAllVisitors();

        totalVisitors = visitors.size();
        regVisitors = db.getVisitorsCount("REG");
        inVisitors = db.getVisitorsCount("IN");
        outVisitors = db.getVisitorsCount("OUT");

        for (Visitor visitor : visitors) {
            visitorViewModels.add(new VisitorViewModel(getActivity().getApplication(),visitor));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBodyContentBinding.inflate(inflater, container, false);
        binding(binding);

        return binding.getRoot();
    }

    private void binding(FragmentBodyContentBinding binding) {
        VisitorAdapter adapter = new VisitorAdapter(this.getContext(),visitorViewModels);
        binding.visitorRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.visitorRecyclerview.setAdapter(adapter);

        binding.tvTotal.setText(String.valueOf(totalVisitors));
        binding.tvREG.setText(String.valueOf(regVisitors));
        binding.tvIn.setText(String.valueOf(inVisitors));
        binding.tvOut.setText(String.valueOf(outVisitors));
    }
}
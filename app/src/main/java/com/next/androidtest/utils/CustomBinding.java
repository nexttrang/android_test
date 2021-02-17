package com.next.androidtest.utils;

import android.view.View;

import androidx.databinding.BindingAdapter;

import com.next.androidtest.ui.main.VisitorViewModel;

public class CustomBinding {

    @BindingAdapter({"android:onSingleClick"})
    public static void onSingleClick(View view, VisitorViewModel viewModel) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.onItemClick(view);
            }
        });
    }

}

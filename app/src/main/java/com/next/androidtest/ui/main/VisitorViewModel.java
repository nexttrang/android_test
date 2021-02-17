package com.next.androidtest.ui.main;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.Navigation;

import com.next.androidtest.R;
import com.next.androidtest.data.model.Visitor;

import java.io.Serializable;

public class VisitorViewModel extends AndroidViewModel implements Serializable {
    private MutableLiveData<Visitor> visitor = new MutableLiveData<>();

    public VisitorViewModel(@NonNull Application application, Visitor visitor) {
        super(application);
        this.visitor.setValue(visitor);
    }

    public MutableLiveData<Visitor> getVisitor() {
        return visitor;
    }

    public void onItemClick(View view) {
        Navigation.findNavController(view)
                .navigate(BodyContentFragmentDirections
                        .actionBodyContentFragmentToInfoDetailFragment(this));
    }
}

package com.next.androidtest.data.model;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.next.androidtest.databinding.ItemRecordBinding;
import com.next.androidtest.ui.main.VisitorViewModel;

import java.util.List;

public class VisitorAdapter extends RecyclerView.Adapter<VisitorAdapter.MyViewHolder>{

    private Context mContext;
    private List<VisitorViewModel> mData;
    private LayoutInflater inflater;

    public VisitorAdapter(Context mContext, List<VisitorViewModel> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }
        ItemRecordBinding binding = ItemRecordBinding.inflate(inflater, parent, false);

        return new MyViewHolder(mContext, binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        if (mData == null) {
            return 0;
        }

        return mData.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private ItemRecordBinding binding;
        private Context context;

        public MyViewHolder(Context context,ItemRecordBinding binding) {
            super(binding.getRoot());
            this.context = context;
            this.binding = binding;
        }

        public void bind(VisitorViewModel viewModel) {
            binding.setViewModel(viewModel);
        }
    }
}

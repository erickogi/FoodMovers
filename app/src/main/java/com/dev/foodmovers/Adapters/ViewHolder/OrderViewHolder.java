package com.dev.foodmovers.Adapters.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.dev.foodmovers.R;
import com.dev.lishabora.Utils.OnclickRecyclerListener;

import java.lang.ref.WeakReference;

public class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
    public TextView txtDate, txtPrice, txtCount, txtStatus;
    private WeakReference<OnclickRecyclerListener> listenerWeakReference;


    public OrderViewHolder(@NonNull View itemView, OnclickRecyclerListener listener) {
        super(itemView);
        listenerWeakReference = new WeakReference<>(listener);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);

        txtDate = itemView.findViewById(R.id.txt_date);
        txtPrice = itemView.findViewById(R.id.txt_price);
        txtCount = itemView.findViewById(R.id.txt_items_count);
        txtStatus = itemView.findViewById(R.id.txt_status);


    }

    @Override
    public void onClick(View view) {

        listenerWeakReference.get().onClickListener(getAdapterPosition(), view);


    }

    @Override
    public boolean onLongClick(View view) {
        listenerWeakReference.get().onClickListener(getAdapterPosition(), view);

        return false;
    }
}

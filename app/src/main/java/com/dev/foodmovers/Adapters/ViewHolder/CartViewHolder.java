package com.dev.foodmovers.Adapters.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.foodmovers.R;
import com.dev.lishabora.Utils.OnclickRecyclerListener;

import java.lang.ref.WeakReference;

public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
    public TextView txtName, txtQty, txtPrice;
    public ImageView imgFood, imgAdd, imgRemove, imgDelete;
    private WeakReference<OnclickRecyclerListener> listenerWeakReference;


    public CartViewHolder(@NonNull View itemView, OnclickRecyclerListener listener) {
        super(itemView);
        listenerWeakReference = new WeakReference<>(listener);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);

        imgFood = itemView.findViewById(R.id.img_food);
        imgFood.setOnClickListener(this);

        txtName = itemView.findViewById(R.id.txt_name);
        txtQty = itemView.findViewById(R.id.txt_qty);

        imgAdd = itemView.findViewById(R.id.img_add);
        imgRemove = itemView.findViewById(R.id.img_remove);
        imgDelete = itemView.findViewById(R.id.img_delete);


        imgAdd.setOnClickListener(this);
        imgRemove.setOnClickListener(this);
        imgDelete.setOnClickListener(this);


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


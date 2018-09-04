package com.dev.foodmovers.Adapters.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.foodmovers.R;
import com.dev.lishabora.Utils.OnclickRecyclerListener;

import java.lang.ref.WeakReference;

public class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
    public TextView txtName, txtType, txtDesc, txtPriceOne, txtPriceTwo, txtAddToCart;
    public ImageView imgFood, imgBck;
    public View cancelPriceOneView;
    private WeakReference<OnclickRecyclerListener> listenerWeakReference;


    public FoodViewHolder(@NonNull View itemView, OnclickRecyclerListener listener) {
        super(itemView);
        listenerWeakReference = new WeakReference<>(listener);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);

        cancelPriceOneView = itemView.findViewById(R.id.view_cancel);
        imgFood = itemView.findViewById(R.id.img_food);
        imgBck = itemView.findViewById(R.id.img_bck);
        imgBck.setOnClickListener(this);
        imgFood.setOnClickListener(this);
        txtName = itemView.findViewById(R.id.txt_name);
        txtType = itemView.findViewById(R.id.txt_type);

        txtDesc = itemView.findViewById(R.id.txt_description);
        txtPriceOne = itemView.findViewById(R.id.txt_price_one);
        txtPriceTwo = itemView.findViewById(R.id.txt_price_two);
        txtAddToCart = itemView.findViewById(R.id.txt_add_to_cart);


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.img_food) {
            listenerWeakReference.get().onClickListener(getAdapterPosition(), view);
        } else {
            listenerWeakReference.get().onClickListener(getAdapterPosition());
        }

    }

    @Override
    public boolean onLongClick(View view) {
        if (view.getId() == R.id.img_food) {
            listenerWeakReference.get().onClickListener(getAdapterPosition(), view);
        } else {
            listenerWeakReference.get().onLongClickListener(getAdapterPosition());
        }
        return false;
    }
}

package com.dev.foodmovers.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.dev.foodmovers.Adapters.ViewHolder.CartViewHolder;
import com.dev.foodmovers.Data.Models.FoodCartModel;
import com.dev.foodmovers.Data.Models.FoodModel;
import com.dev.foodmovers.R;
import com.dev.lishabora.Utils.OnclickRecyclerListener;
import com.google.gson.Gson;

import java.util.List;

public class CartListAdapter extends RecyclerView.Adapter<CartViewHolder> {

    private RequestOptions options;

    private Context context;
    private List<FoodCartModel> modelList;
    private OnclickRecyclerListener listener;

    public CartListAdapter(Context context, List<FoodCartModel> modelList, OnclickRecyclerListener listener) {
        this.context = context;
        this.modelList = modelList;
        this.listener = listener;
        this.options = (new RequestOptions())
                .placeholder(R.drawable.imagepicker_image_placeholder)
                .error(R.drawable.imagepicker_image_placeholder)
                .centerCrop().diskCacheStrategy(DiskCacheStrategy.RESOURCE);

    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;

        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_card, parent, false);

        return new CartViewHolder(itemView, listener);
    }


    @Override
    public void onBindViewHolder(CartViewHolder holder, int position) {
        FoodCartModel model = modelList.get(position);
        holder.txtName.setText(model.getFoodname());
        holder.txtQty.setText("" + model.getQuantity());

        Double price = Double.valueOf(model.getPrice());
        Double discount = 0.0;
        int qty = model.getQuantity();
        if (model.getDiscount() != null && Double.valueOf(model.getDiscount()) >= 0.0) {
            discount = Double.valueOf(model.getDiscount());
        }

        Double totalPrice = (price - discount) * qty;


        holder.txtPrice.setText(String.valueOf(totalPrice));

        Gson gson = new Gson();

        FoodModel m = gson.fromJson(model.getFoodmodel(), FoodModel.class);
        Glide.with(context)
                .load(m.getImage())
                .apply(options)
                .into(holder.imgFood);


    }


    @Override
    public int getItemCount() {
        return (null != modelList ? modelList.size() : 0);
    }

    public void refresh(List<FoodCartModel> foodModels) {
        modelList = foodModels;
        notifyDataSetChanged();
    }
}

package com.dev.foodmovers.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dev.foodmovers.Adapters.ViewHolder.OrderViewHolder;
import com.dev.foodmovers.Data.Models.FoodOrderModel;
import com.dev.foodmovers.R;
import com.dev.lishabora.Utils.OnclickRecyclerListener;

import java.util.List;

public class OrderListAdapter extends RecyclerView.Adapter<OrderViewHolder> {

    private Context context;
    private List<FoodOrderModel> modelList;
    private OnclickRecyclerListener listener;

    public OrderListAdapter(Context context, List<FoodOrderModel> modelList, OnclickRecyclerListener listener) {
        this.context = context;
        this.modelList = modelList;
        this.listener = listener;
    }

    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;

        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_card, parent, false);

        return new OrderViewHolder(itemView, listener);
    }


    @Override
    public void onBindViewHolder(OrderViewHolder holder, int position) {

        FoodOrderModel orderModel = modelList.get(position);


        holder.txtDate.setText(orderModel.getTimestamp());
        holder.txtStatus.setText(orderModel.getStatusname());
        holder.txtCount.setText(orderModel.getCount());
        holder.txtPrice.setText(orderModel.getPrice());


    }


    @Override
    public int getItemCount() {
        return (null != modelList ? modelList.size() : 0);
    }

}

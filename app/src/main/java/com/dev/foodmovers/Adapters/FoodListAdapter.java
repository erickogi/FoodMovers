package com.dev.foodmovers.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.dev.foodmovers.Adapters.ViewHolder.FoodViewHolder;
import com.dev.foodmovers.Data.Models.FoodModel;
import com.dev.foodmovers.R;
import com.dev.lishabora.Utils.OnclickRecyclerListener;

import java.util.List;

public class FoodListAdapter extends RecyclerView.Adapter<FoodViewHolder> {

    private Context context;
    private List<FoodModel> modelList;
    private OnclickRecyclerListener listener;
    private RequestOptions options;


    public FoodListAdapter(Context context, List<FoodModel> modelList, OnclickRecyclerListener listener) {
        this.context = context;
        this.modelList = modelList;
        this.listener = listener;
        this.options = (new RequestOptions())
                .placeholder(R.drawable.imagepicker_image_placeholder)
                .error(R.drawable.imagepicker_image_placeholder)
                .centerCrop().diskCacheStrategy(DiskCacheStrategy.RESOURCE);

    }

    @Override
    public FoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;

        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_card, parent, false);

        return new FoodViewHolder(itemView, listener);
    }


    @Override
    public void onBindViewHolder(FoodViewHolder holder, int position) {

        FoodModel model = modelList.get(position);
        holder.txtName.setText(model.getName());
        holder.txtDesc.setText(model.getDescription());
        holder.txtType.setText(model.getCategoryname());
        holder.txtPriceOne.setText(model.getPrice());
        if (model.isHasDiscount()) {
            holder.cancelPriceOneView.setVisibility(View.VISIBLE);
            holder.txtPriceTwo.setText(model.getPriceTwo());
            holder.txtPriceTwo.setVisibility(View.VISIBLE);
        } else {
            holder.cancelPriceOneView.setVisibility(View.GONE);
            holder.txtPriceTwo.setVisibility(View.GONE);
        }

        Glide.with(context)
                .load(model.getImage())
                .apply(options)
                .into(holder.imgFood);


    }


    @Override
    public int getItemCount() {
        return (null != modelList ? modelList.size() : 0);
    }

    public void refresh(List<FoodModel> foodModels) {
        modelList = foodModels;
        notifyDataSetChanged();
    }
}

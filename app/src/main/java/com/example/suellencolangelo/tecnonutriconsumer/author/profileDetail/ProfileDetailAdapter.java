package com.example.suellencolangelo.tecnonutriconsumer.author.profileDetail;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.suellencolangelo.tecnonutriconsumer.R;
import com.example.suellencolangelo.tecnonutriconsumer.model.Item;

/**
 * Created by suellencolangelo on 28/02/17.
 */

public class ProfileDetailAdapter extends RecyclerView.Adapter<ProfileDetailAdapter.GridViewHolder> {

    private ProfileDetailContract.Presenter mPresenter;

    public ProfileDetailAdapter(ProfileDetailContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public ProfileDetailAdapter.GridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        // Infla o layout da célula da grid
        View gridItem = inflater.inflate(R.layout.item_grid, parent, false);
        return new GridViewHolder(gridItem);

    }

    @Override
    public void onBindViewHolder(ProfileDetailAdapter.GridViewHolder holder, int position) {
        Item item = mPresenter.getItem(position);
        if (item == null) {
            return;
        }
        Glide.with(holder.mImage.getContext())
                .load(item.getImage())
                .crossFade()
                .centerCrop()
                .into(holder.mImage);
    }

    @Override
    public int getItemCount() {
        return mPresenter.itemCount();
    }

    public class GridViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView mImage;

        GridViewHolder(View item) {
            super(item);
            mImage = (ImageView) item.findViewById(R.id.feed_image);
            item.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mPresenter.onFeedClick(getLayoutPosition());
        }
    }


}

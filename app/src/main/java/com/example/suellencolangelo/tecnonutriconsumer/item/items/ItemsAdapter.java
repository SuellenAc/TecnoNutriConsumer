package com.example.suellencolangelo.tecnonutriconsumer.item.items;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.suellencolangelo.tecnonutriconsumer.R;
import com.example.suellencolangelo.tecnonutriconsumer.model.Item;
import com.example.suellencolangelo.tecnonutriconsumer.utils.glide.GlideCircleTransform;

/**
 * Created by suellencolangelo on 26/02/17.
 */

public class ItemsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ItemContract.Presenter mPresenter;
    private Boolean mShowAsGrid;

    public ItemsAdapter(Boolean showAsGrid, ItemContract.Presenter presenter) {
        this.mPresenter = presenter;
        this.mShowAsGrid = showAsGrid;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        if (mShowAsGrid) {
            // Infla o layout da célula da grid
            View gridItem = inflater.inflate(R.layout.item_grid, parent, false);
            return new GridViewHolder(gridItem);
        }
         else {
            // Infla o layout da célula da lista
            View listItem = inflater.inflate(R.layout.item_list, parent, false);
            return new ListViewHolder(listItem);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Item item = mPresenter.getItem(position);
        if (item == null) {
            return;
        }
        if (holder instanceof ListViewHolder){
            ListViewHolder listHolder = (ListViewHolder) holder;
            // Nome do Author
            listHolder.mAuthorName.setText(item.getProfile().getName());
            // Meta do Author
            listHolder.mAuthorGoal.setText(item.getProfile().getGeneralGoal());
            // image de perfil do Author
            Glide.with(listHolder.mAuthorImage.getContext())
                    .load(item.getProfile().getImage())
                    .transform(new GlideCircleTransform(listHolder.mAuthorImage.getContext()))
                    .into(listHolder.mAuthorImage);

            // Dados do item
            //Data
            listHolder.mDate.setText(item.getDate());
            // image do prato
            Glide.with(listHolder.mImage.getContext())
                    .load(item.getImage())
                    .crossFade()
                    .centerCrop()
                    .into(listHolder.mImage);
            // kcal
            listHolder.mFeedKcal.setText(Float.toString(item.getCarbohydrate()));

        }

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

    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mAuthorName;
        TextView mAuthorGoal;
        ImageView mAuthorImage;
        TextView mFeedKcal;
        TextView mDate;
        ImageView mImage;
        View mAuthorProfile;

        ListViewHolder(View item) {
            super(item);
            mAuthorName = (TextView) item.findViewById(R.id.feed_author_name);
            mAuthorGoal = (TextView) item.findViewById(R.id.feed_author_goal);
            mAuthorImage = (ImageView) item.findViewById(R.id.feed_author_image);
            mFeedKcal = (TextView) item.findViewById(R.id.feed_kcal);
            mDate = (TextView) item.findViewById(R.id.feed_date);
            mImage = (ImageView) item.findViewById(R.id.feed_image);
            mAuthorProfile = item.findViewById(R.id.feed_author_profile);

            mAuthorProfile.setOnClickListener(this);
            item.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.feed_author_profile:
                    mPresenter.onAuthorClick(getLayoutPosition());
                    break;
                default:
                    mPresenter.onFeedClick(getLayoutPosition());
                    break;
            }
        }
    }

}

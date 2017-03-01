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

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ListViewHolder> {
    private ItemContract.Presenter mPresenter;


    public ItemsAdapter(ItemContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public ItemsAdapter.ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        // Infla o layout da célula da lista
        View listItem = inflater.inflate(R.layout.item_list, parent, false);
        return new ListViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(ItemsAdapter.ListViewHolder holder, int position) {
        Item item = mPresenter.getItem(position);
        if (item == null) {
            return;
        }

        // Nome do Author
        holder.mAuthorName.setText(item.getProfile().getName());
        // Meta do Author
        holder.mAuthorGoal.setText(item.getProfile().getGeneralGoal());
        // image de perfil do Author

        Glide.with(holder.mAuthorImage.getContext())
                .load(item.getProfile().getImage())
                .transform(new GlideCircleTransform(holder.mAuthorImage.getContext()))
                .placeholder(R.drawable.circular_border)
                .error(R.drawable.circular_border)
                .into(holder.mAuthorImage);

        // Dados do item
        //Data
        holder.mDate.setText(item.getDate());
        // image do prato
        Glide.with(holder.mImage.getContext())
                .load(item.getImage())
                .crossFade()
                .centerCrop()
                .placeholder(R.color.image_place_holder_background)
                .error(R.color.image_place_holder_background)
                .into(holder.mImage);
        // kcal
        holder.mFeedKcal.setText(Float.toString(item.getCarbohydrate()));
    }

    @Override
    public int getItemCount() {
        return mPresenter.itemCount();
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
            mAuthorName = (TextView) item.findViewById(R.id.author_name);
            mAuthorGoal = (TextView) item.findViewById(R.id.author_goal);
            mAuthorImage = (ImageView) item.findViewById(R.id.author_image);
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

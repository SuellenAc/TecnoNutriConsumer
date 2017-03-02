package com.example.suellencolangelo.tecnonutriconsumer.item.detail;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.suellencolangelo.tecnonutriconsumer.R;
import com.example.suellencolangelo.tecnonutriconsumer.model.Food;
import com.example.suellencolangelo.tecnonutriconsumer.model.Item;
import com.example.suellencolangelo.tecnonutriconsumer.utils.DateUtils;
import com.example.suellencolangelo.tecnonutriconsumer.utils.glide.GlideCircleTransform;

/**
 * Created by suellencolangelo on 01/03/17.
 */

public class ItemDetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int FOOTER = 0;
    private static final int HEADER = 1;
    private static final int ITEM = 2;

    private ItemDetailsContract.Presenter mPresenter;

    public ItemDetailsAdapter(ItemDetailsContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public int getItemCount() {
        return mPresenter.getItemCount();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View listItem;
        switch (viewType) {
            case FOOTER:
                listItem = inflater.inflate(R.layout.foods_list, parent, false);
                return new FooterViewHolder(listItem);
            case HEADER:
                listItem = inflater.inflate(R.layout.item_list, parent, false);
                return new HeaderViewHolder(listItem);
            case ITEM:
                listItem = inflater.inflate(R.layout.foods_list, parent, false);
                return new FoodViewHolder(listItem);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == 0) {
            bindHeader((HeaderViewHolder) holder, (Item) mPresenter.getObjectAdapter(position));
        } else if (position == getItemCount() - 1) {
            bindFooter((FooterViewHolder) holder, (Item) mPresenter.getObjectAdapter(position));
        } else {
            bindFood((FoodViewHolder) holder, (Food) mPresenter.getObjectAdapter(position));
        }

    }

    private void bindHeader(HeaderViewHolder holder, Item item) {
        if (item == null) {
            return;
        }
        Context context = holder.mDate.getContext();

        // Nome do Author
        holder.mAuthorName.setText(item.getProfile().getName());
        // Meta do Author
        holder.mAuthorGoal.setText(item.getProfile().getGeneralGoal());
        // image de perfil do Author

        Glide.with(context)
                .load(item.getProfile().getImage())
                .transform(new GlideCircleTransform(context))
                .placeholder(R.drawable.circular_border)
                .error(R.drawable.circular_border)
                .into(holder.mAuthorImage);

        // Dados do item
        //Data
        try {
            String mealDate = context.getResources().getString(R.string.meal_date, DateUtils.dateToString(item.getDate(), DateUtils.DATE_ONLY_FORMAT));
            holder.mDate.setText(mealDate);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // image do prato
        Glide.with(holder.mImage.getContext())
                .load(item.getImage())
                .crossFade()
                .centerCrop()
                .placeholder(R.color.image_place_holder_background)
                .error(R.color.image_place_holder_background)
                .into(holder.mImage);

        // kcal
        holder.mFeedKcal.setText(context.getResources().getString(R.string.meal_kcal, item.getCarbohydrate()));

    }

    private void bindFooter(FooterViewHolder holder, Item item) {
        Context context = holder.mFoodName.getContext();

        holder.mFoodName.setText(R.string.total);
        holder.mFoodUnit.setVisibility(View.GONE);

        holder.mFoodCalories.setText(context.getResources().getString(R.string.string_float, item.getEnergy()));
        holder.mFoodCarbs.setText(context.getResources().getString(R.string.string_float, item.getCarbohydrate()));
        holder.mFoodProtein.setText(context.getResources().getString(R.string.string_float, item.getProtein()));
        holder.mFoodFats.setText(context.getResources().getString(R.string.string_float, item.getFat()));
    }

    private void bindFood(FoodViewHolder holder, Food food) {
        Context context = holder.mFoodName.getContext();

        holder.mFoodName.setText(food.getDescription());
        holder.mFoodUnit.setVisibility(View.VISIBLE);
        holder.mFoodUnit.setText(context.getResources().getString(R.string.string_float, food.getAmount()));

        holder.mFoodCalories.setText(context.getResources().getString(R.string.string_float, food.getEnergy()));
        holder.mFoodCarbs.setText(context.getResources().getString(R.string.string_float, food.getCarbohydrate()));
        holder.mFoodProtein.setText(context.getResources().getString(R.string.string_float, food.getProtein()));
        holder.mFoodFats.setText(context.getResources().getString(R.string.string_float, food.getFat()));

    }

    @Override
    public int getItemViewType(int position) {
        // dados do autor e feed geral
        if (position == 0) {
            return HEADER;
        }
        // total de itens consumidos
        if (position == getItemCount() - 1) {
            return FOOTER;
        }
        // Lista de alimentos.
        return ITEM;
    }

    public class FooterViewHolder extends FoodViewHolder {

        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder {
        TextView mFoodName;
        TextView mFoodUnit;
        TextView mFoodCalories;
        TextView mFoodCarbs;
        TextView mFoodProtein;
        TextView mFoodFats;

        public FoodViewHolder(View itemView) {
            super(itemView);
            mFoodName = (TextView) itemView.findViewById(R.id.food_name);
            mFoodUnit = (TextView) itemView.findViewById(R.id.food_unit);
            mFoodCalories = (TextView) itemView.findViewById(R.id.food_calories);
            mFoodCarbs = (TextView) itemView.findViewById(R.id.food_carbs);
            mFoodProtein = (TextView) itemView.findViewById(R.id.food_protein);
            mFoodFats = (TextView) itemView.findViewById(R.id.food_fats);
        }
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mAuthorName;
        TextView mAuthorGoal;
        ImageView mAuthorImage;
        TextView mFeedKcal;
        TextView mDate;
        ImageView mImage;
        View mAuthorProfile;

        HeaderViewHolder(View item) {
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
            }
        }
    }
}
